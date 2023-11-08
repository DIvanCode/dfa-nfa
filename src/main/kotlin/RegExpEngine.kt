abstract class Instruction
class CharInstruction(var a: Char) : Instruction()
class MatchInstruction : Instruction()
class JumpInstruction(var x: Int) : Instruction()
class SplitInstruction(var x: Int, var y: Int) : Instruction()

class RegExpEngine(inputRegExp: String) {
    val instructions : MutableList<Instruction> = mutableListOf()

    private fun isChar(a: Char): Boolean {
        return a == 'a' || a == 'b'
    }

    private fun parseSingleRegExp(regExp: String) {
        if (regExp.isEmpty()) {
            return
        }

        when (val com = regExp.last()) {
            '?' -> {
                val split = SplitInstruction(instructions.size + 1, -1)
                instructions.add(split)
                parseSingleRegExp(regExp.substring(0, regExp.length - 1))
                split.y = instructions.size
            }
            '*' -> {
                val jump = JumpInstruction(instructions.size)
                val split = SplitInstruction(instructions.size + 1, -1)
                instructions.add(split)
                parseSingleRegExp(regExp.substring(0, regExp.length - 1))
                instructions.add(jump)
                split.y = instructions.size
            }
            '+' -> {
                val split = SplitInstruction(instructions.size, -1)
                parseSingleRegExp(regExp.substring(0, regExp.length - 1))
                instructions.add(split)
                split.y = instructions.size
            }
            else -> {
                instructions.add(CharInstruction(com))
                parseSingleRegExp(regExp.substring(0, regExp.length - 1))
            }
        }
    }

    private fun parseSimpleRegExp(regExp: String) {
        var i = 0
        while (i < regExp.length) {
            var j = i + 1
            while (j < regExp.length && !isChar(regExp[j])) {
                ++j
            }

            parseSingleRegExp(regExp.substring(i, j))

            i = j
        }
    }

    private fun parseOr(sRegExps: MutableList<String>) {
        if (sRegExps.size == 1) {
            parseSimpleRegExp(sRegExps[0])
            return
        }

        val split = SplitInstruction(instructions.size + 1, -1)
        instructions.add(split)
        parseSimpleRegExp(sRegExps[0])
        val jump = JumpInstruction(-1)
        instructions.add(jump)
        split.y = instructions.size
        parseOr(sRegExps.subList(1, sRegExps.size))
        jump.x = instructions.size
    }

    private fun parseRegExp(regExp: String) {
        var i = 0
        while (i < regExp.length) {
            if (i + 1 == regExp.length) {
                instructions.add(CharInstruction(regExp[i]))
                ++i
                continue
            }

            var j = i + 1
            val sRegExps : MutableList<String> = mutableListOf()
            while (j < regExp.length && (!isChar(regExp[j - 1]) || !isChar(regExp[j]))) {
                if (regExp[j] == '|') {
                    sRegExps.add(regExp.substring(i, j))
                    i = j + 1
                }
                ++j
            }

            sRegExps.add(regExp.substring(i, j))
            i = j

            parseOr(sRegExps)
        }

        instructions.add(MatchInstruction())
    }

    init {
        parseRegExp(inputRegExp)
    }

    private fun process(id: Int, pos: Int, word: String): Boolean {
        if (instructions[id] is MatchInstruction) {
            return pos == word.length
        }

        if (instructions[id] is CharInstruction) {
            if (pos == word.length) {
                return false
            }
            if (word[pos] != (instructions[id] as CharInstruction).a) {
                return false
            }
            return process(id + 1, pos + 1, word)
        }

        if (instructions[id] is JumpInstruction) {
            return process((instructions[id] as JumpInstruction).x, pos, word)
        }

        return process((instructions[id] as SplitInstruction).x, pos, word) ||
                process((instructions[id] as SplitInstruction).y, pos, word)
    }

    fun query(word: String): Boolean {
        return process(0, 0, word)
    }
}
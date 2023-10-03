import java.io.File

class DFANode(val i: Int, alphabetSize: Int) {
    private val link: Array<DFANode?> = Array(alphabetSize) {_ -> null}

    fun addLink(to: DFANode, x: Int) {
        link[x] = to
    }

    fun getNext(x: Int): DFANode? = link[x]
}

class DFA(val n: Int, val m: Int) {
    val nodes = Array(n) { i -> DFANode(i, m) }
    var startNode: Int? = null
    var accNodes: MutableList<Int> = mutableListOf()

    fun addLink(from: Int, to: Int, x: Int) {
        nodes[from].addLink(nodes[to], x)
    }

    fun process(w: List<Int>): Boolean {
        if (startNode == null)
            return false

        var v: DFANode? = nodes[startNode!!]
        for (c in w) {
            if (v == null) {
                break
            }
            v = v.getNext(c)
        }

        return v != null && v.i in accNodes
    }
}

fun readDFA(fileName: String): DFA {
    val text = File(fileName).readLines()

    val dfa = DFA(text[0].toInt(), text[1].toInt())

    dfa.startNode = text[2].split(" ").map { it.toInt() }[0]
    dfa.accNodes = text[3].split(" ").map { it.toInt() }.toMutableList()

    for (i in 4..text.size-2) {
        val row = text[i].split(" ").map { it.toInt() }
        dfa.addLink(row[0], row[2], row[1])
    }

    return dfa
}

class NFANode(val i: Int, alphabetSize: Int) {
    private val link: Array<MutableList<NFANode>> = Array(alphabetSize) {_ -> mutableListOf()}

    fun addLink(to: NFANode, x: Int) {
        link[x].add(to)
    }

    fun getNexts(x: Int): MutableList<NFANode> = link[x]
}

class NFA(val n: Int, val m: Int) {
    val nodes = Array(n) { i -> NFANode(i, m) }
    var startNodes: List<Int> = emptyList()
    var accNodes: List<Int> = emptyList()

    fun addLink(from: Int, to: Int, x: Int) {
        nodes[from].addLink(nodes[to], x)
    }

    fun process(w: List<Int>): Boolean {
        if (startNodes.isEmpty())
            return false

        var state = startNodes
        for (c in w) {
            val nextState = mutableListOf<Int>()

            for (v in state) {
                for (u in nodes[v].getNexts(c)) {
                    if (u.i in nextState) continue
                    nextState.add(u.i)
                }
            }

            state = nextState
        }

        for (v in state)
            if (v in accNodes)
                return true
        return false
    }
}

fun readNFA(fileName: String): NFA {
    val text = File(fileName).readLines()

    val nfa = NFA(text[0].toInt(), text[1].toInt())

    nfa.startNodes = text[2].split(" ").map { it.toInt() }
    nfa.accNodes = text[3].split(" ").map { it.toInt() }

    for (i in 4..text.size-2) {
        val row = text[i].split(" ").map { it.toInt() }
        nfa.addLink(row[0], row[2], row[1])
    }

    return nfa
}

fun readWord(fileName: String): List<Int> {
    val text = File(fileName).readLines()
    return text[text.size-1].split(" ").map { it.toInt() }
}

fun transformNFAtoDFA(nfa: NFA): DFA {
    val dfa = DFA(1 shl nfa.n, nfa.m)

    for (mask in 0 until (1 shl nfa.n)) {
        for (c in 0 until nfa.m) {
            var toMask = 0
            for (i in 0 until nfa.n) {
                if (((mask shr i) and 1) == 1) {
                    for (to in nfa.nodes[i].getNexts(c)) {
                        toMask = toMask or (1 shl to.i)
                    }
                }
            }
            dfa.addLink(mask, toMask, c)
        }

        for (i in 0 until nfa.n) {
            if (((mask shr i) and 1) == 1) {
                if (i in nfa.accNodes) {
                    dfa.accNodes.add(mask)
                }
            }
        }
    }

    var startMask = 0
    for (s in nfa.startNodes) {
        startMask = startMask or (1 shl s)
    }

    dfa.startNode = startMask

    return dfa
}

abstract class Instruction
class CharInstruction(var a: Char) : Instruction()
class MatchInstruction : Instruction()
class JumpInstruction(var x: Int) : Instruction()
class SplitInstruction(var x: Int, var y: Int) : Instruction()

class Engine(inputRegExp: String) {
    private val instructions : MutableList<Instruction> = mutableListOf()

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
        for (instruction in instructions) {
            when (instruction) {
                is CharInstruction -> {
                    println("char ${instruction.a}")
                }

                is MatchInstruction -> {
                    println("match")
                }

                is JumpInstruction -> {
                    println("jump ${instruction.x}")
                }

                is SplitInstruction -> {
                    println("split ${instruction.x}, ${instruction.y}")
                }

                else -> {
                    println("undefined")
                }
            }
        }
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

fun main() {
    val fileName = "sample.txt"

    val text = File(fileName).readLines()

    val regExp = text[0]
    val engine = Engine(regExp)

    val word = text[1]
    println(engine.query(word))

//    val w = readWord(fileName)
//    val nfa = readNFA(fileName)
//    val dfa = transformNFAtoDFA(nfa)
//
//    println(dfa.process(w))
}
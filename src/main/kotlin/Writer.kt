import java.io.File

object Writer {
    fun writeDFA(fileName: String, dfa: DFA) {
        File(fileName).appendText("${dfa.n}\n")
        File(fileName).appendText("${dfa.m}\n")
        File(fileName).appendText("${dfa.startNode}\n")
        for (accNode in dfa.accNodes) {
            File(fileName).appendText("$accNode ")
        }
        File(fileName).appendText("\n")
        for (node in dfa.nodes) {
            for (i in 0 until node.link.size) {
                if (node.link[i] != null)
                    File(fileName).appendText("${node.i} $i ${node.link[i]?.i}\n")
            }
        }
    }

    fun writeRegExpEngine(fileName: String, regExpEngine: RegExpEngine) {
        for (instruction in regExpEngine.instructions) {
            when (instruction) {
                is CharInstruction -> {
                    File(fileName).appendText("char ${instruction.a}\n")
                }

                is MatchInstruction -> {
                    File(fileName).appendText("match\n")
                }

                is JumpInstruction -> {
                    File(fileName).appendText("jump ${instruction.x}\n")
                }

                is SplitInstruction -> {
                    File(fileName).appendText("split ${instruction.x}, ${instruction.y}\n")
                }

                else -> {
                    File(fileName).appendText("<undefined>\n")
                }
            }
        }
    }
}
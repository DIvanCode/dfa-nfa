import java.io.File

object Reader {
    fun readDFA(fileName: String): DFA {
        val text = File(fileName).readLines()

        val dfa = DFA(text[0].toInt(), text[1].toInt())

        dfa.startNode = text[2].split(" ").map { it.toInt() }[0]
        dfa.accNodes = text[3].split(" ").map { it.toInt() }.toMutableList()

        for (i in 4..text.size-1) {
            val row = text[i].split(" ").map { it.toInt() }
            dfa.addLink(row[0], row[2], row[1])
        }

        return dfa
    }

    fun readNFA(fileName: String): NFA {
        val text = File(fileName).readLines()

        val nfa = NFA(text[0].toInt(), text[1].toInt())

        nfa.startNodes = text[2].split(" ").map { it.toInt() }
        nfa.accNodes = text[3].split(" ").map { it.toInt() }.toMutableList()

        for (i in 4..text.size-1) {
            val row = text[i].split(" ").map { it.toInt() }
            nfa.addLink(row[0], row[2], row[1])
        }

        return nfa
    }

    fun readList(fileName: String): List<Int> {
        val text = File(fileName).readLines()
        return text[0].split(" ").map { it.toInt() }
    }

    fun readString(fileName: String): String {
        val text = File(fileName).readLines()
        return text[0]
    }
}
fun task1DFA(dfaFile: String, wordFile: String): Boolean {
    val dfa = Reader.readDFA(dfaFile)
    val input = Reader.readList(wordFile)
    return dfa.process(input)
}

fun task1NFA(nfaFile: String, wordFile: String): Boolean {
    val nfa = Reader.readNFA(nfaFile)
    val input = Reader.readList(wordFile)
    return nfa.process(input)
}
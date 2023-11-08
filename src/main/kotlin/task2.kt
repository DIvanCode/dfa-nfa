fun task2(fileName: String) {
    val nfa = Reader.readNFA(fileName)
    val dfa = nfa.transformToDFA()
    Writer.writeDFA(fileName, dfa)
}
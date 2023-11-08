fun task4(fileName: String) {
    val dfa = Reader.readDFA(fileName)
    val minDFA = MooresAlgorithm(dfa)
    Writer.writeDFA(fileName, minDFA)
}
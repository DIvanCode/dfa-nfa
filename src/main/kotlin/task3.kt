fun task3(regExpFile: String, wordFile: String): Boolean {
    val regExp = Reader.readString(regExpFile)
    val word = Reader.readString(wordFile)

    val regExpEngine = RegExpEngine(regExp)
    Writer.writeRegExpEngine(regExpFile, regExpEngine)

    return regExpEngine.query(word)
}
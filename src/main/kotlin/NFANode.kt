class NFANode(val i: Int, alphabetSize: Int) {
    private val link: Array<MutableList<NFANode>> = Array(alphabetSize) {_ -> mutableListOf()}

    fun addLink(to: NFANode, x: Int) {
        link[x].add(to)
    }

    fun getNexts(x: Int): MutableList<NFANode> = link[x]
}
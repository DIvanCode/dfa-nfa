class DFANode(val i: Int, alphabetSize: Int) {
    val link: Array<DFANode?> = Array(alphabetSize) {_ -> null}

    fun addLink(to: DFANode, x: Int) {
        link[x] = to
    }

    fun getNext(x: Int): DFANode? = link[x]
}
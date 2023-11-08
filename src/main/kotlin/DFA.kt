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
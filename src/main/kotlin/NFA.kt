class NFA(val n: Int, val m: Int) {
    val nodes = Array(n) { i -> NFANode(i, m) }
    var startNodes: List<Int> = emptyList()
    var accNodes: MutableList<Int> = mutableListOf()

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

    fun transformToDFA(): DFA {
        val dfa = DFA(1 shl n, m)

        for (mask in 0 until (1 shl n)) {
            for (c in 0 until m) {
                var toMask = 0
                for (i in 0 until n) {
                    if (((mask shr i) and 1) == 1) {
                        for (to in nodes[i].getNexts(c)) {
                            toMask = toMask or (1 shl to.i)
                        }
                    }
                }
                dfa.addLink(mask, toMask, c)
            }

            for (i in 0 until n) {
                if (((mask shr i) and 1) == 1) {
                    if (i in accNodes) {
                        dfa.accNodes.add(mask)
                    }
                }
            }
        }

        var startMask = 0
        for (s in startNodes) {
            startMask = startMask or (1 shl s)
        }

        dfa.startNode = startMask

        return dfa
    }
}
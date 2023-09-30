import java.io.File

class DFANode(val i: Int, alphabetSize: Int) {
    private val link: Array<DFANode?> = Array(alphabetSize) {_ -> null}

    fun addLink(to: DFANode, x: Int) {
        link[x] = to
    }

    fun getNext(x: Int): DFANode? = link[x]
}

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

fun readDFA(fileName: String): DFA {
    val text = File(fileName).readLines()

    val dfa = DFA(text[0].toInt(), text[1].toInt())

    dfa.startNode = text[2].split(" ").map { it.toInt() }[0]
    dfa.accNodes = text[3].split(" ").map { it.toInt() }.toMutableList()

    for (i in 4..text.size-2) {
        val row = text[i].split(" ").map { it.toInt() }
        dfa.addLink(row[0], row[2], row[1])
    }

    return dfa
}

class NFANode(val i: Int, alphabetSize: Int) {
    private val link: Array<MutableList<NFANode>> = Array(alphabetSize) {_ -> mutableListOf()}

    fun addLink(to: NFANode, x: Int) {
        link[x].add(to)
    }

    fun getNexts(x: Int): MutableList<NFANode> = link[x]
}

class NFA(val n: Int, val m: Int) {
    val nodes = Array(n) { i -> NFANode(i, m) }
    var startNodes: List<Int> = emptyList()
    var accNodes: List<Int> = emptyList()

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
}

fun readNFA(fileName: String): NFA {
    val text = File(fileName).readLines()

    val nfa = NFA(text[0].toInt(), text[1].toInt())

    nfa.startNodes = text[2].split(" ").map { it.toInt() }
    nfa.accNodes = text[3].split(" ").map { it.toInt() }

    for (i in 4..text.size-2) {
        val row = text[i].split(" ").map { it.toInt() }
        nfa.addLink(row[0], row[2], row[1])
    }

    return nfa
}

fun readWord(fileName: String): List<Int> {
    val text = File(fileName).readLines()
    return text[text.size-1].split(" ").map { it.toInt() }
}

fun transformNFAtoDFA(nfa: NFA): DFA {
    val dfa = DFA(1 shl nfa.n, nfa.m)

    for (mask in 0 until (1 shl nfa.n)) {
        for (c in 0 until nfa.m) {
            var toMask = 0
            for (i in 0 until nfa.n) {
                if (((mask shr i) and 1) == 1) {
                    for (to in nfa.nodes[i].getNexts(c)) {
                        toMask = toMask or (1 shl to.i)
                    }
                }
            }
            dfa.addLink(mask, toMask, c)
        }

        for (i in 0 until nfa.n) {
            if (((mask shr i) and 1) == 1) {
                if (i in nfa.accNodes) {
                    dfa.accNodes.add(mask)
                }
            }
        }
    }

    var startMask = 0
    for (s in nfa.startNodes) {
        startMask = startMask or (1 shl s)
    }

    dfa.startNode = startMask

    return dfa
}

fun main() {
    val fileName = "sample.txt"

    val w = readWord(fileName)
    val nfa = readNFA(fileName)
    val dfa = transformNFAtoDFA(nfa)

    println(dfa.process(w))
}
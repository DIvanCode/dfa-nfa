import java.io.File

class Node(val i: Int, alphabetSize: Int) {
    private val link: Array<MutableList<Node>> = Array(alphabetSize) {_ -> mutableListOf()}

    fun addLink(to: Node, x: Int) {
        link[x].add(to)
    }

    fun getNext(x: Int): MutableList<Node> = link[x]
}

fun dfa(filename: String): Boolean {
    val text = File(filename).readLines()

    val n: Int = text[0].toInt()
    val m: Int = text[1].toInt()

    val node = Array(n) { i -> Node(i, m) }

    val starts = text[2].split(" ").map { it.toInt() }
    val acc = text[3].split(" ").map { it.toInt() }

    for (i in 4..text.size-2) {
        val row = text[i].split(" ").map { it.toInt() }
        node[row[0]].addLink(node[row[2]], row[1])
    }

    val w = text[text.size-1].split(" ").map { it.toInt() }

    var v: Node? = node[starts[0]]
    for (c in w) {
        if (v == null) {
            break
        }
        v = v.getNext(c)[0]
    }

    return v != null && v.i in acc
}

fun nfa(filename: String): Boolean {
    val text = File(filename).readLines()

    val n: Int = text[0].toInt()
    val m: Int = text[1].toInt()

    val node = Array(n) { i -> Node(i, m) }

    val starts = text[2].split(" ").map { it.toInt() }
    val acc = text[3].split(" ").map { it.toInt() }

    for (i in 4..text.size-2) {
        val row = text[i].split(" ").map { it.toInt() }
        node[row[0]].addLink(node[row[2]], row[1])
    }

    val w = text[text.size-1].split(" ").map { it.toInt() }

    var state = starts
    for (c in w) {
        val nextState = mutableListOf<Int>()

        for (v in state) {
            for (u in node[v].getNext(c)) {
                if (u.i in nextState) continue
                nextState.add(u.i)
            }
        }

        state = nextState
    }

    for (v in state) {
        if (v in acc) {
            return true
        }
    }
    return false
}

fun main() {
    println(nfa("sample.txt"))
}
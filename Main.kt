class Node<A>(var value: A,
              var next: Node<A>? = null,
              var previous: Node<A>? = null,
              var count: Int = 0)

class LinkedList<A> {
    var head: Node<A>? = null
    // The head is the first node on the list.

    var tail: Node<A>? = null
    // The tail is the final node on the list.

    fun insertNode(value: A) {

        // Creates a new node with a count of how many distinct characters in its string.

        val newNode = Node(value)
        val distinctCount = (value.toString().lowercase().toList().distinct().count())
        newNode.count = (distinctCount)
        var node = head

        // Inserts new node before the node with the same number of characters
        // or before the next highest number of characters.

        if (node == null) {
            head = newNode
            tail = newNode
        } else {
            while (node?.count!! < newNode.count && node.next != null) {
                node = node.next
            }
            val prevNode = node.previous
            val thirdInLine = node.next
            when {
                thirdInLine != null && prevNode != null -> {
                    if (node.count < newNode.count) {
                        node.next = newNode
                        newNode.previous = node
                        newNode.next = thirdInLine
                        thirdInLine.previous = newNode
                    } else {
                        prevNode.next = newNode
                        newNode.previous = prevNode
                        newNode.next = node
                        node.previous = newNode
                    }
                }
                thirdInLine == null && prevNode != null -> {
                    if (node.count < newNode.count) {
                        node.next = newNode
                        newNode.previous = node
                        tail = newNode
                    } else {
                        prevNode.next = newNode
                        newNode.previous = prevNode
                        newNode.next = node
                        node.previous = newNode
                    }
                }
                thirdInLine != null && prevNode == null -> {
                    if (node.count < newNode.count) {
                        node.next = newNode
                        newNode.previous = node
                        newNode.next = thirdInLine
                        thirdInLine.previous = newNode
                    } else {
                        head = newNode
                        newNode.next = node
                        node.previous = newNode
                    }
                } else -> {
                if (node.count < newNode.count) {
                    node.next = newNode
                    newNode.previous = node
                    tail = newNode
                } else {
                    head = newNode
                    newNode.next = node
                    node.previous = newNode
                }
            }
            }
        }
    }

    fun middleNode(start: Node<A>?, last: Node<A>?): Node<A> {

        // Finds the node in the middle of two on the list.

        var slow = start
        var fast = start
        while (fast?.next != last?.next) {
            fast = fast?.next
            if (fast?.next != last?.next) {
                slow = slow?.next
                fast = fast?.next
                // Fast goes next twice until it hits null. Slow went half speed so it stops halfway.
            }
        }
        return slow!!
    }

    fun binary(value: A) {

        // Creates a new node that is identical to the one sought.

        var start = head
        var last = tail
        val copyNode = Node(value)
        val distinctCount = (value.toString().lowercase().toList().distinct().count())
        var middle = middleNode(start, last)

        // Looks at the middle node. If the count of the sought node is higher
        // than the middle node, it finds the middle node of the larger half
        // and vice versa. It continues halving the list and comparing the
        // counts until the integer values match.

        if (start != null) {
            while (distinctCount != middle.count) {
                if (distinctCount < middle.count) {
                    last = middle.previous
                    middle = middleNode(start, last)
                } else {
                    start = middle.next
                    middle = middleNode(start, last)
                }
            }

            // If there is more than one node with the same number of distinct
            // characters, it steps backward, checking the strings until the copyNode
            // matches the sought node.

            while (copyNode.value != middle.value) {
                middle = middle.previous!!
            }
            val count = countIt(middle)
            // See below.

            return println("${middle.value} is at slot: $count")
        } else return println("There is no list.")
    }

    fun countIt(Node: Node<A>): Int? {

        // Counts the node's slot on the list from the start.
        // Only necessary to illustrate the node can be found.

        var count = 1
        var node = head
        return if (node != null) {
            while (node != Node) {
                node = node?.next
                count++
            }
                count
            } else null
    }

    override fun toString(): String {
        var node = head
        var list = ""

        // Puts each node on the list until the null at the end.

        while (node != null) {
            list = list.plus("${node.count} - ${node.value}")
            node = node.next
            if (node != null) list = list.plus(", ")
        }
        return list
    }
}

fun main() {
    println("Hello World! This is a list of random words in order of their number of distinct characters:")

    val randomWords = LinkedList<String>()


    randomWords.insertNode("A")
    println(randomWords)
    randomWords.insertNode("Ant")
    println(randomWords)
    randomWords.insertNode("At")
    println(randomWords)
    randomWords.insertNode("Apple")
    println(randomWords)
    randomWords.insertNode("Artichoke")
    println(randomWords)
    randomWords.insertNode("Antelope")
    println(randomWords)
    randomWords.insertNode("Antithesis")
    println(randomWords)
    randomWords.insertNode("Aquarium")
    println(randomWords)
    randomWords.insertNode("Arrow")
    println(randomWords)
    randomWords.insertNode("Articulate")
    println(randomWords)
    randomWords.insertNode("Ass")
    println(randomWords)

    randomWords.binary("A")
    randomWords.binary("Ass")
    randomWords.binary("At")
    randomWords.binary("Ant")
    randomWords.binary("Arrow")
    randomWords.binary("Apple")
    randomWords.binary("Aquarium")
    randomWords.binary("Antithesis")
    randomWords.binary("Antelope")
    randomWords.binary("Articulate")
    randomWords.binary("Artichoke")

    println(randomWords)
}
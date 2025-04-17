package uz.droid.wallatopia.kaptura.lru

class LruCache<K, V>(
    private val maxSize: Long
) {

    private val cache = mutableMapOf<K, Node<K, V>>()

    private var head: Node<K, V>? = null
    private var tail: Node<K, V>? = null
    fun save(k: K, v: V) {
        if (cache.containsKey(k)) {
            val mruNode = cache[k]!!
            mruNode.data = v
            removeNode(mruNode)
            addToHead(mruNode)
        } else {
            val newNode = Node(k, v)
            cache[k] = newNode
            addToHead(newNode)
            if (cache.size > maxSize) {
                removeLeastRecentlyUsed()
            }
        }

    }


    private fun removeLeastRecentlyUsed() {
        tail?.let { lru ->
            cache.remove(lru.key)
            removeNode(lru)

            if (lru.prev == null) {
                head = null
            }
            tail = lru.prev
            tail?.next = null
        }
    }

    private fun removeNode(node: Node<K, V>) {
        if (node.prev != null) {
            node.prev?.next = node.next
        } else {
            head = node.next
        }

        if (node.next != null) {
            node.next?.prev = node.prev
        } else {
            tail = node.prev
        }
    }


    private fun addToHead(node: Node<K, V>) {
        node.next = head
        head?.prev = node
        head = node
        head?.prev = null
        if (tail == null) {
            tail = head
        }
    }

    fun get(k: K): V? {
        val node = cache[k] ?: return null
        removeNode(node)
        addToHead(node)
        return node.data
    }


}
package uz.droid.wallatopia.kaptura.lru

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class LruCache<K, V>(
    private val maxSize: Long
) {

    private val cache = mutableMapOf<K, Node<K, V>>()
    private val mutex = Mutex()

    private var head: Node<K, V>? = null
    private var tail: Node<K, V>? = null

    suspend fun save(k: K, v: V) = mutex.withLock {
        val existing = cache[k]
        if (existing != null) {
            existing.data = v
            removeNode(existing)
            addToHead(existing)
        } else {
            val newNode = Node(k, v)
            cache[k] = newNode
            addToHead(newNode)
            if (cache.size > maxSize) {
                removeLeastRecentlyUsed()
            }
        }
    }

    suspend fun get(k: K): V? = mutex.withLock {
        val node = cache[k] ?: return@withLock null
        removeNode(node)
        addToHead(node)
        node.data
    }

    private fun removeLeastRecentlyUsed() {
        tail?.let { lru ->
            cache.remove(lru.key)
            removeNode(lru)
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

        node.prev = null
        node.next = null
    }

    private fun addToHead(node: Node<K, V>) {
        node.prev = null
        node.next = head
        head?.prev = node
        head = node
        if (tail == null) {
            tail = head
        }
    }
}

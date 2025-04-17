package uz.droid.wallatopia.kaptura.lru

data class Node<K, V>(
    val key: K,
    var data: V,
    var next: Node<K, V>? = null,
    var prev: Node<K, V>? = null,
)

package uz.droid.wallatopia

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
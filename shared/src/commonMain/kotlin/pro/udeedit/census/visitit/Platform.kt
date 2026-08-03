package pro.udeedit.census.visitit

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
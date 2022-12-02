object Utils {
    fun forEachLineInResource(name: String, block: (line: String) -> Unit) {
        val reader = ClassLoader.getSystemResourceAsStream(name)?.reader() ?: error("Could not find content")
        reader.forEachLine(block)
    }
}
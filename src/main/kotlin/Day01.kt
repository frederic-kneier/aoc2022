@file:JvmName("Day01")

typealias Elf = ArrayList<Int>
typealias Elves = ArrayList<Elf>

fun main() {
    val elves = Elves()
    fun addElf() = Elf().also(elves::add)

    var elve = addElf()
    val content = ClassLoader.getSystemResourceAsStream("Day01.txt")?.reader() ?: error("Could not find content")

    content.forEachLine { line ->
        when {
            line.isBlank() -> elve = addElf()
            else -> elve.add(line.toInt())
        }
    }

    elves.sortByDescending { it.sum() }

    val top = elves.firstOrNull()
    val top3 = elves.subList(0,3)

    println(top?.sum())
    println(top3.sumOf { it.sum() })

}

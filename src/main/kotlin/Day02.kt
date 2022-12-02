@file:JvmName("Day02")

import Result.*
import Shape.*

val configurations = listOf(
    Configuration(Rock, Rock, Draw),
    Configuration(Rock, Paper, Loss),
    Configuration(Rock, Scissors, Win),
    Configuration(Paper, Rock, Win),
    Configuration(Paper, Scissors, Loss),
    Configuration(Paper, Paper, Draw),
    Configuration(Scissors, Rock, Loss),
    Configuration(Scissors, Scissors, Draw),
    Configuration(Scissors, Paper, Win),
)

fun main() {
    var totalShape = 0
    var totalResult = 0

    Utils.forEachLineInResource("Day02.txt") { line ->
        if (line.isBlank()) return@forEachLineInResource
        val parts = line.split(' ')

        val opponentShape = parts[0].let {
            when (it) {
                "A" -> Rock
                "B" -> Paper
                "C" -> Scissors
                else -> error("Opponent shape for '$it' is not supported")
            }
        }
        val playerShape = parts[1].let {
            when (it) {
                "X" -> Rock
                "Y" -> Paper
                "Z" -> Scissors
                else -> error("Player shape for '$it' is not supported")
            }
        }
        val result = parts[1].let {
            when (it) {
                "X" -> Loss
                "Y" -> Draw
                "Z" -> Win
                else -> error("Result for '$it' is not supported")
            }
        }

        val shapeConfiguration = configurations.first {
            it.opponentShape == opponentShape && it.playerShape == playerShape
        }
        val resultConfiguration = configurations.first {
            it.opponentShape == opponentShape && it.result == result
        }

        totalShape += shapeConfiguration.let { it.playerShape.score + it.result.score }
        totalResult += resultConfiguration.let { it.playerShape.score + it.result.score }
    }

    println("Total score based on player shape is $totalShape")
    println("Total score based on result is $totalResult")
}

data class Configuration(
    val playerShape: Shape,
    val opponentShape: Shape,
    val result: Result,
)

enum class Shape(val score: Int) {
    Rock(score = 1),
    Paper(score = 2),
    Scissors(score = 3),
}

enum class Result(val score: Int) {
    Loss(score = 0),
    Draw(score = 3),
    Win(score = 6),
}
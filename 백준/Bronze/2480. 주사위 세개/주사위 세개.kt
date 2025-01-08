import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main(args: Array<String>) {
    var (first, second, third) = readLine()!!.split(" ").map { it.toInt() }
    if (first == second && second == third) {
        println(10000 + first * 1000)
    } else if (first == second || second == third || third == first) {
        val sameNumber = if (first == second) first else if (second == third) second else first
        println(1000 + sameNumber * 100)
    } else {
        println(100 * maxOf(first, second, third))
    }
}
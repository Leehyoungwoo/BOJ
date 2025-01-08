import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main(args: Array<String>) {
    var (a, b) = readLine()!!.split(" ").map { it.toInt() }
    when {
        a < b -> println("<")
        a == b -> println("==")
        a > b -> println(">")
    }
}
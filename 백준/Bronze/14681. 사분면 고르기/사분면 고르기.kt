import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main(args: Array<String>) {
    val a = readLine()!!.split(' ').map { it.toInt() }
    val b = readLine()!!.split(' ').map { it.toInt() }

    when {
        a[0] > 0 && b[0] > 0 -> println(1)
        a[0] < 0 && b[0] > 0 -> println(2)
        a[0] < 0 && b[0] < 0 -> println(3)
        a[0] > 0 && b[0] < 0 -> println(4)
    }
}
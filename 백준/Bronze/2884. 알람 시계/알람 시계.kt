import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

private val fixMinute = 45
fun main(args: Array<String>) {
    var (time, minute) = readLine()!!.split(' ').map { it.toInt() }
    minute -= fixMinute;
    if (minute < 0) {
        time--;
        minute += 60
    }
    if (time < 0) {
        time += 24
    }
    println(time.toString() + ' ' + minute.toString())
}
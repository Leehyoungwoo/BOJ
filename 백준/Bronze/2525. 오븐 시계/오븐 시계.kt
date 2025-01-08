import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main(args: Array<String>) {
    var (time, minute) = readLine()!!.split(' ').map { it.toInt() }
    var cookingTime = readLine()!!.toInt()
    minute += cookingTime
    if (minute >= 60) {
        var left = minute % 60
        var timePlus = minute / 60
        time += timePlus
        minute = left
    }
    if (time >= 24) {
        time-=24
    }

    println(time.toString() + ' ' + minute.toString())
}
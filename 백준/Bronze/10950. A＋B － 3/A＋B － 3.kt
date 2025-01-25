fun main(args: Array<String>) {
    var testCase = readLine()!!.toInt()
    for(i in 1..testCase) {
        val (a, b) = readLine()!!.split(' ').map { it.toInt() }
        println(a + b)
    }
}
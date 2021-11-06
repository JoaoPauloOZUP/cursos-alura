fun main() {
    val myFunctionLambdaWithReturn: (a: Double, b:Double) -> Double = { a, b ->
        a+b
        // O ultimo sempre será o retorno!
    }
    println(myFunctionLambdaWithReturn(1.0, 2.0))

    val myFunctionAnonymousWithReturn = fun(a:Int, b: Int): String {
        return (a+b).toString()
    }
    println(myFunctionAnonymousWithReturn(1, 2))
}
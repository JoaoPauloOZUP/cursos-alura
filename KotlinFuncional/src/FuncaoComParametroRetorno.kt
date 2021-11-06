fun main() {
    /**
     * Funcao com parametros e retorno
     * */
    val myFunctionWithParametes: (a: Int, b: Int) -> Unit = { a: Int, b: Int ->
        println(a+b)
    }

    val myFunctionWithReturn: (a: Int, b: Int) -> Int = ::sum
    val myFunctionWithReturnTwo: (a: Int, b: Int) -> Int = Sum()
    println(myFunctionWithReturn(1,1))
    println(myFunctionWithReturnTwo(1,1))
}

fun sum(a:Int, b:Int): Int = a+b
class Sum : (Int, Int) -> Int {
    override fun invoke(a: Int, b: Int): Int = a+b
}
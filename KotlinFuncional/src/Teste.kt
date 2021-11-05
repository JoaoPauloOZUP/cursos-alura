fun main() {
    /**
     * Expressao lambda e funcao anonima
     * */
    val myFunctionLambda: () -> Unit = {
        println("Executando com expressao Lambda")
    }
    myFunctionLambda()

    val myFunctionAnonymous: () -> Unit = fun () {
        println("Executando com funcao Anonima")
    }
    myFunctionAnonymous()

    val testOne = { a: Int, b: Int ->
        println(a+b)
    }
    testOne(10, 10)
}
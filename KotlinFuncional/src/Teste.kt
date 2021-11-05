fun main() {
    /**
     * Variaveis do tipo funcao
     * */
    val myFunction: (a: String) -> Unit = ::println
    myFunction("Joca")

    val myFunctionClass = FunctionClass()
    myFunctionClass()
    myFunctionClass("Executando Invoke no operator")
}

class FunctionClass : () -> Unit {
    override fun invoke() {
        println("Executando Invoke")
    }

    operator fun invoke(message: String) {
        println(message)
    }
}
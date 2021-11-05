fun main() {
    /**
     * Variaveis do tipo funcao
     * */
    val myFunction: (a: String) -> Unit = ::println
    myFunction("Joca")

    val myFunctionClass = FunctionClass()
    myFunctionClass()
}

class FunctionClass : () -> Unit {
    override fun invoke() {
        println("Executando Invoke")
    }
}
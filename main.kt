fun isNumber(sym: String): Boolean {
    if (sym.isEmpty()) return false
    for (symbol in sym) {
        if (!symbol.isDigit()) {
            return false
        }
    }
    return true
}

fun main(args: Array<String>) {
    print("Enter expression: ")

    val answer: String? = readLine()
    val ops = arrayOf("+", "-", "*", "/")

    if (!answer.isNullOrEmpty()) {

        val parts = answer.split(' ')
        val stack = mutableListOf<String>()

        for (part in parts.reversed()) {
            if (isNumber(part)) {
                stack.add(0, part)
            } else {
                if (part in ops) {
                    if (stack.size >= 2) {
                        var str = "(" + stack.first()
                        stack.removeAt(0)
                        str = str + part + stack.first() + ")"
                        stack.removeAt(0)
                        stack.add(0, str)

                    } else {
                        println("Less then two arguments, try again.")
                        return
                    }
                } else {
                    println("Seems like you have made a typo or a joke instead of an expression. Please try again.")
                    return
                }
            }
        }

        when {
            stack.size > 1 -> println("Not enough operators, try again.")
            stack.isEmpty() -> println("Stack is empty. How could you let this happen? Try again.")
            else -> println("Result: ${stack.removeAt(stack.lastIndex)}")
        }
    }
    else {
        println("You did not enter anything. Please enter an expression.")
    }
}
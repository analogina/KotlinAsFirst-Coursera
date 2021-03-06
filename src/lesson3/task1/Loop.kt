@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int, step: Int = 0): Int {
    println("    ".repeat(step) + "n: $n, m: $m, step: $step")
    val res = when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m, step + 1) + digitCountInNumber(n % 10, m, step + 1)
    }
    println("    ".repeat(step) + "res: $res")
    if (step == 0) {
        println("*********************************")
        println()
    }
    return res
}

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int = when {
    n < 10 -> 1
    else -> 1 + digitNumber(n / 10)
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int = when {
    (n == 1) -> 1
    (n == 2) -> 1
    else -> {
        var predPred = 1
        var pred = 1
        var otvet = 0
        for (i in 3..n) {
            otvet = pred + predPred
            //  println("i = $i")
            //  println("pred = $pred")
            //  println("predPred = $predPred")
            //  println("f($i) = $otvet")
            predPred = pred
            pred = otvet

        }
        println("************")
        otvet
    }
}

fun fibRec(n: Int): Int = when {
    (n == 1) -> 1
    (n == 2) -> 1
    else -> {
        print("$n ")
        if (n == 3) {
            println()
            System.out.flush()
        }
        fibRec(n - 1) + fibRec(n - 2)
    }

}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int = TODO()

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (m in 2..sqrt(n.toDouble()).toInt()) {
        if (n % m == 0)
            return m
    }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (m in n / 2 downTo 1) {
        if (n % m == 0)
            return m
    }
    return 1
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    val minNumber = min(m, n)
    for (i in 2..minNumber) {
        if (m % i == 0 && n % i == 0) return false
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val sqrtM = sqrt(m.toDouble()).toInt()
    val sqrtN = sqrt(n.toDouble()).toInt()
    for (i in sqrtM..sqrtN) {
        val sqrI = sqr(i)
        if (sqrI in m..n)
            return true
    }
    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {

    fun nextX(): Int = if (x % 2 == 0) {
        (x / 2)
    } else {
        3 * x + 1
    }

    return when (x) {
        1 -> 0
        else ->
            1 + collatzSteps(nextX())
    }
}


/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    val y = x % (2 * PI)
    println("y = $y")
    var i = 1
    var member = y.pow(i)/ factorial(i)
    var res = member
    var n = 1
    println("эпсилон = $eps")
    println("x = $x")
    println("i = $i")
    println("член ряда = $member")
    println("результат = $res")
    while (abs(member) >= eps) {
        i += 2
        n = -n
        member = n * y.pow(i) / factorial(i)
        res += member
        println("i = $i")
        println("n = $n")
        println("member = $member")
        println("eps = $eps")
        println("abs(eps) = ${abs(eps)}")
        println("abs(member) >= eps = ${abs(member) >= eps}")
        println("новый результат = $res")
    }
    println("-------------------------------")
    return res
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    /* period of cosinus is 2 PI */
    val y = x % (2 * PI)
    var i = 2
    var n = -1
    var member = y.pow(i) / factorial(i)
    var res = 1 + (n * member)
    println("x = $x")
    println("y = $y")
    println("i = $i")
    println("эпсилон = $eps")
    println("n = $n")
    println("member = $member")
    println("res = $res")
    while (abs(member) >= eps){
        i += 2
        n = -n
        member = y.pow(i) / factorial(i)
        res += member
        println("i = $i")
        println("n = $n")
        println("member = $member")
        println("abs(member) >= eps = ${abs(member) >= eps}")
        println("res = $res")

    }
    println("res = $res")
    println("*******************")
    return res
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int = TODO()

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int)
        : Boolean = TODO()

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean = TODO()

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int)
        : Int = TODO()

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int = TODO()

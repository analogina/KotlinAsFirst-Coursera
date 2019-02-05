@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
        sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {
    val n1 = number / 1000
    val n2 = number / 100 % 10
    val n3 = number / 10 % 10
    val n4 = number % 10
    return n1 + n2 == n3 + n4
}

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean =
        x1 == x2 || y1 == y2 ||
                abs(x1 - x2) == abs(y1 - y2)


/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {
//    год, номер которого кратен 400, — високосный;
//    остальные годы, номер которых кратен 100, — невисокосные;
//    остальные годы, номер которых кратен 4, — високосные.
    val isLeapYear: Boolean = year % 400 == 0 || year % 4 == 0 && year % 100 != 0
    return when {
        month in setOf(1, 3, 5, 7, 8, 10, 12) -> 31
        month in setOf(4, 6, 9, 11) -> 30
        month == 2 && isLeapYear -> 29
        else -> 28
    }
}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(x1: Double, y1: Double, r1: Double,
                 x2: Double, y2: Double, r2: Double): Boolean {
    val dc = sqrt(sqr(x1 - x2) + sqr(y1 - y2))
    return r2 >= dc + r1
}

/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {
    println("*****************************************************")
    println("Пролезает ли кирпич $a x $b x $c в отверстие $r x $s?")

    fun passes(brickSide1: Int, brickSide2: Int, wallSide1: Int, wallSide2: Int): Boolean {
        println("Пролезает ли $brickSide1 x $brickSide2 в $wallSide1 x $wallSide2?")
        val result = brickSide1 <= wallSide1 && brickSide2 <= wallSide2 ||
                brickSide1 <= wallSide2 && brickSide2 <= wallSide1
        println("$result")
        return result
    }

    return passes(a, b, r, s) ||
            passes(b, c, r, s) ||
            passes(a, c, r, s)
}

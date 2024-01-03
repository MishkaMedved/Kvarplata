package Homework

import scala.math.sqrt

object HomeworkOne {
  def main(args: Array[String]): Unit = {
    println(pow(20))
    println(pow(4))
    println(pow(3))
    println(square(44))
    println(convert(50))
    println(distance(1,2,6,9))
  }

  def pow(num: Int): Int = num * num

  def square(rad: Int): Double = 3.14 * pow(rad)
  def convert(degrees: Double): Unit = {
    val fahrenheit = degrees *(9/5)+32
}
  def length(string: String): Int = string.length
  def distance(x1:Int,y1:Int,x2:Int,y2:Int): Unit = {
    val d = sqrt(pow(x2-x1)+pow(y2-y1))
  }
}
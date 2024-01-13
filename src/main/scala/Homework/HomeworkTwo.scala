package Homework

import scala.annotation.tailrec

object HomeworkTwo {
  def main(args: Array[String]): Unit = {
    parity(19)
    bigOrSmallNull(1)
    forCycleOn()
    forCycleOff()
    println()
    tableMultiplication()
    for (i<-1 to 10) {
      print(fibonachi(i)+" ")
    }
  }

  def parity(num: Int): Unit = {
    if (num % 2 == 0) println("even") else println("odd")
  }

  def bigOrSmallNull(num: Int): Unit = {
    if (num < 0) {
      println("smaller null")
    } else if (num > 0) {
      println("bigger null")
    } else
      println("null")
  }

  def forCycleOn(): Unit = {
    for (i <- 1 to 10) print(i + " ")
    println()
  }
  def forCycleOff(): Unit = {
    for (i <- 1 until  10) print(i + " ")
  }

  def tableMultiplication(): Unit = {
    for (i<-1 to 10){
      val x = 5
      println(x +" * " + i +" = " + (x*i))
    }
  }

  def fibonachi(n: Int): Int = {
    if (n == 1 || n == 2) return 1
    return fibonachi(n-1)+ fibonachi(n-2)
  }

}

package Homework

object HomeworkTwo {
  def main(args: Array[String]): Unit = {
    parity(19)
    bigOrSmallNull(1)
    forCycleOn()
    forCycleOff()
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


}

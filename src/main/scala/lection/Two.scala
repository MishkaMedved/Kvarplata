package lection

import Magic.ImplicitForPrint


object UnapplyTuple extends App with ImplicitForPrint {
  val (x, y, z) = (1, 2, 3)
  x.print // 1
  y.print // 2
  z.print // 3
}

object StrMargin extends App with ImplicitForPrint {
  val str1 =
    """
      |Aaaaa
      |Bbbbb
      |"""
  str1.print // Корявый вывод

  val str2 =
    """
      |Ccccc
      |Ddddd
      |""".stripMargin
  str2.print // С Margin топ

}

object DefaultDefParam extends App with ImplicitForPrint {
  def sayHello(to: String = "World!"): Unit = println(s"Hello $to")

  sayHello()
  sayHello("Scala")
}

object ifElse extends App with ImplicitForPrint {
  //if (check) something else somethingElse
  val x = 1
  val y = 2
  val z = 3

  def f(f: String = "f"): String = {
    println(f)
    f
  }

  def g(g: String = "g"): String = {
    println(g)
    g
  }

  def h(h: String = "h"): String = {
    println(h)
    h
  }

  if (x == y) f()
  if (x == y){
    f()
  }else if (y == z){
    g()
  } else {
    h()
  }

  lazy val lazyVal = {
    println("Thread sleep")
    Thread.sleep(6000)
    1
  }

  val  sleepless = {
    println("sleepless")
    2
  }

  println("1 if eval")
  if(1==1) sleepless else lazyVal
  println("2 if eval")
  if (false) sleepless else lazyVal
  println("3 if eval")
  if (3 == 3) lazyVal else sleepless
  //sleepless
  //1 if
  //2 if
  //Thread sleep
  //3 if

  val res = if (x==y) f() else g() // without else => res: Any, cause sugar else()
  println(res)
  //java:
  //x == y ? v1 : v2
}

object For extends App with ImplicitForPrint {
  //java:
  // for (statement1; statement2; statement3) {
  //     // code
  // }

  for (i <- 1 to 5)println(i)
  for (i <- 1 until 5) println(i)

  val l = List(1, 2, 3, 4, 5)
  for (i <- l) println(i)

  val l2 = l.map(_ * 2)
  for { // or for(i <- l;j <- l2)
    i <- l
    j <- l2
  } s"i $i j $j".print // (1, 1), (1, 2), (1, 3) (1, 4) ... (2, 1) .. (3, 4) .. etc

  for (i <- l) {
    val res = i * i
    res.print
  }

  val l3: List[Int] = for (i <- l) yield i * 2 //eq l.map(_ * 2)

  val userBase = List(
    ("Вася", 28),
    ("Юля", 33),
    ("Катя", 44),
    ("Петя", 23)
  )

  val somethings: List[String] =
    for (user <- userBase if user._2 >= 20 && user._2 < 30)
      yield user._1
  println(somethings) //Вася, Петя

  def f(n: Int, sum: Int) =
    for {
      i <- 0 until n
      j <- 0 until n if i + j == sum
    } yield (i, j)

  println {f(10, 10)} // (1, 9) (2, 8) (3, 7) (4, 6) (5, 5) (6, 4) (7, 3) (8, 2) (9, 1)
}

object While extends App with ImplicitForPrint {

  //java:
  // var i = 0;
  // while (i < 3) {
  //     sout(i);
  //     i += 1;
  // }
  var i = 0
  while (i < 3) {
    println(i)
    i += 1
  }

  var i2 = 0
  do {
    println(i2)
    i2 += 1
  } while (i2 < 0)

}

object Rec extends App  {
  // 5! = 1 * 2 * 3 * 4 * 5 = 120

  //@tailrec => recursive call not in tail position => compile error, ne zoebis
  def factorial1(n: BigInt): BigInt = {
    if (n == 0) 1
    else n * factorial1(n - 1)
  }
  println(factorial1(5))
  //  factorial1(10000) //10000 => java.lang.StackOverflowError

  //  def factorial2(n: BigInt): BigInt = {
  //    @tailrec // only on tail recursive function
  //    def facHelp(acc: BigInt, n: BigInt): BigInt = {
  //      if (n == 0) acc
  //      else facHelp(n * acc, n - 1)
  //    }
  //    facHelp(1, n)
  //  }

  import scala.annotation.tailrec
  @tailrec // only on tail recursive function, zoebis
  def facHelp(acc: BigInt, n: BigInt): BigInt = {
    if (n == 0) acc
    else facHelp(n * acc, n - 1)
  }
  println(facHelp(1, 10000))
}

object TryMe extends App  {
  //java:
  // try {
  //   //some code
  // } catch (Exception e) {
  //     e.printStackTrace();
  // } catch (IllegalAccessError e) {
  //     e.printStackTrace();
  // } catch (Exception | IllegalAccessError e) { //or
  //     e.printStackTrace();
  // } finally {
  //   //some code
  // }

  def div(x: Int, y: Int): Int = x / y // div by 0 => throw exception

  try {
    val res = div(1, 0)
    println(res)
  } catch {
    case ae: ArithmeticException =>
      println("Oh no, div by zero!")
      println(ae.getMessage)
      ae.printStackTrace()
    case e: Exception =>
      println(e.getMessage)
      e.printStackTrace()
    case t: Throwable =>
      println("Throwable")
      println(t)
  } finally {
    println("finally block")
  }

}

object BreakMe extends App {

  import scala.util.control.Breaks.break
  import scala.util.control.Breaks.breakable

  val search = "peter piper picked a peck of pickled peppers"
  var numPs = 0
  for (i <- 0 until search.length) {
    breakable {
      if (search.charAt(i) != 'p') {
        break()
      } else {
        numPs += 1
      }
    }
  }
  println(s"Found $numPs p's in the string.")

  val countPs = search.count(_ == 'p')
  println(s"Found $countPs p's in the string.")
}


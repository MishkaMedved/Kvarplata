package Homework

object HomeworkThree {
  def main(args: Array[String]): Unit = {
    val circle = Circle(2.321)
    circle.square()
    val rectangle = Rectangle(15,2)
    rectangle.square()
    val  square = Square(4)
    square.square()
  }


  class Book(zagolovok: String, avtor: String, year: Int) {

  }

  object Book {
    def createBook(name: String, avtor: String, year: Int): Book = new Book(name, avtor, year)
  }

  trait Figure{
    def square()
  }
  case class Circle(radius: Double) extends Figure{
    import scala.math.pow
    override def square(): Unit = {
      val s = 3.14 * pow(radius,2)
      println(s)
    }
  }
  case class Rectangle(a:Int, b: Int) extends Figure{
    override def square(): Unit = {
      val s = a * b
      println(s)
    }
  }

  case class Square(a: Int) extends Figure{
    import scala.math.pow
    override def square(): Unit = {
      val s = pow(a,2)
      println(s)
    }
  }
}


package Homework

import scala.math.pow
import scala.util.{Failure, Success, Try}

object HomeworkFour extends App {

  // Фукнция для вычисления площади фигуры
  trait Figure {

  }

  case class Square(a: Int) extends Figure

  case class Circle(radius: Double) extends Figure

  case class Rectangle(a: Int, b: Int) extends Figure

  case class Triangle(a: Int, h: Int) extends Figure

  case class Rhombus(d1: Int, d2: Int) extends Figure

  case class Trapezium(a: Int, b: Int, h: Int) extends Figure


  def figureSquare(figure: Figure): Unit = {
    figure match {
      case Square(a) => println(s"Square 1: ${pow(a, 2)}")
      case Circle(radius) => println(s"Square 2: ${3.14 * pow(radius, 2)}")
      case Rectangle(a, b) => println(s"Square 3: ${a * b}")
      case Triangle(a, h) => println(s"Square 4: ${(a * h) / 2}")
      case Rhombus(d1, d2) => println(s"Square 5: ${(d1 * d2) / 2}")
      case Trapezium(a, b, h) => println(s"Square 6: ${((a + b) / 2) * h}")
      case _ => println("UNKNOWN")
    }
  }

  figureSquare(Square(9))
  figureSquare(Circle(5))
  figureSquare(Rectangle(3, 5))
  figureSquare(Triangle(2, 3))
  figureSquare(Rhombus(15, 3))
  figureSquare(Trapezium(6, 2, 3))

  // Функция для проверки содержания Option[String] на определенное слово
  def check(option: Option[String], word: String): String = {
    option.get match {
      case w if w.equals(word) => "Word  found"
      case _ => "Word not found"
    }
  }

  val o1: Option[String] = Option("джавист")
  println(check(o1, "джавист"))
  println(check(o1, "скалист"))

  // Функция сравнивает возраст человека
  def functuon(option: Option[Int]): String = {
    option.get match {
      case age if 14 > age => "child"
      case age if (14 < age) => "teenager"
      case age if 40 < age => "adult"
      case _ => "pensioner"
    }
  }

  val o2: Option[Int] = Option(17)
  println(functuon(o2))

  //Функция делящая а на b
  val a = 10
  val b = 5

  def divOption(a: Int, b: Int): Option[Double] = Option(a / b)

  def divTry(a: Int, b: Int): Try[Double] = Try(a / b)

  def divEither(a: Int, b: Int): Either[String, Double] = {
    if (b != 0) Right(a / b)
    else Left("Делить на ноль стыдно!")
  }

  divOption(a, b) match {
    case Some(value) => println(value)
    case None => println("Деление на ноль!")
  }

  divTry(a, b) match {
    case Success(value) => println(value)
    case Failure(exception) => println(exception)
  }

  divEither(a, b) match {
    case Left(error) => println(error)
    case Right(value)=> println(value)
  }

}

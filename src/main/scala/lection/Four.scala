package lection

import scala.util.Random

object Match extends App {
  val nextInt = Random.nextInt(10)
  val sstr = nextInt match {
    case 0 => "zero"
    case 1 => "one"
    case 2 => "two"
    case 3 => "three"
    case 4 => "four"
    case 5 => "five"
    case _ => "unknown"
  }
  println(sstr)
}

object CaseClassMatch extends App {
  trait Action

  case class Create(name: String, age: Int) extends Action

  case class Read(id: Long, maxSize: Int) extends Action

  case class Update(id: Long, newName: String) extends Action

  case class Delete(id: Long, ignoreWarning: Boolean) extends Action


  def processAction(ac: Action): Unit = {
    ac match {
      case Create(name, age) if name.isEmpty =>
        println("Ошибка отсутсвует имя")
      case Create(_, age) if (age < 18 || age > 65) =>
        println("Ошибка возраст меньше 18, ну или вы пенсионер")
      case Create(name, age) =>
        println(s"create with name: $name")
      case Delete(id, ignoreWarning) =>
        println(s"delete by id: $id, with ignore warning: $ignoreWarning")
      case Read(id, maxSize) =>
        println(s"read by id: $id, with max size: $maxSize")
      case Update(id, newName) =>
        println(s"update by id: $id, with new name: $newName")
      case _ =>
        println("unknown action")
    }
  }

  processAction(Delete(1L,ignoreWarning = true))


  def processActionV2(ac: Action): Unit ={
    ac match {
      case c: Create =>
        println(s"create with name: ${c.name}")
      case d: Delete =>
        println(s"delete by id: ${d.id}, with ignore warning: ${d.ignoreWarning}")
      case r: Read =>
        println(s"read by id: ${r.id}, with max size: ${r.maxSize}")
      case u: Update =>
        println(s"update by id: ${u.id}, with new name: ${u.newName}")
      case _ =>
        println("unknown action")
    }
  }

  processActionV2(Create("Vasya",99))
}

object SealedCaseClassMatch extends App {
  sealed trait Action // extends only in this life

  // check private trait
  case class Create(name: String, age: Int) extends Action

  case class Read(id: Long, maxSize: Int) extends Action

  case class Update(id: Long, newName: String) extends Action

  case class Delete(id: Long, ignoreWarning: Boolean) extends Action

  def processAction(ac: Action): String = {
     ac match {
       case Create(name,age) => "???"
       case Read(id,maxSize) => "???"
       case Update(id,newName) => "???"
       case Delete(id,ignoreWarning) => "???"
       // not exists "case _ => cause sealed trait
     }
  }

  processAction(Create("1",1))
}

object ScalaOption extends App  {
  // Option(any) => Some(any) or None

  val o1: Option[Int] = Option(1)         // Some(1)
  val o2: Option[Int] = None              // None
  val o3: Option[Int] = Option.empty[Int] // None
  val o4              = Option(null)      // None

  println(o1.isEmpty)   // true if None
  println(o1.isDefined) // !isEmpty
  println(o1.nonEmpty)  // isDefined

  println(o1.contains(1)) // check doc

  println(o1.filter(x => x > 0)) // Some(any) if f in filter return true
  println(o1.filterNot(_ < 0))   // Some(any) if f in filter return false
  println(o1.exists(_ > 1))      // true if f in f exists return true
  println(o1.forall(_ == 1))     // eq exists

  val o1Value = o1.get
  println(o1Value)
  val o1ValueSafe = o1.getOrElse(-1)
  println(o1ValueSafe)

  //val noneGet = o2.get // !!! java.util.NoSuchElementException: None.get

  def loadById(): String        = ""
  def loadByAnotherId(): String = ""

  val loadByOpt: Option[String] = Option(loadById())
    .orElse(Option(loadByAnotherId()))

  loadByOpt match {
    case Some(value) if value.isEmpty =>
      println(s"!$value!")
    case Some(value) =>
      println(value)
    case None =>
      println("loadByOpt None")
  }

  val optionoption = Option(Option("ha!"))
  println(optionoption)
  val optionoptionFlatten = optionoption.flatten
  // [["ha!"]] . flatten => ["ha!"]
  // [[["ha!"]]] . flatten => [["ha!"]] . flatten => ["ha!"]
  println(optionoptionFlatten)

  val optionNone = Option(Option.empty[String])
  println(optionNone)
  val optionNoneFlatten = optionNone.flatten
  println(optionNoneFlatten)

  println("-----------------")
  val o1Mapped = println(o1.map(_ * 10).map(_.toString))
  println("-----------------")
  val o2Mapped = o3
    .map { x =>
      println("o3.map")
      x * 10
    }
    .map { x =>
      println("o3.map2")
      x.toString
    }

  val o1Foreach = o1.foreach(println(_))

  case class ClassWithOpt(id: Long, name: Option[String])

  def loadCWO: Option[ClassWithOpt] = Option(ClassWithOpt(1, Option("Urza")))

  val cwoNameOpt1: Option[String] = loadCWO.map(_.name).flatten // eq loadCWO.flatMap(_.name)
  val cwoNameOpt2: Option[String] = loadCWO.flatMap(_.name)

  val cwoNameOpt = for {
    c    <- loadCWO
    name <- c.name
  } yield name

  val cwoNameOptV1: Option[(String, Option[String])] = for {
    ClassWithOpt(_, nOpt) <- loadCWO
    name                  <- nOpt
  } yield name -> nOpt
}

object ScalaTry extends App  {

  // Try(any) => Success(any) or Failure(exception)

  import scala.util.Try
  import scala.util.Failure
  import scala.util.Success

  case class Id(id: Int)

  def mayBeThrowException: Try[Id] = Try {
    throw new Exception("exception")
    //Id(1)
  }

  mayBeThrowException match {
    case Failure(exception) =>
      println(exception.getMessage)
    case Success(value) =>
      println(value)
  }

  val isSuccess = mayBeThrowException.isSuccess
  val isFailure = mayBeThrowException.isFailure

  //  val withGet       = mayBeThrowException.get
  val withGetOrElse = mayBeThrowException.getOrElse(Id(-1))

  val withMap: Try[Int] = mayBeThrowException.map(_.id)

  def mayBeThrowExceptionV2: Try[Id] = Try {
    //    throw new Exception("exception")
    Id(1)
  }

  val x: Try[Id] = mayBeThrowException.flatMap(_ => mayBeThrowExceptionV2)

  val filteredFail = mayBeThrowException.filter(_.id != 0)
  println(filteredFail) // Failure(java.lang.Exception: exception)
  val filteredSuc = mayBeThrowExceptionV2.filter(_.id != 0)
  println(filteredSuc) //Success(Id(1))
  val filteredFailByFilter = mayBeThrowExceptionV2.filter(_.id == 0)
  println(filteredFailByFilter) //Failure(java.util.NoSuchElementException: Predicate does not hold for Id(1))

  mayBeThrowException.foreach(println(_))

  val recoverFail = mayBeThrowException
    .map(_.id.toString)
    .recover { case _ =>
      "fail!"
    }
    .foreach(println(_)) //Success(fail!)

  val recoverSuc = mayBeThrowExceptionV2.map(_.id.toString).recover { case _ =>
    "fail!"
  }
  println(recoverSuc) //Success(1)

  def mayBeThrowExceptionV3(id: Id): Try[Id] = Try {
    Id(id.id + 1)
  }

  val res1 = mayBeThrowExceptionV2.flatMap(mayBeThrowExceptionV3)
  println(res1) // Success(Id(2))

  val res2 = for {
    id1 <- mayBeThrowExceptionV2
    id2 <- mayBeThrowExceptionV3(id1)
  } yield id2
  println(res2) // Success(Id(2))

}

object ScalaEither extends App  {
  def getInfoFromUrl(url: String): Either[String, Int] = //Прикольная тема возвращает 1 из 2 возможных типов
    if (url.isEmpty) Left("Empty url!")
    else Right(42)

  val res1 = getInfoFromUrl("AAA")
  println(res1) // Right(42)
  val res2 = getInfoFromUrl("")
  println(res2) // Left(Empty url!)

  getInfoFromUrl("BBB") match {
    case Left(er) => println(er)
    case Right(value) => println(value)
  }

  val res3 = getInfoFromUrl("BBB").map(_.toLong).getOrElse(-1L) // Использовал _ вместо x=> x считаю успех
  println(res3)
  println("---------------")
  val res4 = getInfoFromUrl("").map(_.toLong)
  println(res4)

  val res5 = getInfoFromUrl("AAA").right.map(_ + 1) // eq getInfoFromUrl("AAA").map(_ + 1)
  val res6 = getInfoFromUrl("BBB").left.map(s => s"!!! $s !!!") //s: String
  val res7 = getInfoFromUrl("").left.map(s => s"!!! $s !!!") //s: String


  val res8 = getInfoFromUrl("").left.getOrElse("")

  val res9 = getInfoFromUrl("AAA").flatMap { x =>
    getInfoFromUrl("BBB").map { y =>
      x + y
    }
  }
  val res10 = for {
    x <- getInfoFromUrl("AAA")
    y <- getInfoFromUrl("BBB")
  } yield x + y

  val toOptSome = getInfoFromUrl("sss").toOption
  println(toOptSome)
  val toOptNone = getInfoFromUrl("").toOption
  println(toOptNone)

  case class PersonalInfo(id: Long, contractNumber: String, phone: String)

  val infos = List(
    PersonalInfo(1, "42", "99"),
    PersonalInfo(2, "", "1"),
    PersonalInfo(3, "24", ""),
    PersonalInfo(4, "11", "22")
  )
  def insertToDB(pi: PersonalInfo): Unit = println(s"Insert to DB: $pi")

  val res: List[Either[String, Long]] = infos.map {
    case pi: PersonalInfo if pi.contractNumber.isEmpty =>
      Left(s"$pi => contractNumber isEmpty")
    case pi: PersonalInfo if pi.phone.isEmpty =>
      Left(s"$pi => phone isEmpty")
    case pi =>
      insertToDB(pi)
      Right(pi.id)
  }

  res.foreach(println(_))

}

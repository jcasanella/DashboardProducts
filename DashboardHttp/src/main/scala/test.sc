import scala.reflect.runtime._
import scala.reflect.runtime.universe._


case class CaseClass(i: Int, s: String)

// =:= left must be exactly the same as the right
typeOf[CaseClass].members.withFilter(!_.isMethod).map(_.typeSignature).foreach{
  t =>
    t match {
      case t if t =:= typeOf[Int] => println("int")
      case t if t =:= typeOf[String] => println("string")
      case _ => println("aaaaa")
    }
}
//  .foreach {
//  case T => print("i")
//  case S => print("s")
//}

def toTuple[A <: Object](as:List[A]):Product = {
  val tupleClass = Class.forName("scala.Tuple" + as.size)
  tupleClass.getConstructors.apply(0).newInstance(as:_*).asInstanceOf[Product]
}

val testVals = List(4, "aaaaa")
//CaseClass.tupled(toTuple(testVals))
//val instance = constructor.newInstance(args:_*).asInstanceOf[Foo]
val ru = scala.reflect.runtime.universe
val rm = ru.runtimeMirror(getClass.getClassLoader)
val typ = ru.typeOf[CaseClass]
val primary = typ.typeSymbol.asClass.primaryConstructor
println(primary.toString)

val tt = typeTag[CaseClass]
val cc = currentMirror.reflectClass(tt.tpe.typeSymbol.asClass).reflectConstructor(
  tt.tpe.members.filter(m =>
    m.isMethod && m.asMethod.isConstructor
  ).iterator.toSeq(0).asMethod
)(testVals: _*).asInstanceOf[CaseClass]

println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
println(cc.i)
println(cc.s)


case class TestJordi2(age: Int, big: Long, isHandsome: Boolean)
val cad = "true,1234789,25"
val tokens = cad.split(",")
val types = typeOf[TestJordi2].members.withFilter(!_.isMethod).map(_.typeSignature).toArray

//val attributes2 = types.flatMap { typ: Type =>
//  tokens.map { token: String =>
//    typ match {
////      case t if t =:= typeOf[Int] => token.trim.toInt
////      case t if t =:= typeOf[Boolean] => token.trim.toBoolean
////      case t if t =:= typeOf[Double] => token.trim.toDouble
////      case t if t =:= typeOf[Float] => token.trim.toFloat
////      case t if t =:= typeOf[Long] => token.trim.toLong
//      case _ => token
//    }
//  }
//}
//attributes2.foreach(println)

val attributes = for {
  idx <- 0 until types.size by 1

  typ: Type = types(idx)
  attr = typ match {
    case t if t =:= typeOf[Int] => tokens(idx).toInt
    case t if t =:= typeOf[Boolean] => tokens(idx).toBoolean
    case t if t =:= typeOf[Double] => tokens(idx).toDouble
    case t if t =:= typeOf[Float] => tokens(idx).toFloat
    case t if t =:= typeOf[Long] => tokens(idx).toLong
    case _ => tokens(idx)
  }
} yield attr

val elems = types zip tokens
val attributes2 = elems.map{ elem => 
  elem._1 match {
    case t if t =:= typeOf[Int] => elem._2.toInt
    case t if t =:= typeOf[Boolean] => elem._2.toBoolean
    case t if t =:= typeOf[Double] => elem._2.toDouble
    case t if t =:= typeOf[Float] => elem._2.toFloat
    case t if t =:= typeOf[Long] => elem._2.toLong
    case _ => elem._2
  }
}
attributes2.foreach(println)

def getParamTypes: Map[String, ru.Type] = {
  val clazz = getClass
  val tpe = ru.runtimeMirror(clazz.getClassLoader).classSymbol(clazz).toType
  tpe.
    member(ru.termNames.CONSTRUCTOR).
    asMethod.paramLists(0).
    foldRight(Map(): Map[String, ru.Type])((p, a) => {
      a + (p.name.decodedName.toString -> p.typeSignature)
    })
}

// Works!!!!!!
val rm2 = scala.reflect.runtime.universe.runtimeMirror(getClass.getClassLoader)
val typ2 = scala.reflect.runtime.universe.typeOf[TestJordi2]
val primary2 = typ2.typeSymbol.asClass.primaryConstructor
val sig2 = primary2.asMethod.paramLists(0).map(s => s.typeSignature)
sig2.foreach(println)

val tokens2 = "56,3535353535353535,true".split(",")
val elems3 = sig2 zip tokens2
val attributes3 = elems3.map{ elem =>
  val t = elem._1
  t match {
    case t if t =:= typeOf[Int] => elem._2.toInt
    case t if t =:= typeOf[Boolean] => elem._2.toBoolean
    case t if t =:= typeOf[Double] => elem._2.toDouble
    case t if t =:= typeOf[Float] => elem._2.toFloat
    case t if t =:= typeOf[Long] => elem._2.toLong
    case _ => elem._2
  }
}
attributes3.foreach(println)

val tt2 = typeTag[TestJordi2]
val cc2 = currentMirror.reflectClass(tt2.tpe.typeSymbol.asClass).reflectConstructor(
  tt2.tpe.members.filter(m =>
    m.isMethod && m.asMethod.isConstructor
  ).iterator.toSeq(0).asMethod
)(attributes3: _*).asInstanceOf[TestJordi2]

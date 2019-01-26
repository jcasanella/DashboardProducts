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

class Test[T <: MyTrait : TypeTag] {

  def createInstance(args: AnyRef*)(ctor: Int = 0): T = {
    val tt = typeTag[T]

    currentMirror.reflectClass(tt.tpe.typeSymbol.asClass).reflectConstructor(
      tt.tpe.members.filter(m =>
        m.isMethod && m.asMethod.isConstructor
      ).iterator.toSeq(ctor).asMethod
    )(args: _*).asInstanceOf[T]
  }
}
//.members.info.decls.filter(m => m.isMethod && m.asMethod.isPrimaryConstructor).head
package com.parser.csv

import scala.reflect.runtime.universe._

object ReflectionHelpers {

  implicit class CaseClassFactory[A <: Product](line: String)(
      implicit tag: WeakTypeTag[A]
  ) {

    def As[A <: Product]()(implicit tag: WeakTypeTag[A]): A = {
      val classLoaderMirror = runtimeMirror(getClass.getClassLoader)
      val args2: Seq[String] = line.split(",")

      // Obtain symbol for the class
      val tpe = tag.tpe
      val classSymbol = tpe.typeSymbol.asClass

      // Check if it's a case class
      if (!(tpe <:< typeOf[Product] && classSymbol.isCaseClass))
        throw new IllegalArgumentException(
          "CaseClassFactory only applies to case classes"
        )

      // Obtain class mirror used later to create the new instance
      val classMirror = classLoaderMirror.reflectClass(classSymbol)
      val ctor = tpe.typeSymbol.asClass.primaryConstructor

      // Get default constructor
      val defaultConstructor =
        if (ctor.isMethod) ctor.asMethod
        else {
          val ctors = ctor.asTerm.alternatives
          ctors.map { _.asMethod }.find { _.isPrimaryConstructor }.get
        }

      // Constructor and parameters
      val ctorMethod = classMirror.reflectConstructor(defaultConstructor)
      val ctorTypes = ctor.asMethod.paramLists(0).map(s => s.typeSignature)

      val ctorTypesValues = ctorTypes zip args2
      val params = ctorTypesValues.map { elem =>
        elem._1 match {
          case i if i =:= typeOf[Int]     => elem._2.toInt
          case b if b =:= typeOf[Boolean] => elem._2.toBoolean
          case d if d =:= typeOf[Double]  => elem._2.toDouble
          case f if f =:= typeOf[Float]   => elem._2.toFloat
          case l if l =:= typeOf[Long]    => elem._2.toLong
          case s if s =:= typeOf[Short]   => elem._2.toShort
          case _                          => elem._2
        }
      }

      ctorMethod(params: _*).asInstanceOf[A]
    }
  }
}

package com.parser.csv


import scala.reflect.runtime.universe._

object ReflectionHelpers extends ReflectionHelpers

trait ReflectionHelpers {
  protected val classLoaderMirror = runtimeMirror(getClass.getClassLoader)

  class CaseClassFactory[A <: Product](implicit tag: WeakTypeTag[A]) {
   // val tpe = typeOf[A]
    val tpe = tag.tpe
    val classSymbol = tpe.typeSymbol.asClass

    if (!(tpe <:< typeOf[Product] && classSymbol.isCaseClass))
      throw new IllegalArgumentException("CaseClassFactory only applies to case classes")

    val classMirror = classLoaderMirror.reflectClass(classSymbol)
    val ctor = tpe.typeSymbol.asClass.primaryConstructor

    val defaultConstructor =
      if (ctor.isMethod) ctor.asMethod
      else {
        val ctors = ctor.asTerm.alternatives
        ctors.map { _.asMethod }.find { _.isPrimaryConstructor }.get
      }

    val ctorMethod = classMirror.reflectConstructor(defaultConstructor)

    def buildWith(args: Seq[_])(implicit tag: WeakTypeTag[A]): A = ctorMethod(args: _*).asInstanceOf[A]
  }
}

/*

class OpsAs {

  def As[A: ClassTag]()(implicit tag: TypeTag[A]): A = {

    val args = Array("field1", "field2")
//    val types = typeTag[A]

    val ctor = implicitly[ClassTag[A]].runtimeClass.getConstructors.head
    val typesCtor = ctor.getParameterTypes

    val typeTokens = typesCtor zip args
    val attributes = typeTokens.map { typeToken =>
      typeToken._1.getName match {
        case "int" => 5
        case t if t =:= typeOf[Boolean] => true
        case t if t =:= typeOf[Double] => 234.5
        case t if t =:= typeOf[Float] => 2242.5f
        case t if t =:= typeOf[Long] => 242424l
        case _ => typeToken._2
      }
    }


    /*
    val primCtor = tag.tpe.typeSymbol.asClass.primaryConstructor
    val typesCtor = primCtor.asMethod.paramLists(0).map(s => s.typeSignature)


    val tokens = args
    val typesTokens = typesCtor zip tokens

    val attributes = typesTokens.map { typeToken =>
      typeToken._1 match {
        case t if t =:= typeOf[Int] => 5
        case t if t =:= typeOf[Boolean] => true
        case t if t =:= typeOf[Double] => 234.5
        case t if t =:= typeOf[Float] => 2242.5f
        case t if t =:= typeOf[Long] => 242424l
        case _ => typeToken._2
      }
    }

//    val tt = typeTag[A]
    currentMirror.reflectClass(tag.tpe.typeSymbol.asClass).reflectConstructor(
      tag.tpe.members.filter(m =>
        m.isMethod && m.asMethod.isConstructor
      ).iterator.toSeq(0).asMethod
    )(attributes: _*).asInstanceOf[A]

/*
    val tt = typeTag[A]
    currentMirror.reflectClass(tt.tpe.typeSymbol.asClass).reflectConstructor(
      tt.tpe.members.filter(m =>
        m.isMethod && m.asMethod.isConstructor
      ).iterator.toSeq(0).asMethod
    )(attributes: _*).asInstanceOf[A]*/

    /*currentMirror.reflectClass(tt.tpe.typeSymbol.asClass).reflectConstructor(
      tt.tpe.members.filter(m =>
        m.isMethod && m.asMethod.isConstructor
      ).iterator.toSeq(0).asMethod
    )(args: _*).asInstanceOf[A]*/


   /* val types = typeOf[A]

    val primCtor = types.typeSymbol.asClass.primaryConstructor
    val typesCtor = primCtor.asMethod.paramLists(0).map(s => s.typeSignature)


      val tokens = line.split(",")
    val typesTokens = typesCtor zip tokens

    val attributes = typesTokens.map { typeToken =>
      typeToken._1 match {
        case t if t =:= typeOf[Int] => typeToken._2.toInt
        case t if t =:= typeOf[Boolean] => typeToken._2.toBoolean
        case t if t =:= typeOf[Double] => typeToken._2.toDouble
        case t if t =:= typeOf[Float] => typeToken._2.toFloat
        case t if t =:= typeOf[Long] => typeToken._2.toLong
        case _ => typeToken._2
      }

    }

    val tt = typeTag[A]
    currentMirror.reflectClass(tt.tpe.typeSymbol.asClass).reflectConstructor(
      tt.tpe.members.filter(m =>
        m.isMethod && m.asMethod.isConstructor
      ).iterator.toSeq(0).asMethod
    )(attributes: _*).asInstanceOf[A]*/
    */
  }
}


*/
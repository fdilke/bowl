package com.fdilke.bowl.fsets

import com.fdilke.bowl.topos.{Topos, Wrappings}

import scala.language.higherKinds

object FiniteSets extends Topos with Wrappings[Traversable] {
  override val name: String = "Finite Sets"
  override type DOT[S] = FiniteSetsDot[S]
  override type >[S, T] = FiniteSetsArrow[S, T]
  override type UNIT = Unit
  override val I: DOT[UNIT] = new FiniteSetsDot(Seq(()))

  class FiniteSetsDot[S](
    val elements: Traversable[S]
  ) extends Dot[S] {

  }

  class FiniteSetsArrow[S, T](
    val elements: Traversable[S]
  ) extends Arrow[S, T] {

  }

  override def makeDotUncached[T](predot: Traversable[T]) =
    new FiniteSetsDot[T](predot)

}

//case class FiniteSetsPreArrow[S, T](
//  source: Traversable[S],
//  target: Traversable[T],
//  function: S => T
//)
//

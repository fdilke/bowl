package com.fdilke.bowl.topos

import scala.language.higherKinds

trait BaseTopos {
  val name: String
  type DOT[S] <: Dot[S]
  type >[S, T] <: Arrow[S, T]

  type UNIT
  val I : DOT[UNIT]

  trait BaseDot[S] { self: DOT[S] =>
//    val toI: S > UNIT
  }

  trait Dot[S] extends BaseDot[S] { self: DOT[S] =>

  }

  trait BaseArrow[S, T] { self: S > T =>
//    val toI: S > UNIT
  }

  trait Arrow[S, T] extends BaseArrow[S, T] { self: S > T =>

  }
}

trait Topos extends BaseTopos {

}

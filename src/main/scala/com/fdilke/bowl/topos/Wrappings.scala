package com.fdilke.bowl.topos

import com.fdilke.bowl.helper.Memoize
import scala.language.higherKinds

trait Wrappings[
  PREDOT[_]
] { topos: Topos =>

  def makeDotUncached[T](
    predot: PREDOT[T]
  ) : DOT[T]

  private val memoizedDotWrapper = {
    def wrap[T](elements: PREDOT[T]) =
      makeDotUncached(elements)
    Memoize generic wrap
  }

  final def makeDot[T](
    predot: PREDOT[T]
  ) : DOT[T] =
    memoizedDotWrapper(predot)

//  final def makeArrow[S, T](
//     prearrow: PREARROW[S, T]
//   ) : S > T

//  def makeArrowUncached[S, T](
//     prearrow: PREARROW[S, T]
//   ) : S > T

}

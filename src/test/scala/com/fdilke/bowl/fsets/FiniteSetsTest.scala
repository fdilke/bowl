package com.fdilke.bowl.fsets

import com.fdilke.bowl.topos.{GenericToposTests, Topos}
import org.scalatest.Matchers._
import com.fdilke.bowl.fsets.FiniteSetsUtilities.dot

import scala.language.higherKinds

class FiniteSetsTest extends GenericToposTests {
  override val topos = FiniteSets
  override type FOO = Boolean
  override val foo = dot(true, false)
}

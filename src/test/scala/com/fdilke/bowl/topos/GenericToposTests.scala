package com.fdilke.bowl.topos

import org.scalatest.FunSpec
import org.scalatest.Matchers._

abstract class GenericToposTests extends FunSpec {
  val topos : Topos
  type FOO

  import topos.DOT
  val foo: DOT[FOO]

  import topos.I

//  describe(s"The topos ${topos.name}") {
  describe(s"The topos topos.name") {
    it("has nontrivial fixtures with sane equality semantics") {
      foo should not be I
      foo shouldBe foo
    }
  }
}

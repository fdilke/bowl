package com.fdilke.bowl.helper

import org.scalatest.FunSpec
import org.scalatest.Matchers._
import scala.language.higherKinds

class MemoizeTest extends FunSpec {

  private case class Input[T](seq: Seq[T])
  private case class Output[T](seq: Seq[T])

  private class CounterizeGeneric[I[T], O[T]](func: I[_] => O[_]) {
    private var count = 0

    def check(verify: => Unit) {
      count shouldBe 0
      verify
      count shouldBe 1
      verify
      count shouldBe 1
    }

    def apply[U](i: I[U]): O[U] = {
      count += 1
      func(i.asInstanceOf[I[_]]).asInstanceOf[O[U]]
    }
  }

  describe("A memoized function") {

    it("caches its results, with a generic parameter") {
      def testFunc(input: Input[_]): Output[_] =
        new Output(input.seq ++ input.seq)

      val counter = new CounterizeGeneric[Input, Output](testFunc)

      def instrumentedFunc[T](input: Input[T]): Output[T] =
        counter(input)

      val memoizedFunc = Memoize.generic(instrumentedFunc)

      counter.check {
        memoizedFunc(
          new Input[Int](Seq(2))
        ).seq shouldBe Seq(2,2)
      }
    }

    it("still works with structural types") {
      type Widget[T] = Seq[Option[T]]

      def testFunc[T](input: Seq[_]): Widget[_] =
        Seq(input.headOption, None)

      val counter = new CounterizeGeneric[Seq, Widget](testFunc)

      def instrumentedFunc[T](input: Seq[T]): Widget[T] =
        counter(input)

      val memoizedFunc = Memoize.generic(instrumentedFunc)

      counter.check {
        memoizedFunc(
          Seq(2)
        ) shouldBe Seq(Some(2), None)
      }
    }
  }
}



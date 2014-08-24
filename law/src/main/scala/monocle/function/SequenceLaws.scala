package monocle.function

import monocle.{OptionalLaws, TraversalLaws, IsoLaws, PrismLaws}
import monocle.function._
import org.scalacheck.Prop._
import org.scalacheck.{Properties, Arbitrary}
import scalaz.std.tuple._
import scalaz.syntax.equal._
import scalaz.std.anyVal._
import scalaz.std.list._

import scalaz.Equal

/**
 * Laws that Optics for a sequence like data structure should satisfy
 */
object SequenceLaws {

  def apply[S, A](implicit aEq: Equal[A], aArb: Arbitrary[A],
                           sEq: Equal[S], sArb: Arbitrary[S],
                           evEmpty: Empty[S],
                           evReverse: Reverse[S, S],
                           evCons: Cons[S, A],
                           evSnoc: Snoc[S, A],
                           evEach: Each[S, A],
                           evIndex: Index[S, Int, A],
                           evFilterIndex: FilterIndex[S, Int, A]) = new Properties("Sequence") {

    include(IsoLaws(reverse[S, S]))
    include(PrismLaws(empty[S]))
    include(PrismLaws(cons[S, A]))
    include(PrismLaws(snoc[S, A]))
    include(OptionalLaws(index(2)))
    include(TraversalLaws(filterIndex[S, Int, A](_ % 2 == 0)))
    include(TraversalLaws(each[S, A]))


    property("cons == snoc . reverse") = forAll { as: List[A] =>
      as.foldRight(_empty[S])(_cons) === as.foldLeft(_empty[S])(_snoc)
    }

  }

}

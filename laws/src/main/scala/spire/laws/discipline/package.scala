package spire.laws

import spire.algebra.Eq
import spire.laws.shadows.{Shadow, Shadowing}

import org.scalacheck.{Arbitrary, Prop}

package object discipline {

  implicit def spireLawsIsEqToProp[A](isEq: IsEq[A])(implicit ev: Eq[A]): Prop =
    isEq.fold((x, y) => Prop(ev.eqv(x, y)), Prop.passed)

  implicit def spireLawsArbitraryShadow[A, S](implicit A: Arbitrary[A], S: Shadowing[A, S]): Arbitrary[Shadow[A, S]] =
    Arbitrary { A.arbitrary.map( a => Shadow(a, S.toShadow(a)) ) }
}

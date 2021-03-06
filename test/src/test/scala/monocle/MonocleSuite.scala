package monocle

import monocle.function.GenericOptics
import monocle.generic.GenericInstances
import monocle.refined.RefinedInstances
import monocle.state.StateLensSyntax
import monocle.std.StdInstances
import monocle.syntax.Syntaxes
import org.scalatest.{FunSuite, Matchers}
import org.typelevel.discipline.scalatest.Discipline

trait MonocleSuite extends FunSuite
                      with Discipline
                      with Matchers
                      with TestInstances
                      with StdInstances
                      with GenericOptics
                      with GenericInstances
                      with RefinedInstances
                      with Syntaxes
                      with StateLensSyntax
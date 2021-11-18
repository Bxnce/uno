package de.htwg.se.uno
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import CardLayout._
class CardLayoutSpec extends AnyWordSpec {
  "CardLayout" should {
    "have a eol as String of form'\\n'" in {
      eol should be("\n")
    }
    "have a row as String of form '+--'" in {
      row should be("+--")
    }
    "have a rowEnd as String of form '+'" in {
      rowEnd should be("+" + eol)
    }
    "have a scalable udRow" in {
      udRow(1) should be("+--+" + eol)
      udRow(2) should be("+--+--+" + eol)
      udRow(3) should be("+--+--+--+" + eol)
    }
  }
}

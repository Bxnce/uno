package de.htwg.se.uno

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import Field._

class FieldSpec extends AnyWordSpec {
  "Field" should {
    "have a lb as String of form'lblb'" in {
      lb() should be("\n\n")
    }
    "have a row as String of form '+--'" in {
      row() should be("+--")
    }

    "have a rowEnd as String of form '+'" in {
      rowEnd() should be("+\n")
    }

    "have a mid as String of form '|  '" in {
      mid() should be("|  ")
    }

    "have a midEnd as String of form '|'" in {
      midEnd() should be("|\n")
    }

    "have a udRow as String of form '+--+'" in {
      udRow() should be("+--+\n")
    }

    "have a midRow as String of form '|  |'" in {
      midRow() should be("|  |\n")
    }

    "have a finalCard as String of form '\n+--+\n|  |\n+--+'" in {
      finalCard() should be("+--+\n|  |\n+--+\n")
    }

    "have a scalable udRow" in {
      udRow(1) should be("+--+\n")
      udRow(2) should be("+--+--+\n")
      udRow(3) should be("+--+--+--+\n")
    }

    "have a scalable midRow" in {
      midRow(1) should be("|  |\n")
      midRow(2) should be("|  |  |\n")
      midRow(3) should be("|  |  |  |\n")
    }

    "have a scalable finalCard" in {
      finalCard(1) should be("+--+\n|  |\n+--+\n")
      finalCard(2) should be("+--+--+\n|  |  |\n+--+--+\n")
      finalCard(3) should be("+--+--+--+\n|  |  |  |\n+--+--+--+\n")
    }

    "have a stack" in {
      stack() should be("+--+\n|  |\n+--+\n")
    }

    /*"have a scalable table" in {
      table(1, 1) should be(
        "+--+\n|  |\n+--+\n\n\n+--+\n|  |\n+--+\n\n\n+--+\n|  |\n+--+\n"
      )
      table(2, 2) should be(
        "+--+--+\n|  |  |\n+--+--+\n\n\n+--+\n|  |\n+--+\n\n\n+--+--+\n|  |  |\n+--+--+\n"
      )
      table(3, 3) should be(
        "+--+--+--+\n|  |  |  |\n+--+--+--+\n\n\n+--+\n|  |\n+--+\n\n\n+--+--+--+\n|  |  |  |\n+--+--+--+\n"
      )
    }*/
  }
}

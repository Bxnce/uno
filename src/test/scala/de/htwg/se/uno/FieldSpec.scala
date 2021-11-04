package de.htwg.se.uno

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import Field._

class FieldSpec extends AnyWordSpec {
  "Field" should {
    "have a lb as String of form'\\n'" in {
      eol should be("\n")
    }
    "have a row as String of form '+--'" in {
      row should be("+--")
    }
    "have a rowEnd as String of form '+'" in {
      rowEnd should be("+" + eol)
    }
    "have a mid as String of form '|  '" in {
      mid should be("|  ")
    }
    "have a midEnd as String of form '|'" in {
      midEnd should be("|" + eol)
    }
    "have a udRow as String of form '+--+'" in {
      udRow() should be("+--+" + eol)
    }
    "have a midRow as String of form '|  |'" in {
      midRow() should be("|  |" + eol)
    }
    "have a finalCard as String of form '\n+--+\n|  |\n+--+'" in {
      finalCard() should be(
        "+--+" + eol +
          "|  |" + eol +
          "+--+" + eol
      )
    }
    "have a scalable udRow" in {
      udRow(1) should be("+--+" + eol)
      udRow(2) should be("+--+--+" + eol)
      udRow(3) should be("+--+--+--+" + eol)
    }
    "have a scalable midRow" in {
      midRow(1) should be("|  |" + eol)
      midRow(2) should be("|  |  |" + eol)
      midRow(3) should be("|  |  |  |" + eol)
    }
    "have a scalable finalCard" in {
      finalCard(1) should be(
        "+--+\n" +
          "|  |\n" +
          "+--+\n"
      )
      finalCard(2) should be(
        "+--+--+\n" +
          "|  |  |\n" +
          "+--+--+\n"
      )
      finalCard(3) should be(
        "+--+--+--+\n" +
          "|  |  |  |\n" +
          "+--+--+--+\n"
      )
    }
    "have a stack" in {
      stack() should be(
        "+--+\n" +
          "|  |\n" +
          "+--+\n"
      )
    }
    "have a scalable table" in {
      table("Player1", "Player2", 1, 1) should be(
        "Player1" + eol +
          "+--+" + eol +
          "|  |" + eol +
          "+--+" + eol * 3 +
          "+--+" + eol +
          "|  |" + eol +
          "+--+" + eol * 3 +
          "+--+" + eol +
          "|  |" + eol +
          "+--+" + eol +
          "Player2" + eol
      )
      table("Player1", "Player2", 2, 2) should be(
        "Player1" + eol +
          "+--+--+" + eol +
          "|  |  |" + eol +
          "+--+--+" + eol * 3 +
          "+--+" + eol +
          "|  |" + eol +
          "+--+" + eol * 3 +
          "+--+--+" + eol +
          "|  |  |" + eol +
          "+--+--+" + eol +
          "Player2" + eol
      )
      table("Player1", "Player2", 3, 3) should be(
        "Player1" + eol +
          "+--+--+--+" + eol +
          "|  |  |  |" + eol +
          "+--+--+--+" + eol * 3 +
          "+--+" + eol +
          "|  |" + eol +
          "+--+" + eol * 3 +
          "+--+--+--+" + eol +
          "|  |  |  |" + eol +
          "+--+--+--+" + eol +
          "Player2" + eol
      )
    }
  }
}

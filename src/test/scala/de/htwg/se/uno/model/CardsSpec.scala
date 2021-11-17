package de.htwg.se.uno
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class CardsSpec extends AnyWordSpec {
  "Cards" should {
    import Card._
    import CardColor._
    import CardValue._
    "be one of 4 colors (Red, Blue, Green, Yellow) and have a value between 0 and 9" in {
      val red: Array[Card] = Card.values.filter(c => c.getColor == Red)
      red.size should be(10)
      red.map(c => c.getValue).toSet shouldBe Set(
        Zero,
        One,
        Two,
        Three,
        Four,
        Five,
        Six,
        Seven,
        Eight,
        Nine
      )
      val blue: Array[Card] = Card.values.filter(c => c.getColor == Blue)
      blue.size should be(10)
      blue.map(c => c.getValue).toSet shouldBe Set(
        Zero,
        One,
        Two,
        Three,
        Four,
        Five,
        Six,
        Seven,
        Eight,
        Nine
      )
      val green: Array[Card] = Card.values.filter(c => c.getColor == Green)
      green.size should be(10)
      green.map(c => c.getValue).toSet shouldBe Set(
        Zero,
        One,
        Two,
        Three,
        Four,
        Five,
        Six,
        Seven,
        Eight,
        Nine
      )
      val yellow: Array[Card] = Card.values.filter(c => c.getColor == Yellow)
      yellow.size should be(10)
      yellow.map(c => c.getValue).toSet shouldBe Set(
        Zero,
        One,
        Two,
        Three,
        Four,
        Five,
        Six,
        Seven,
        Eight,
        Nine
      )
    }
    "have a method getColor that returns the color of the card" in {
      R0.getColor should be(CardColor.Red)
      B0.getColor should be(CardColor.Blue)
      G0.getColor should be(CardColor.Green)
      Y0.getColor should be(CardColor.Yellow)
    }
    "have a method getValue that returns the color of the card" in {
      R0.getValue should be(CardValue.Zero)
      B0.getValue should be(CardValue.Zero)
      G0.getValue should be(CardValue.Zero)
      Y0.getValue should be(CardValue.Zero)
      R1.getValue should be(CardValue.One)
    }
    "have its own toString Method that prints it's ID" in {
      R0.toString should be("R0")
      B0.toString should be("B0")
      G0.toString should be("G0")
      Y0.toString should be("Y0")
    }
    "have a method getCard(String) that converts a String into a card" in {
      toCard.getCard("R0") should be(R0)
      toCard.getCard("B9") should be(B9)
      toCard.getCard("G4") should be(G4)
      toCard.getCard("Y6") should be(Y6)

      toCard.getCard("R13") should be(XX)
      toCard.getCard("Hallo") should be(XX)
    }
  }
}

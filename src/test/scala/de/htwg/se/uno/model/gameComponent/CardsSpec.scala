package de.htwg.se.uno
package model.gameComponent

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
      val error = (XX)
      error.getColor shouldBe (CardColor.ErrorC)
      error.getValue shouldBe (CardValue.Error)
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
      toCard.getCard("R1") should be(R1)
      toCard.getCard("R2") should be(R2)
      toCard.getCard("R3") should be(R3)
      toCard.getCard("R4") should be(R4)
      toCard.getCard("R5") should be(R5)
      toCard.getCard("R6") should be(R6)
      toCard.getCard("R7") should be(R7)
      toCard.getCard("R8") should be(R8)
      toCard.getCard("R9") should be(R9)

      toCard.getCard("B0") should be(B0)
      toCard.getCard("B1") should be(B1)
      toCard.getCard("B2") should be(B2)
      toCard.getCard("B3") should be(B3)
      toCard.getCard("B4") should be(B4)
      toCard.getCard("B5") should be(B5)
      toCard.getCard("B6") should be(B6)
      toCard.getCard("B7") should be(B7)
      toCard.getCard("B8") should be(B8)
      toCard.getCard("B9") should be(B9)

      toCard.getCard("G0") should be(G0)
      toCard.getCard("G1") should be(G1)
      toCard.getCard("G2") should be(G2)
      toCard.getCard("G3") should be(G3)
      toCard.getCard("G4") should be(G4)
      toCard.getCard("G5") should be(G5)
      toCard.getCard("G6") should be(G6)
      toCard.getCard("G7") should be(G7)
      toCard.getCard("G8") should be(G8)
      toCard.getCard("G9") should be(G9)

      toCard.getCard("Y0") should be(Y0)
      toCard.getCard("Y1") should be(Y1)
      toCard.getCard("Y2") should be(Y2)
      toCard.getCard("Y3") should be(Y3)
      toCard.getCard("Y4") should be(Y4)
      toCard.getCard("Y5") should be(Y5)
      toCard.getCard("Y6") should be(Y6)
      toCard.getCard("Y7") should be(Y7)
      toCard.getCard("Y8") should be(Y8)
      toCard.getCard("Y9") should be(Y9)

      toCard.getCard("R13") should be(XX)
      toCard.getCard("Hallo") should be(XX)
    }
  }
}

package de.htwg.se.uno
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

import Card._

class PlayerSpec extends AnyWordSpec {
  "Player" should {
    "have name in form of a String" in {
      val p1 = Player("Spieler1", Vector[Card]())
      p1.name shouldBe ("Spieler1")
    }
    "have a Vector with the Players Cards" in {
      val p1 = Player("Spieler1", Vector[Card]())
      p1.karten.size shouldBe (0)
      val p2 = Player("Spieler2", Vector[Card](R0))
      p2.karten.size shouldBe (1)
      /*p1.add(R0)
      p1.karten.size shouldBe (1)
      p1.karten(0) shouldBe (R0)
       */
    }
    "have a method print() that prints out the Players Cards" in {
      val p1 = Player("Spieler1", Vector[Card]())
      p1.print() shouldEqual (
        "+--+\n" +
          "|  |\n" +
          "+--+\n"
      )
      val p2 = Player("Spieler2", Vector[Card](R0))
      p2.print() shouldEqual (
        "+--+\n" +
          "|R0|\n" +
          "+--+\n"
      )
      val p3 = Player("Spieler2", Vector[Card](R0, Y9))
      p3.print() shouldEqual (
        "+--+--+\n" +
          "|R0|Y9|\n" +
          "+--+--+\n"
      )
    }
    "have a method printFiller() that prints a Card with the number of cards of the player" in {
      val p1 = Player("Spieler1", Vector[Card]())
      p1.printFiller() shouldBe (
        "+--+\n" +
          "| 0|\n" +
          "+--+\n"
      )
      val p2 = Player("Spieler2", Vector[Card](R0))
      p2.printFiller() shouldBe (
        "+--+\n" +
          "| 1|\n" +
          "+--+\n"
      )
      val p3 =
        Player("Spieler2", Vector[Card](R0, R1, R2, R3, R4, R5, R6, R7, R8, R9))
      p3.printFiller() shouldBe (
        "+--+\n" +
          "|10|\n" +
          "+--+\n"
      )
    }

    "have a method removeInd(Integer) that removes a card at aspecific index from the players hand" in {
      var p1 = Player("p1", Vector[Card](Y0, Y1, Y2, Y3))
      p1.karten.size shouldBe (4)

      p1 = p1.removeInd(0)
      p1.karten.size shouldBe (3)
      p1.karten(0) shouldBe (Y1)

      p1 = p1.removeInd(2)
      p1.karten.size shouldBe (2)
      p1.karten(0) shouldBe (Y1)
      p1.karten(1) shouldBe (Y2)

      var p2 = Player("p2", Vector[Card](G0, G1, G2, G3, G4))
      p2.karten.size shouldBe (5)

      p2 = p2.removeInd(2)
      p2.karten.size shouldBe (4)
      p2.karten(2) shouldBe (G3)

      p2 = p2.removeInd(1)
      p2.karten.size shouldBe (3)
      p2.karten(1) shouldBe (G3)

      p2 = p2.removeInd(1)
      p2.karten.size shouldBe (2)
      p2.karten(0) shouldBe (G0)
      p2.karten(1) shouldBe (G4)

    }
    "have a method getName() that returns the name of the Player" in {
      val p1 = Player("Spieler1", Vector[Card]())
      p1.getName() shouldBe ("Spieler1")
    }
    "have a method add(Card) that adds a card to the Vector karten" in {
      //sehr unschön wenn man vals nimmt
      val p1 = Player("Spieler1", Vector[Card]())
      p1.karten.size shouldBe (0)
      val p2 = p1.add(R0)
      p2.karten.size shouldBe (1)
      p2.karten(0) shouldBe (R0)
      val p3 = p2.add(Y9)
      p3.karten.size shouldBe (2)
      p3.karten(1) shouldBe (Y9)
    }
  }
}

package de.htwg.se.uno
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

import Card._

class PlayerSpec extends AnyWordSpec {
  "Player" should {
    "have name in form of a String" in {
      val p1 = Player("Spieler1")
      p1.name shouldBe ("Spieler1")
    }
    "have a Vector with the Players Cards" in {
      val p1 = Player("Spieler1")
      p1.karten.size shouldBe (0)
      p1.add(R0)
      p1.karten.size shouldBe (1)
      p1.karten(0) shouldBe (R0)
    }
    "have a method print() that prints out the Players Cards" in {
      val p1 = Player("Spieler1")
      p1.print() shouldEqual (
        "+--+\n" +
          "|  |\n" +
          "+--+\n"
      )
      p1.add(R0)
      p1.print() shouldEqual (
        "+--+\n" +
          "|R0|\n" +
          "+--+\n"
      )
      p1.add(Y9)
      p1.print() shouldEqual (
        "+--+--+\n" +
          "|R0|Y9|\n" +
          "+--+--+\n"
      )
    }
    "have a method printFiller() that prints a Card with the number of cards of the player" in {
      val p1 = Player("Spieler1")
      p1.printFiller() shouldBe (
        "+--+\n" +
          "| 0|\n" +
          "+--+\n"
      )
      p1.add(R0)
      p1.printFiller() shouldBe (
        "+--+\n" +
          "| 1|\n" +
          "+--+\n"
      )
      p1.add(R1)
      p1.add(R2)
      p1.add(R3)
      p1.add(R4)
      p1.add(R5)
      p1.add(R6)
      p1.add(R7)
      p1.add(R8)
      p1.add(R9)
      p1.printFiller() shouldBe (
        "+--+\n" +
          "|10|\n" +
          "+--+\n"
      )
    }

    "have a method removeInd(Integer) that removes a card at aspecific index from the players hand" in {
      val p1 = Player("p1")
      p1.add(Y0)
      p1.add(Y1)
      p1.add(Y2)
      p1.add(Y3)
      p1.karten.size shouldBe (4)

      p1.removeInd(0)
      p1.karten.size shouldBe (3)
      p1.karten(0) shouldBe (Y1)

      p1.removeInd(2)
      p1.karten.size shouldBe (2)
      p1.karten(0) shouldBe (Y1)
      p1.karten(1) shouldBe (Y2)

      val p2 = Player("p2")
      p2.add(G0)
      p2.add(G1)
      p2.add(G2)
      p2.add(G3)
      p2.add(G4)
      p2.karten.size shouldBe (5)

      p2.removeInd(2)
      p2.karten.size shouldBe (4)
      p2.karten(2) shouldBe (G3)

      p2.removeInd(1)
      p2.karten.size shouldBe (3)
      p2.karten(1) shouldBe (G3)

      p2.removeInd(1)
      p2.karten.size shouldBe (2)
      p2.karten(0) shouldBe (G0)
      p2.karten(1) shouldBe (G4)

    }
    "have a method getName() that returns the name of the Player" in {
      val p1 = Player("Spieler1")
      p1.getName() shouldBe ("Spieler1")
    }
    "have a method add(Card) that adds a card to the Vector karten" in {
      val p1 = Player("Spieler1")
      p1.karten.size shouldBe (0)
      p1.add(R0)
      p1.karten.size shouldBe (1)
      p1.karten(0) shouldBe (R0)
      p1.add(Y9)
      p1.karten.size shouldBe (2)
      p1.karten(1) shouldBe (Y9)
    }
  }
}

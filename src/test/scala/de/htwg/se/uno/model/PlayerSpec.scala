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
      p1.print() shouldBe (
        "+--+\n" +
          "|  |\n" +
          "+--+\n"
      )
      p1.add(R0)
      p1.print() shouldBe (
        "+--+\n" +
          "|R0|\n" +
          "+--+\n"
      )
      p1.add(Y9)
      p1.print() shouldBe (
        "+--+--+\n" +
          "|R0|Y9|\n" +
          "+--+--+\n"
      )
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

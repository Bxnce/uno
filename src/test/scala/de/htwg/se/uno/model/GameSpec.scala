package de.htwg.se.uno
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

import Player._
import toCard._
import CardLayout._
import Card._

class GameSpec extends AnyWordSpec {
  "Game" should {
    "have a method add(String, String) that adds a card to a players hand" in {
      val game = Game("player1", "player2", 0)
      game.p1.karten.size shouldBe (0)
      game.p2.karten.size shouldBe (0)
      game.add("p1", "R0")
      game.p1.karten.size shouldBe (1)
      game.p2.karten.size shouldBe (0)
      game.p1.karten(0) shouldBe (R0)
      game.add("p2", "G7")
      game.p1.karten.size shouldBe (1)
      game.p2.karten.size shouldBe (1)
      game.p2.karten(0) shouldBe (G7)
      //die ganzen Abfragen prüfen
      game.add("p2", "XX") shouldEqual (-1)
      game.add("p1", "R1")
      game.add("p1", "R1")
      game.add("p1", "R1") shouldEqual (-2)
      game.add("p3", "G8") shouldEqual (-3)
      game.cardStack.cards(R2) shouldBe (2)
      game.add("p1", "R2")
      game.cardStack.cards(R2) shouldBe (1)
    }
    "have a method add(String, Card) that adds a card to a players hand" in {
      val game = Game("player1", "player2", 0)
      game.p1.karten.size shouldBe (0)
      game.p2.karten.size shouldBe (0)
      game.midCard.karten.size shouldBe (1)

      game.add("p1", XX)
      game.p1.karten.size shouldBe (1)

      game.add("p2", R0) shouldEqual (0)
      game.p2.karten.size shouldBe (1)

      game.add("midstack", R0)
      game.midCard.karten.size shouldBe (2) //es liegen dann halt immer mehrere Karten auf dem Stapel

      game.add("p3", "G8") shouldEqual (-3)
    }
    "have a method take(String) that adds a random card to a players hand" in {
      val game = Game("player1", "player2", 0)
      game.p1.karten.size shouldBe (0)
      game.p2.karten.size shouldBe (0)

      game.take("p1")
      game.p1.karten.size shouldBe (1)

      game.take("p2")
      game.p2.karten.size shouldBe (1)
    }
    "have a method take() that adds a random card to the player whos turn it is" in {
      val game = Game("player1", "player2", 0)
      game.p1.karten.size shouldBe (0)
      game.p2.karten.size shouldBe (0)
      game.playerDiff shouldBe (0)

      game.take()
      game.p1.karten.size shouldBe (1)

      game.next()
      game.playerDiff shouldBe (1)
      game.take()
      game.p2.karten.size shouldBe (1)
    }
    //place funktioniert nach next nicht mehr richtig muss noch getestet werden
    "have a method next() that increments the PlayerDiff variable" in {
      val game = Game("player1", "player2", 0)
      game.playerDiff shouldBe (0)
      game.next()
      game.playerDiff shouldBe (1)
      game.next()
      game.playerDiff shouldBe (2)
    }
    "override the method toString() and return a String in Form of" in {
      val game = Game("player1", "player2", 0)
      game.midCard.karten = game.midCard.karten.updated(0, R0)
      game.toString() shouldBe (
        "player1" + eol +
          "+--+" + eol +
          "|  |" + eol +
          "+--+" + eol +
          eol +
          "+--+" + eol +
          "|R0|" + eol +
          "+--+" + eol +
          eol +
          "+--+" + eol +
          "|  |" + eol +
          "+--+" + eol +
          "player2" + eol
      )

      val game1 = Game("player1", "player2", 1)
      game1.midCard.karten = game1.midCard.karten.updated(0, R0)
      game1.p1.karten = game1.p1.karten.updated(0, B0)
      game1.p2.karten = game1.p2.karten.updated(0, G0)
      game1.toString() shouldBe (
        "player1" + eol +
          "+--+" + eol +
          "|B0|" + eol +
          "+--+" + eol +
          eol +
          "+--+" + eol +
          "|R0|" + eol +
          "+--+" + eol +
          eol +
          "+--+" + eol +
          "|G0|" + eol +
          "+--+" + eol +
          "player2" + eol
      )
    }
  }
}

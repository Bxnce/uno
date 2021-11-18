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
    "should create a game with 2 Players and one Card in the middle" in {
      val g1 = Game("player1", "player2", 4)
      g1.playerDiff shouldBe (3)
      g1.midCard.karten.size shouldBe (1)
      g1.p1.karten.size shouldBe (4)
      g1.p2.karten.size shouldBe (4)
    }
    "have a method add(String, String) that adds a card to a players hand" in {
      val game = Game("player1", "player2", 0)
      //dafür sorgen dass R2 nicht auf dem Spielstapel liegt
      val tmp = game.midCard.karten(0)
      game.add("midstack", Y9)
      game.midCard.removeInd(0)
      game.cardStack.cards =
        game.cardStack.cards + (tmp -> ((game.cardStack.cards(tmp) + 1)))
      game.midCard.karten.size shouldBe (1)
      game.midCard.karten(0) shouldBe (Y9)

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
      val game1 = Game("player1", "player2", 0)
      game1.p1.karten.size shouldBe (0)
      game1.p2.karten.size shouldBe (0)
      game1.midCard.karten.size shouldBe (1)

      game1.add("p1", XX)
      game1.p1.karten.size shouldBe (1)

      game1.add("p2", R0) shouldEqual (0)
      game1.p2.karten.size shouldBe (1)

      game1.add("midstack", R0)
      game1.midCard.karten.size shouldBe (2) //es liegen dann halt immer mehrere Karten auf dem Stapel

      game1.add("p3", "G8") shouldEqual (-3)
    }
    "have a method take(String) that adds a random card to a players hand" in {
      val game2 = Game("player1", "player2", 0)
      game2.p1.karten.size shouldBe (0)
      game2.p2.karten.size shouldBe (0)

      game2.take("p1")
      game2.p1.karten.size shouldBe (1)

      game2.take("p2")
      game2.p2.karten.size shouldBe (1)
    }
    "have a method take() that adds a random card to the player whos turn it is" in {
      val game3 = Game("player1", "player2", 0)
      game3.p1.karten.size shouldBe (0)
      game3.p2.karten.size shouldBe (0)
      game3.playerDiff shouldBe (3)

      game3.next()
      game3.playerDiff shouldBe (4)
      game3.take()
      game3.p1.karten.size shouldBe (1)

      game3.next()
      game3.playerDiff shouldBe (5)
      game3.next()
      game3.playerDiff shouldBe (6)
      game3.take()
      game3.p2.karten.size shouldBe (1)
    }
    "have a method place(Integer) that places a card onto the stack" in {
      val game4 = Game("player1", "player2", 1)
      game4.playerDiff shouldBe (3)
      game4.midCard.karten = game4.midCard.karten.updated(0, R0)
      game4.p1.karten = game4.p1.karten.updated(0, B0)
      game4.p2.karten = game4.p2.karten.updated(0, G0)
      game4.p1.karten.size shouldBe (1)
      game4.p2.karten.size shouldBe (1)
      game4.midCard.karten.size shouldBe (1)

      game4.next()
      game4.playerDiff shouldBe (4)
      game4.place(0)
      game4.p1.karten.size shouldBe (0)
      game4.p2.karten.size shouldBe (1)
      game4.midCard.karten.size shouldBe (1)

      game4.next()
      game4.playerDiff shouldBe (5)
      game4.next()
      game4.playerDiff shouldBe (6)
      game4.place(0)
      game4.p1.karten.size shouldBe (0)
      game4.p2.karten.size shouldBe (0)
      game4.midCard.karten.size shouldBe (1)

      game4.next()
      game4.place(1) shouldBe (-4)

      val game5 = Game("player1", "player2", 3)
      game5.midCard.karten = game5.midCard.karten.updated(0, R0)
      game5.p1.karten = game5.p1.karten.updated(0, B0)
      game5.p1.karten = game5.p1.karten.updated(1, B1)
      game5.p1.karten = game5.p1.karten.updated(2, B2)
      game5.p2.karten = game5.p2.karten.updated(0, G0)
      game5.p2.karten = game5.p2.karten.updated(1, G1)
      game5.p2.karten = game5.p2.karten.updated(2, G2)

      game5.playerDiff shouldBe (3)
      game5.p1.karten.size shouldBe (3)
      game5.p2.karten.size shouldBe (3)
      game5.midCard.karten.size shouldBe (1)

      game5.next()
      game5.playerDiff shouldBe (4)
      game5.place(1)
      game5.p1.karten.size shouldBe (2)
      game5.p2.karten.size shouldBe (3)
      game5.midCard.karten.size shouldBe (1)
      game5.midCard.karten(0) shouldBe (B1)

      game5.next()
      game5.playerDiff shouldBe (5)
      game5.next()
      game5.playerDiff shouldBe (6)
      game5.place(2)
      game5.p1.karten.size shouldBe (2)
      game5.p2.karten.size shouldBe (2)
      game5.midCard.karten.size shouldBe (1)
      game5.midCard.karten(0) shouldBe (G2)

    }
    "have a method next() that increments the PlayerDiff variable" in {
      val game6 = Game("player1", "player2", 0)
      game6.playerDiff shouldBe (3)
      game6.next()
      game6.playerDiff shouldBe (4)
      game6.next()
      game6.playerDiff shouldBe (5)
    }
    "have a method playerFill() that fills the karten from one player with the parameter amount " in {
      val gameXX = Game("player1", "player2", 0)
      gameXX.p1.karten.size shouldBe (0)
      gameXX.p2.karten.size shouldBe (0)
      gameXX.playerFill(2)
      gameXX.p1.karten.size shouldBe (2)
      gameXX.p2.karten.size shouldBe (2)

    }
    "override the method toString() and return a String in Form of" in {
      val game7 = Game("player1", "player2", 0)
      //game7.midCard.karten = game7.midCard.karten.updated(0, R0)
      game7.add("P1", R0)
      game7.next()
      game7.place(0)
      game7.next()
      game7.toString() shouldBe (
        "player1" + eol +
          "+--+" + eol +
          "| 0|" + eol +
          "+--+" + eol +
          eol +
          "+--+" + eol +
          "|R0|" + eol +
          "+--+" + eol +
          eol +
          "+--+" + eol +
          "| 0|" + eol +
          "+--+" + eol +
          "player2" + eol
      )

      val game8 = Game("player1", "player2", 0)
      //game8.midCard.karten = game8.midCard.karten.updated(0, R0)
      //game8.p1.karten = game8.p1.karten.updated(0, B0)
      game8.add("P1", B0)
      game8.add("P1", R0)
      game8.add("P2", G0)
      game8.next()
      game8.place(1)
      game8.toString() shouldBe (
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
          "| 1|" + eol +
          "+--+" + eol +
          "player2" + eol
      )
      game8.next()
      //game8.p2.karten = game8.p2.karten.updated(0, G0)
      game8.toString() shouldBe (
        "player1" + eol +
          "+--+" + eol +
          "| 1|" + eol +
          "+--+" + eol +
          eol +
          "+--+" + eol +
          "|R0|" + eol +
          "+--+" + eol +
          eol +
          "+--+" + eol +
          "| 1|" + eol +
          "+--+" + eol +
          "player2" + eol
      )
      game8.next()
      game8.toString() shouldBe (
        "player1" + eol +
          "+--+" + eol +
          "| 1|" + eol +
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

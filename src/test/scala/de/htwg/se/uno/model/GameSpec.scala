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
      g1.currentstate shouldBe (g1.p1n)
      g1.midCard.karten.size shouldBe (1)
      g1.pList(0).karten.size shouldBe (4)
      g1.pList(1).karten.size shouldBe (4)
      g1.cardsInDeck shouldBe (40)
    }

    "have a method add(String, Card) that adds a card to a players hand" in {
      val game1 = Game("player1", "player2", 0)
      game1.pList(0).karten.size shouldBe (0)
      game1.pList(1).karten.size shouldBe (0)
      game1.midCard.karten.size shouldBe (1)

      game1.add("p1", XX)
      game1.pList(0).karten.size shouldBe (1)

      game1.add("p2", R0) shouldEqual (0)
      game1.pList(1).karten.size shouldBe (1)

      game1.add("midstack", R0)
      game1.midCard.karten.size shouldBe (2)

      game1.add("p3", G8) shouldEqual (-3)
    }
    "have a method take(String) that adds a random card to a players hand" in {
      val game2 = Game("player1", "player2", 0)
      game2.pList(0).karten.size shouldBe (0)
      game2.pList(1).karten.size shouldBe (0)

      game2.take("p1")
      game2.pList(0).karten.size shouldBe (1)

      game2.take("p2")
      game2.pList(1).karten.size shouldBe (1)
    }

    "have a method place(Integer, player) that places a card onto the stack" in {
      val game4 = Game("player1", "player2", 0)
      game4.currentstate shouldBe (game4.p1n)
      game4.addTest("midstack", R0)
      game4.midCard.karten(0) shouldBe (R0)
      game4.add("p1", B0)
      game4.add("p2", G0)
      game4.pList(0).karten.size shouldBe (1)
      game4.pList(1).karten.size shouldBe (1)
      game4.midCard.karten.size shouldBe (1)

      game4.changeState()
      game4.currentstate shouldBe (game4.p1s)
      game4.place(0, game4.pList(0))
      game4.ERROR shouldBe (0)
      game4.pList(0).karten.size shouldBe (0)
      game4.pList(1).karten.size shouldBe (1)
      game4.midCard.karten.size shouldBe (1)
      game4.midCard.karten(0) shouldBe (B0)

      game4.changeState()
      game4.currentstate shouldBe (game4.p2n)
      game4.changeState()
      game4.currentstate shouldBe (game4.p2s)
      game4.place(0, game4.pList(1))
      game4.ERROR shouldBe (0)
      game4.pList(0).karten.size shouldBe (0)
      game4.pList(1).karten.size shouldBe (0)
      game4.midCard.karten.size shouldBe (1)
      game4.midCard.karten(0) shouldBe (G0)

      val game5 = Game("player1", "player2", 0)
      game5.addTest("midStack", R1)
      game5.midCard.karten(0) shouldBe (R1)
      game5.add("p1", B0)
      game5.add("p1", B1)
      game5.add("p1", B2)
      game5.add("p2", G0)
      game5.add("p2", G1)
      game5.add("p2", G2)

      game5.currentstate shouldBe (game5.p1n)
      game5.pList(0).karten.size shouldBe (3)
      game5.pList(1).karten.size shouldBe (3)
      game5.midCard.karten.size shouldBe (1)

      game5.changeState()
      game5.currentstate shouldBe (game5.p1s)
      game5.place(1, game5.pList(0))
      game5.ERROR shouldBe (0)
      game5.pList(0).karten.size shouldBe (2)
      game5.pList(1).karten.size shouldBe (3)
      game5.midCard.karten.size shouldBe (1)
      game5.midCard.karten(0) shouldBe (B1)

      game5.changeState()
      game5.currentstate shouldBe (game5.p2n)
      game5.changeState()
      game5.currentstate shouldBe (game5.p2s)
      game5.place(1, game5.pList(1))
      game5.ERROR shouldBe (0)
      game5.pList(0).karten.size shouldBe (2)
      game5.pList(1).karten.size shouldBe (2)
      game5.midCard.karten.size shouldBe (1)
      game5.midCard.karten(0) shouldBe (G1)

    }
    "have a method changeState() that changes the currentstate to the next one" in {
      val game6 = Game("player1", "player2", 0)
      game6.currentstate shouldBe (game6.p1n)
      game6.changeState()
      game6.currentstate shouldBe (game6.p1s)
      game6.changeState()
      game6.currentstate shouldBe (game6.p2n)
    }
    "have a method playerFill() that fills the karten from one player with the parameter amount " in {
      val gameXX = Game("player1", "player2", 0)
      gameXX.pList(0).karten.size shouldBe (0)
      gameXX.pList(1).karten.size shouldBe (0)
      gameXX.playerFill(2)
      gameXX.pList(0).karten.size shouldBe (2)
      gameXX.pList(1).karten.size shouldBe (2)

    }
    "override the method toString() and return a String in Form of" in {
      val game7 = Game("player1", "player2", 0)
      game7.addTest("Midstack", R0)
      game7.changeState()
      game7.changeState()
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
      game8.add("P1", B0)
      game8.addTest("midStack", R0)
      game8.add("P2", G0)
      game8.changeState()
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
      game8.changeState()
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
      game8.changeState()
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

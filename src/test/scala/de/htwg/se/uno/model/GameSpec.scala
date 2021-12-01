package de.htwg.se.uno
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

import Player._
import toCard._
import CardLayout._
import Card._
import util.State

import controller._

class GameSpec extends AnyWordSpec {
  "Game" should {
    "should create a game with 2 Players and one Card in the middle" in {
      val g1 = new Game("player1", "player2").take("midstack")
      g1.currentstate shouldEqual (between21State)
      g1.midCard.karten.size shouldBe (1)
      g1.pList(0).karten.size shouldBe (0)
      g1.pList(1).karten.size shouldBe (0)
      g1.cardsInDeck shouldBe (40)
    }

    "have a method add(String, Card) that adds a card to a players hand" in {
      var game1 = Game.newGame("player1", "player2")
      game1.pList(0).karten.size shouldBe (7)
      game1.pList(1).karten.size shouldBe (7)
      game1.midCard.karten.size shouldBe (1)

      game1 = game1.add("p1", XX)
      game1.pList(0).karten.size shouldBe (8)

      game1 = game1.add("p2", R0)
      game1.ERROR shouldEqual (0)
      game1.pList(1).karten.size shouldBe (8)

      game1 = game1.add("midstack", R0)
      game1.midCard.karten.size shouldBe (2)

      game1 = game1.add("p3", G8)
      game1.ERROR shouldEqual (-1)
    }
    "have a method take(String) that adds a random card to a players hand" in {
      var game2 = new Game("player1", "player2")
      game2.pList(0).karten.size shouldBe (0)
      game2.pList(1).karten.size shouldBe (0)

      game2 = game2.take("p1")
      game2.pList(0).karten.size shouldBe (1)

      game2 = game2.take("p2")
      game2.pList(1).karten.size shouldBe (1)
    }

    "have a method place(Integer, player) that places a card onto the stack" in {
      var game4 = new Game("player1", "player2")
      game4.currentstate shouldBe (between21State)
      game4 = game4.addTest("midstack", R0)
      game4.midCard.karten(0) shouldBe (R0)
      game4 = game4.add("p1", B0)
      game4 = game4.add("p2", G0)
      game4.pList(0).karten.size shouldBe (1)
      game4.pList(1).karten.size shouldBe (1)
      game4.midCard.karten.size shouldBe (1)

      game4 = Game(
        game4.pList,
        player1State,
        game4.ERROR,
        game4.cardStack,
        game4.midCard
      )
      game4.currentstate shouldBe (player1State)
      game4 = game4.place(0, 0)
      game4.ERROR shouldBe (0)
      game4.pList(0).karten.size shouldBe (0)
      game4.pList(1).karten.size shouldBe (1)
      game4.midCard.karten.size shouldBe (1)
      game4.midCard.karten(0) shouldBe (B0)

      game4 = Game(
        game4.pList,
        between12State,
        game4.ERROR,
        game4.cardStack,
        game4.midCard
      )
      game4.currentstate shouldBe (between12State)
      game4 = Game(
        game4.pList,
        player2State,
        game4.ERROR,
        game4.cardStack,
        game4.midCard
      )
      game4.currentstate shouldBe (player2State)
      game4 = game4.place(0, 1)
      game4.ERROR shouldBe (0)
      game4.pList(0).karten.size shouldBe (0)
      game4.pList(1).karten.size shouldBe (0)
      game4.midCard.karten.size shouldBe (1)
      game4.midCard.karten(0) shouldBe (G0)

      var game5 = new Game("player1", "player2")
      game5 = game5.addTest("midStack", R1)
      game5.midCard.karten(0) shouldBe (R1)
      game5 = game5.add("p1", B0)
      game5 = game5.add("p1", B1)
      game5 = game5.add("p1", B2)
      game5 = game5.add("p2", G0)
      game5 = game5.add("p2", G1)
      game5 = game5.add("p2", G2)

      game5.currentstate shouldBe (between21State)
      game5.pList(0).karten.size shouldBe (3)
      game5.pList(1).karten.size shouldBe (3)
      game5.midCard.karten.size shouldBe (1)

      game5 = Game(
        game5.pList,
        player1State,
        game5.ERROR,
        game5.cardStack,
        game5.midCard
      )
      game5.currentstate shouldBe (player1State)
      game5 = game5.place(1, 0)
      game5.ERROR shouldBe (0)
      game5.pList(0).karten.size shouldBe (2)
      game5.pList(1).karten.size shouldBe (3)
      game5.midCard.karten.size shouldBe (1)
      game5.midCard.karten(0) shouldBe (B1)

      game5 = Game(
        game5.pList,
        between12State,
        game5.ERROR,
        game5.cardStack,
        game5.midCard
      )
      game5.currentstate shouldBe (between12State)
      game5 = Game(
        game5.pList,
        player2State,
        game5.ERROR,
        game5.cardStack,
        game5.midCard
      )
      game5.currentstate shouldBe (player2State)
      game5 = game5.place(1, 1)
      game5.ERROR shouldBe (0)
      game5.pList(0).karten.size shouldBe (2)
      game5.pList(1).karten.size shouldBe (2)
      game5.midCard.karten.size shouldBe (1)
      game5.midCard.karten(0) shouldBe (G1)
    }

    "have a method playerFill() that fills the karten from one player with the parameter amount " in {
      var gameXX = new Game("player1", "player2")
      gameXX.pList(0).karten.size shouldBe (0)
      gameXX.pList(1).karten.size shouldBe (0)
      gameXX = gameXX.playerFill(2)
      gameXX.pList(0).karten.size shouldBe (2)
      gameXX.pList(1).karten.size shouldBe (2)

    }
    "override the method toString() and return a String in Form of" in {
      var game7 = new Game("player1", "player2")
      game7 = game7.addTest("Midstack", R0)
      game7 = Game(
        game7.pList,
        player1State,
        game7.ERROR,
        game7.cardStack,
        game7.midCard
      )
      game7 = Game(
        game7.pList,
        between12State,
        game7.ERROR,
        game7.cardStack,
        game7.midCard
      )
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
      /*
      val game8 = new Game("player1", "player2")
      game8.add("P1", B0)
      game8.addTest("midStack", R0)
      game8.add("P2", G0)
      game8.copy(
        game8.pList,
        player1State,
        game8.ERROR,
        game8.cardStack,
        game8.midCard
      )
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
      game8.copy(
        game8.pList,
        between12State,
        game8.ERROR,
        game8.cardStack,
        game8.midCard
      )
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
      game8.copy(
        game8.pList,
        player2State,
        game8.ERROR,
        game8.cardStack,
        game8.midCard
      )
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
       */
    }
  }
}

package de.htwg.se.uno
package model.gameComponent.gameBaseImpl

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

import Player._
import toCard._
import CardLayout._
import Card._
import util.State

import de.htwg.se.uno.controller.controllerComponent.controllerBaseImpl.between21State
import de.htwg.se.uno.controller.controllerComponent.controllerBaseImpl.player1State
import de.htwg.se.uno.controller.controllerComponent.controllerBaseImpl.between12State
import de.htwg.se.uno.controller.controllerComponent.controllerBaseImpl.player2State

class GameSpec extends AnyWordSpec {
  "Case Class Game" should {
    "have a second constructor that takes (String,String,State) and creates a new Game" in {
      val g1 = new Game("Bence", "Timo", between21State)
      g1.pList(0).name shouldBe ("Bence")
      g1.pList(1).name shouldBe ("Timo")
      g1.pList(0).karten.size shouldBe (0)
      g1.pList(1).karten.size shouldBe (0)
      g1.midCard.karten.size shouldBe (0)
      g1.currentstate shouldEqual (between21State)
    }

    "should create a game with 2 Players and one Card in the middle" in {
      val g1 = new Game("player1", "player2", between21State).take("midcard")
      g1.currentstate shouldEqual (between21State)
      g1.midCard.karten.size shouldBe (1)
      g1.pList(0).karten.size shouldBe (0)
      g1.pList(1).karten.size shouldBe (0)
      g1.cardsInDeck shouldBe (40)
    }

    "have a method add(String, Card) that adds a card to a players hand" in {
      var game1 = new Game("player1", "player2", between21State).take("midcard")
      game1.pList(0).karten.size shouldBe (0)
      game1.pList(1).karten.size shouldBe (0)
      game1.midCard.karten.size shouldBe (1)

      game1 = game1.add("p1", XX)
      game1.ERROR shouldEqual (0)
      game1.pList(0).karten.size shouldBe (1)

      game1 = game1.add("p2", R0)
      game1.ERROR shouldEqual (0)
      game1.pList(1).karten.size shouldBe (1)

      game1 = game1.add("midstack", R0)
      game1.midCard.karten.size shouldBe (1)

      game1 = game1.add("p3", G8)
      game1.ERROR shouldEqual (-1)
    }

    "have a method take(String) that adds a random card to a players hand" in {
      var game1 = new Game("player1", "player2", between21State)
      game1.pList(0).karten.size shouldBe (0)
      game1.pList(1).karten.size shouldBe (0)

      game1 = game1.take("p1")
      game1.pList(0).karten.size shouldBe (1)

      game1 = game1.take("p2")
      game1.pList(1).karten.size shouldBe (1)
    }

    "have a method checkPlace(Int,Int) that checks if a player is allowed to place a specific card" in {
      var game1 = new Game("player1", "player2", between21State)
      game1 = game1.addTest("midcard", R0)
      game1 = game1.add("p1", R1)
      game1.checkPlace(0, 0) shouldBe (true)

      var game2 = new Game("player1", "player2", between21State)
      game2 = game2.addTest("midcard", R0)
      game2 = game2.add("p2", G0)
      game2.checkPlace(0, 1) shouldBe (true)
    }

    "have a method place(Integer, player) that places a card onto the stack" in {
      var game1 = new Game("player1", "player2", between21State)
      game1.currentstate shouldBe (between21State)
      game1 = game1.addTest("midstack", R0)
      game1.midCard.karten(0) shouldBe (R0)
      game1 = game1.add("p1", B0)
      game1 = game1.add("p2", G0)
      game1.pList(0).karten.size shouldBe (1)
      game1.pList(1).karten.size shouldBe (1)
      game1.midCard.karten.size shouldBe (1)

      game1 = Game(
        game1.pList,
        player1State,
        game1.ERROR,
        game1.cardStack,
        game1.midCard,
        -1
      )

      game1.currentstate shouldBe (player1State)
      game1 = game1.place(0, 0)
      game1.ERROR shouldBe (0)
      game1.pList(0).karten.size shouldBe (0)
      game1.pList(1).karten.size shouldBe (1)
      game1.midCard.karten.size shouldBe (1)
      game1.midCard.karten(0) shouldBe (B0)

      game1 = Game(
        game1.pList,
        between12State,
        game1.ERROR,
        game1.cardStack,
        game1.midCard,
        -1
      )

      game1.currentstate shouldBe (between12State)
      game1 = Game(
        game1.pList,
        player2State,
        game1.ERROR,
        game1.cardStack,
        game1.midCard,
        -1
      )

      game1.currentstate shouldBe (player2State)
      game1 = game1.place(0, 1)
      game1.ERROR shouldBe (0)
      game1.pList(0).karten.size shouldBe (0)
      game1.pList(1).karten.size shouldBe (0)
      game1.midCard.karten.size shouldBe (1)
      game1.midCard.karten(0) shouldBe (G0)

      var game2 = new Game("player1", "player2", between21State)
      game2 = game2.addTest("midStack", R1)
      game2.midCard.karten(0) shouldBe (R1)
      game2 = game2.add("p1", B0)
      game2 = game2.add("p1", B1)
      game2 = game2.add("p1", B2)
      game2 = game2.add("p2", G0)
      game2 = game2.add("p2", G1)
      game2 = game2.add("p2", G2)

      game2.currentstate shouldBe (between21State)
      game2.pList(0).karten.size shouldBe (3)
      game2.pList(1).karten.size shouldBe (3)
      game2.midCard.karten.size shouldBe (1)

      game2 = Game(
        game2.pList,
        player1State,
        game2.ERROR,
        game2.cardStack,
        game2.midCard,
        game2.winner
      )

      game2.currentstate shouldBe (player1State)
      game2 = game2.place(1, 0)
      game2.ERROR shouldBe (0)
      game2.pList(0).karten.size shouldBe (2)
      game2.pList(1).karten.size shouldBe (3)
      game2.midCard.karten.size shouldBe (1)
      game2.midCard.karten(0) shouldBe (B1)

      game2 = Game(
        game2.pList,
        between12State,
        game2.ERROR,
        game2.cardStack,
        game2.midCard,
        game2.winner
      )
      game2.currentstate shouldBe (between12State)
      game2 = Game(
        game2.pList,
        player2State,
        game2.ERROR,
        game2.cardStack,
        game2.midCard,
        game2.winner
      )

      game2.currentstate shouldBe (player2State)
      game2 = game2.place(1, 1)
      game2.ERROR shouldBe (0)
      game2.pList(0).karten.size shouldBe (2)
      game2.pList(1).karten.size shouldBe (2)
      game2.midCard.karten.size shouldBe (1)
      game2.midCard.karten(0) shouldBe (G1)

      var game3 = new Game("player1", "player2", between21State)
      game3.currentstate shouldBe (between21State)
      game3 = game1.addTest("midstack", R0)
      game3.midCard.karten(0) shouldBe (R0)
      game3 = game3.add("p1", B1)
      game3.pList(0).karten.size shouldBe (1)
      game3.midCard.karten.size shouldBe (1)

      game3 = Game(
        game3.pList,
        player1State,
        game3.ERROR,
        game3.cardStack,
        game3.midCard,
        game3.winner
      )

      game3.currentstate shouldBe (player1State)
      game3 = game3.place(0, 0)
      game3.ERROR shouldBe (-1)
    }

    "have a method checkWin(Player) that checks if the player has won" in {
      var game1 = new Game("player1", "player2", between21State)
      game1.checkWin(game1.pList(0)) shouldBe (true)

      game1 = game1.add("p1", B0)
      game1.checkWin(game1.pList(0)) shouldBe (false)
    }

    "have a method setError(Int), that sets the ERROR value of game to the given Int" in {
      var game1 = new Game("player1", "player2", between21State)
      game1.ERROR shouldBe (0)

      game1 = game1.setError(-1)
      game1.ERROR shouldBe (-1)

      game1 = game1.setError(13)
      game1.ERROR shouldBe (13)
    }

    "have a method playerFill() that fills the karten from one player with the parameter amount " in {
      var game1 = new Game("player1", "player2", between21State)
      game1.pList(0).karten.size shouldBe (0)
      game1.pList(1).karten.size shouldBe (0)
      game1 = game1.playerFill(2)
      game1.pList(0).karten.size shouldBe (2)
      game1.pList(1).karten.size shouldBe (2)
    }
  }
}

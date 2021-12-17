package de.htwg.se.uno
package controller.controllerComponent

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

import model.gameComponent.Game
import model.gameComponent.Card._
import model.gameComponent.CardLayout.eol

class UnoStateSpec extends AnyWordSpec {
  "UnoState" should {
    var game = new Game("p1", "p2", between21State)
    game = game.addTest("midstack", R0)
    game = game.add("p1", B0)
    game = game.add("p2", G0)
    var c = new Controller(game)
    val takeC = UnoCommand(c, "take")
    val placeC = UnoCommand(0, c)
    val winC = UnoCommand(c, "win")
    val nextC = UnoCommand(c, "next")
    "player1State should have a method handle() that matches the commands" in {
      c.game = player1State.handle(takeC)
      c.game.pList(0).karten.size shouldBe (2)

      c.game = player1State.handle(placeC)
      c.game.pList(0).karten.size shouldBe (1)
      c.game.midCard.karten(0) shouldEqual (B0)

      c.game = player1State.handle(nextC)
      c.game.currentstate shouldBe (between12State)
    }
    "player2State should have a method handle() that matches the commands" in {

      c.game = player2State.handle(placeC)
      c.game.pList(1).karten.size shouldBe (0)
      c.game.midCard.karten(0) shouldEqual (G0)

      //c.game = player2State.handle(winC)    //bei win bekommt man readlines

      c.game = player2State.handle(nextC)
      c.game.currentstate shouldBe (between21State)
    }
    "between12State should have a method handle() that matches the commands" in {
      c.game = between12State.handle(takeC)
      c.game.ERROR shouldBe (-1)

      c.game = between12State.handle(placeC)
      c.game.ERROR shouldBe (-1)

      c.game = between12State.handle(winC)
      c.game.ERROR shouldBe (-1)

      c.game = between12State.handle(nextC)
      c.game.ERROR shouldBe (0)
    }
    "between21State should have a method handle() that matches the commands" in {
      c.game = between21State.handle(takeC)
      c.game.ERROR shouldBe (-1)

      c.game = between21State.handle(placeC)
      c.game.ERROR shouldBe (-1)

      c.game = between21State.handle(winC)
      c.game.ERROR shouldBe (-1)

      c.game = between21State.handle(nextC)
      c.game.ERROR shouldBe (0)
    }
  }
}

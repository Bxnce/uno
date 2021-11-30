package de.htwg.se.uno
package controller

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

import model.Game
import model.Card._

class ControllerSpec extends AnyWordSpec {
  "Controller" should {
    val game = new Game("p1", "p2", 0)
    val c = new Controller(game)
    c.game.addTest("midstack", R0)
    "contain a game" in {
      c.game.pList(0).karten.size shouldBe (0)
      c.game.pList(1).karten.size shouldBe (0)
      c.game.midCard.karten.size shouldBe (1)
      c.game.midCard.karten(0) shouldBe (R0)

    }
    "have a method take(), that calls the take() function in game" in {
      //that we know a Card from player1 to test place later
      c.game.add("p1", B0) shouldBe (0)

      c.game.currentstate shouldBe (game.p1n)
      c.next() //p1s
      c.take()
      c.game.ERROR shouldBe (0)
      c.next() //p2n
      c.take()
      c.game.ERROR shouldBe (-1)
      c.next() //p2s
      c.take()
      c.game.ERROR shouldBe (0)
    }
    "override the method toString" in {
      c.toString shouldEqual (c.game.toString)
    }
    "have a method place() that places a Card from a player to the midStack" in {
      c.next() //p1n
      c.next() //p1s
      c.place(0)
      c.game.ERROR shouldBe (0)
      c.game.midCard.karten(0) shouldBe (B0)
      c.game.changeState() //p2n
      c.game.currentstate shouldBe (game.p2n)
      c.place(0)
      c.game.ERROR shouldBe (-1)
    }
    "have a method next()" in {
      c.game.currentstate shouldEqual (game.p2n)
      c.next() //p2s
      c.game.currentstate shouldEqual (game.p2s)
    }
  }
}

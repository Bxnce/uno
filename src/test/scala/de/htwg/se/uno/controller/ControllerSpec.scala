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
    "contain a game" in {
      c.game.p1.karten.size shouldBe (0)
      c.game.p2.karten.size shouldBe (0)
      c.game.midCard.karten.size shouldBe (1)
    }
    "have a method take(), that calls the take() function in game" in {
      c.game.add("p1", B1) shouldBe (0)

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
      c.game.place(
        0,
        c.game.p1
      ) //nochmal drüber nachdenken wo man das place aufrufen soll
      c.game.ERROR shouldBe (0)
      c.game.midCard.karten(0) shouldBe (B1)
      c.game.changeState() //p1n
      c.game.currentstate shouldBe (game.p1n)
      c.place(0) //genauso hier
      c.game.ERROR shouldBe (-1)
    }
    "have a method next()" in {
      c.game.currentstate shouldEqual (game.p1n)
      c.next() //p1
      c.game.currentstate shouldEqual (game.p1s) //p2n
    }
  }
}

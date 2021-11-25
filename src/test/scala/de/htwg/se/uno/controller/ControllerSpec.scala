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
    "have a method add(String, String), that calls the add function in game" in {
      //dafür sorgen dass B0 nicht auf dem Spielstapel liegt
      val tmp = game.midCard.karten(0)
      game.add("midstack", Y9)
      game.midCard.removeInd(0)
      game.cardStack.cards =
        game.cardStack.cards + (tmp -> ((game.cardStack.cards(tmp) + 1)))
      game.midCard.karten.size shouldBe (1)
      game.midCard.karten(0) shouldBe (Y9)

      c.add("p1", "B0") shouldBe (0)
      c.add("p4", "G0") shouldBe (-3)
      c.add("p1", "B0") shouldBe (0)
      c.add("p1", "B0") shouldBe (-2)
      c.add("p2", "B1") shouldBe (0)
      c.add("p1", "Hallo") shouldBe (-1)
    }
    "have a method take(String), that calls the take(String) function in game" in {
      c.take("p1") shouldBe (0)
      c.take("p2") shouldBe (0)
      c.take("p3") shouldBe (-3)
    }
    "have a method take(), that calls the take() function in game" in {
      c.game.currentstate shouldBe (game.p1n)
      c.next() //p1s
      c.take() shouldBe (0)
      c.next() //p2n
      c.take() shouldBe (-4)
      c.next() //p2s
      c.take() shouldBe (0)
    }
    "override th method toString" in {
      c.toString shouldEqual (c.game.toString)
    }
    "have a method place() that places a Card from a player to the midStack" in {
      c.game.place(0) shouldBe (0)
      c.game.midCard.karten(0) shouldBe (B1)
      c.game.changeState() //p1n
      c.game.currentstate shouldBe (game.p1n)
      c.game.place(0) shouldBe (-4)
    }
    "have a method next()" in {
      c.game.currentstate shouldEqual (game.p1n)
      c.next() //p1
      c.game.currentstate shouldEqual (game.p1s) //p2n
    }
  }
}

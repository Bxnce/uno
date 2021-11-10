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
      val game = Game()
      game.p1.karten.size shouldBe (0)
      game.add("p1", "R0")
      game.p1.karten.size shouldBe (1)
      game.p1.karten(0) shouldBe (R0)
    }
  }
}
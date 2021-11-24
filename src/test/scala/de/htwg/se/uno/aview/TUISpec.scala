package de.htwg.se.uno
package aview

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

import model.Game
import model.Card._
import model.CardLayout._
import controller.Controller

class TUISpec extends AnyWordSpec {
  "TUI" when {
    val game = Game("Bence", "Timo", 0)
    val controller = Controller(game)
    //controller.game.p1.karten = controller.game.p1.karten.updated(0, R0)
    controller.game.add("P1", "Y6") //liegt auf midStack
    controller.game.add("P1", "R0")
    controller.game.add("P1", "R1")
    controller.game.add("P1", "R2")
    controller.game.add("P2", "G0")
    controller.game.add("P2", "G1")
    controller.game.add("P2", "G2")
    controller.next() // playerDiff = 4
    controller.place(0)
    controller.next() // playerDiff = 5

    val tui = TUI(controller)

    "created with the given parameters " should {
      "have the following values" in {
        controller.toString shouldEqual (
          "Bence\n" +
            "+--+\n" +
            "| 3|\n" +
            "+--+\n" +
            "\n" +
            "+--+\n" +
            "|Y6|\n" +
            "+--+\n" +
            "\n" +
            "+--+\n" +
            "| 3|\n" +
            "+--+\n" +
            "Timo\n"
        )
        //vllt einfach einen case new bei dem dann erst ein neues Spiel erstellt wird.
      }
      "have a method run(String) that checks the output of convertInputString" in {
        tui.run("") shouldBe (tui.printhelp())
      }
      "have a method convertinputString(String) that calls the controller to execute the command" in {
        tui.convertinputString("") shouldBe (tui.ERROR)
        tui.convertinputString("a") shouldBe (tui.ERROR)
        tui.convertinputString("add p1") shouldBe (tui.ERROR)
        tui.convertinputString("help") shouldBe (tui.SUCCESS)

        tui.convertinputString("add p1 R3") shouldBe (tui.SUCCESS)
        tui.convertinputString("add p2 G3") shouldBe (tui.SUCCESS)
        controller.game.p1.karten.size shouldBe (4)
        controller.game.p2.karten.size shouldBe (4)
        controller.game.p1.karten(3) shouldBe (R3)
        controller.game.p2.karten(3) shouldBe (G3)

        tui.convertinputString("n") shouldBe (tui.SUCCESS) // playerDiff = 6
        tui.convertinputString("n") shouldBe (tui.SUCCESS) // playerDiff = 7
        tui.convertinputString("n") shouldBe (tui.SUCCESS) // playerDiff = 8
        tui.convertinputString("+") shouldBe (tui.SUCCESS)
        controller.game.p1.karten.size shouldBe (5)

        tui.convertinputString("- 1") shouldBe (tui.SUCCESS)
        controller.game.p1.karten.size shouldBe (4)
        controller.game.midCard.karten(0) shouldBe (R0)

        tui.convertinputString("n") shouldBe (tui.SUCCESS) // playerDiff = 9
        tui.convertinputString("n") shouldBe (tui.SUCCESS) // playerDiff = 10
        controller.game.playerDiff shouldBe (10)
      }
      "Have a method printhelp() that prints out the help message" in {}
      "override th method update" in {}
    }
  }
}

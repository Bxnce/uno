package de.htwg.se.uno

import aview._
import model.Game
import controller.Controller
import scala.io.StdIn.readLine //import controller._

@main def Main: Unit =
  println("\n" * 50)
  println("Bitte den Anweisungen folgen:")
  val game = Game(
    readLine("Name Spieler1:                   "),
    readLine("Name Spieler2:                   "),
    readLine("Anzahl der Startkarten eingeben: ").toInt
  )
  val controller = Controller(game)
  val tui = TUI(controller)
  tui.run()

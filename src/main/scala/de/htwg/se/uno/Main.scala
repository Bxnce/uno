package de.htwg.se.uno

import aview._
import model.Game
import de.htwg.se.uno.controller.Controller
import scala.io.StdIn.readLine //import controller._

@main def Main: Unit =
  println(
    "\n" * 50 + "Willkommen zum Spiel! für Hilfe help eingeben!" + "\n" * 5
  )
  val game = Game(
    readLine("Name Spieler1:                   "),
    readLine("Name Spieler2:                   "),
    readLine("Anzahl der Startkarten eingeben: ").toInt
  )
  val controller = Controller(game)
  val tui = TUI(controller)
  tui.run()

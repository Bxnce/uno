package de.htwg.se.uno

import aview._
import model.Game
import de.htwg.se.uno.controller.Controller
//import controller._

@main def Main: Unit =
  val game = Game()
  val controller = Controller(game)
  val tui = TUI(controller)
  tui.run()

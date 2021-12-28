package de.htwg.se.uno

import aview._
import model.gameComponent.gameBaseImpl.Game
import model.gameComponent.gameBaseImpl.Player
import model.gameComponent.gameBaseImpl.Card
import controller.controllerComponent.controllerBaseImpl._
import scala.io.StdIn.readLine //import controller._
import Console.{BLUE, RESET}
import de.htwg.se.uno.aview.GUI.mGUI

@main def Main: Unit =
  println("\n" * 50)
  val controller = new Controller(
    new Game("place_h", "place_h", between21State)
  )
  val tui = TUI(controller)
  val gui = mGUI(controller)

  var input: String = ""
  while input != "q" && input != "exit" do
    Console.print(s"${BLUE}>>>  ${RESET}")
    input = readLine()
    tui.run(input)

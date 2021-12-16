package de.htwg.se.uno

import aview._
import model.gameComponent.Game
import model.gameComponent.Player
import model.gameComponent.Card
import controller.controllerComponent._
import scala.io.StdIn.readLine //import controller._
import Console.{BLUE, RESET}

@main def Main: Unit =
  println("\n" * 50)
  val controller = new Controller(
    new Game("place_h", "place_h", between21State)
  )
  val tui = TUI(controller)
  //val gui = GUI(controller)

  var input: String = ""
  while input != "q" && input != "exit" do
    Console.print(s"${BLUE}>>>  ${RESET}")
    input = readLine()
    tui.run(input)

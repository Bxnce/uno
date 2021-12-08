package de.htwg.se.uno

import aview._
import model.Game
import controller.Controller
import scala.io.StdIn.readLine //import controller._
import Console.{BLUE, RESET}

@main def Main: Unit =
  println("\n" * 50)
  val controller = new Controller(Game.newGame("Player 1", "Player 2"))
  val tui = TUI(controller)
  val gui = GUI(controller)

  var input: String = ""
  while input != "q" && input != "exit" do
    Console.print(s"${BLUE}>>>  ${RESET}")
    input = readLine()
    tui.run(input)

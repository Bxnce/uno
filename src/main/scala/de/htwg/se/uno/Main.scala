package de.htwg.se.uno

import aview._
import model.gameComponent.Game
import controller.controllerComponent._
import scala.io.StdIn.readLine //import controller._
import Console.{BLUE, RESET}

@main def Main: Unit =
  println("\n" * 50)
  /*val controller = new Controller(
    Game("place_h", "place_h", between12State)
  )*/
  //val tui = TUI(controller)
  //val gui = GUI(controller)

  val game = Game(
    Player("Timo", Vector[Card](), false),
    Player("Bence", Vector[Card](), false),
    between21State
  )
  print(game.toString)
  game.init()
  print(game.toString)

/*
  var input: String = ""
  while input != "q" && input != "exit" do
    Console.print(s"${BLUE}>>>  ${RESET}")
    input = readLine()
    tui.run(input)
 */

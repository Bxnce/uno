package de.htwg.se.uno

import aview._
import model.Game
import controller.Controller
import scala.io.StdIn.readLine //import controller._

@main def Main: Unit =
  println("\n" * 50)
  val tui = TUI()

  var input: String = ""
  while input != "q" && input != "exit" do
    input = readLine()
    tui.run(input)

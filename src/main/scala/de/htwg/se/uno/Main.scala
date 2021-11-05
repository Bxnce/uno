package de.htwg.se.uno

import scala.io.StdIn.readLine
import aview._
//import controller._
import model._

import scala.collection.immutable.HashMap
import de.htwg.se.uno._

@main def Main: Unit =
  println("Willkommen zu Uno!\n")
  val game1 = TUI()
  game1.TUI()

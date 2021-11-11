package de.htwg.se.uno
package aview

import scala.io.StdIn.readLine
import controller.Controller
import util.Observer
import Console.{RED, GREEN, RESET}

class TUI(controller: Controller) extends Observer:
  controller.add(this)
  def run() =
    println(controller.game.toString)
    TUI()

  override def update: Unit =
    print(controller.toString())

  def TUI(): Unit =
    val in = readLine()
    val innew = in.split(" ")
    innew(0) match
      case "exit" | "q" =>
        return
      case "help" | "h" =>
        Console.print(s"""${GREEN}
              Befehlsübersicht für Uno:
              - help | h                       : zeigt alle Befehle für Uno
              - exit | q                       : verlässt das Spiel
              - add <player> <card>            : fügt eine Karte einem Spieler hinzu
              - take | + <player>              : fügt eine Zufällige Karte zum jeweiligen Spieler hinzu           
              ${RESET}""" + "\n")
      case "add" =>
        if (innew.size == 3) {
          val p = innew(1)
          val c = innew(2)
          controller.add(p, c)
        } else {
          Console.println(s"${RED}Falscher take Aufruf!${RESET}")
        }
      case "take" | "+" =>
        if (innew.size == 2) {
          controller.take(innew(1))
        } else {
          controller.take()
        }
      case "place" | "-" =>
        controller.place(innew(1).toInt - 1)
      case "next" | "n" =>
        controller.next()
      case _ =>
        println("Ungültige Eingabe, versuchen sie help")
    TUI()

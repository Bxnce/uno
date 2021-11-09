package de.htwg.se.uno
package aview

import scala.io.StdIn.readLine
import controller.Controller
import util.Observer
import Console.{RED, RESET}

class TUI(controller: Controller) extends Observer:
  controller.add(this)
  def run() =
    println(
      "\n" * 10 + "Willkommen zum Spiel! für Hilfe help eingeben" + "\n" * 5 + controller.game.toString
    )
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
        print("""
              Befehlsübersicht für Uno:
              - help | h                       : zeigt alle Befehle für Uno
              - exit | q                       : verlässt das Spiel
              - add <player> <card>            : fügt eine Karte einem Spieler hinzu           
              """ + "\n")
      case "add" =>
        if (innew.size == 3) {
          val p = innew(1)
          val c = innew(2)
          controller.add(p, c)
        } else {
          Console.println(s"${RED}Falscher Add Aufruf!${RESET}")
        }

      case _ =>
        println("Ungültige Eingabe, versuchen sie help")
    TUI()

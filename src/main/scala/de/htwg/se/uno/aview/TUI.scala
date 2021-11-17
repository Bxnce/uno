package de.htwg.se.uno
package aview

import scala.io.StdIn.readLine
import controller.Controller
import model.Game
import util.Observer
import Console.{RED, GREEN, RESET}

class TUI(controller: Controller) extends Observer:
  controller.add(this)
  print(
    "\n\nWillkommen zu Uno! Um zur Uebersicht der Befehle zu kommen bitte help eingeben\n\n"
  )
  println(controller.toString)

  def this() =
    this(
      new Controller(
        Game(
          readLine("Name Spieler1:                   "),
          readLine("Name Spieler2:                   "),
          readLine("Anzahl der Startkarten eingeben: ").toInt
        )
      )
    )

  def run(input: String): Unit =
    val in = input
    val innew = in.split(" ")
    innew(0) match
      case "exit" | "q" => return
      case "help" | "h" =>
        Console.print(s"""${GREEN}
              Befehlsuebersicht fuer Uno:
              - help | h                       : zeigt alle Befehle fuer Uno
              - exit | q                       : verlaesst das Spiel
              - add <player> <card>            : fuegt eine Karte einem Spieler hinzu
              - take <player> | + <player>     : fuegt eine Zufaellige Karte zum jeweiligen Spieler hinzu 
              - place <index> | - <index>      : Legt die Karte an diesem Index auf den Spielstapel
              - next | n                       : Beendet den Zug, der naechste Spieler ist dran       
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
    print("\n\n")

  override def update: Unit =
    print(controller.toString())

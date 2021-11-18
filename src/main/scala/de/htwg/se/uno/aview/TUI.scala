package de.htwg.se.uno
package aview

import scala.io.StdIn.readLine
import controller.Controller
import model.Game
import util.Observer
import Console.{RED, GREEN, RESET}

class TUI(controller: Controller) extends Observer:
  val ERROR = -1
  val EXIT = 0
  val SUCCESS = 1
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

  def run(input: String) =
    convertinputString(input) match
      case ERROR   => printhelp()
      case EXIT    => print("Goodbye\n")
      case SUCCESS => print("\n\n")
      case _       => "Hier sollten sie nicht hinkommen"

  def convertinputString(input: String): Int =
    if (input.size == 0)
      print("Keine Eingabe!\n")
      return ERROR
    val in = input
    val innew = in.split(" ")
    innew(0) match
      case "exit" | "q" => return EXIT
      case "help" | "h" =>
        printhelp()
        return SUCCESS
      case "add" =>
        if (innew.size == 3) {
          val p = innew(1)
          val c = innew(2)
          controller.add(p, c)
          return SUCCESS
        } else {
          Console.println(s"${RED}Falscher take Aufruf!${RESET}")
          return ERROR
        }

      case "take" | "+" =>
        if (innew.size == 2) {
          controller.take(innew(1))
        } else {
          controller.take()
        }
        return SUCCESS
      case "place" | "-" =>
        controller.place(innew(1).toInt - 1)
        return SUCCESS
      case "next" | "n" =>
        controller.next()
        return SUCCESS
      case _ =>
        print("Falsche Eingabe, es gibt folgende Befehle: \n")
        return ERROR

  def printhelp() =
    Console.print(s"""${GREEN}
              Befehlsuebersicht fuer Uno:
              - help | h                       : zeigt alle Befehle fuer Uno
              - exit | q                       : verlaesst das Spiel
              - add <player> <card>            : fuegt eine Karte einem Spieler hinzu
              - take <player> | + <player>     : fuegt eine Zufaellige Karte zum jeweiligen Spieler hinzu 
              - place <index> | - <index>      : Legt die Karte an diesem Index auf den Spielstapel
              - next | n                       : Beendet den Zug, der naechste Spieler ist dran       
              ${RESET}""" + "\n")

  override def update: Unit =
    print(controller.toString())

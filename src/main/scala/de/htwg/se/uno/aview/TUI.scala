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

  def this() = this(new Controller(Game("Player 1", "Player 2", 0)))

  def run(input: String) =
    convertinputString(input) match
      case ERROR   => printhelp()
      case EXIT    => print("Goodbye\n")
      case SUCCESS => print("\n\n")
      case _       => print("Hier sollten sie nicht hinkommen\n")

  def convertinputString(input: String): Int =
    if (input.size == 0)
      print("Keine Eingabe!\n")
      return ERROR
    val in = input.split(" ")

    in(0) match
      case "exit" | "q" => return EXIT

      case "help" | "h" =>
        printhelp()
        return SUCCESS

      case "new" =>
        controller.game = Game(
          readLine("Name Spieler1:                   "),
          readLine("Name Spieler2:                   "),
          readLine("Anzahl der Startkarten eingeben: ").toInt
        )
        println(controller.toString)
        return SUCCESS

      case "add" =>
        if (in.size == 3) {
          val err = controller.add(in(1), in(2))
          if (err == -3) {
            Console.println(s"${RED}!!!Falschen Namen eingegeben!!!${RESET}")
            return ERROR
          } else if (err == -2) {
            Console.println(s"${RED}!!!Karte ist nichtmehr im Stack!!!${RESET}")
            return ERROR
          } else if (err == -1) {
            Console.println(s"${RED}!!!Ungültige Karte!!!${RESET}")
            return ERROR
          } else {
            return SUCCESS
          }
        } else {
          Console.println(s"${RED}Falscher add Aufruf!${RESET}")
          return ERROR
        }

      case "take" | "+" =>
        if (in.size == 2) {
          val err = controller.take(in(1))
          if (err == -3) {
            Console.println(s"${RED}!!!Falschen Namen eingegeben!!!${RESET}")
            return ERROR
          }
        } else {
          val err = controller.take()
          if (err == -4) {
            Console.println(
              s"${RED}!!!take oder + ist in diesem Zustand nicht möglich!!!${RESET}"
            )
            return ERROR
          }
        }
        return SUCCESS;

      case "place" | "-" =>
        if (in.size < 2) {
          Console.println(s"${RED}Falscher place Aufruf!${RESET}")
          return ERROR
        } else {
          val err = controller.place(in(1).toInt - 1)
          if (err == -4) {
            Console.println(
              s"${RED}!!!place oder - ist nicht möglich in diesem Zustand!!!${RESET}"
            )
            return ERROR
          } else {
            return SUCCESS
          }
        }

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
              - new  |                         : startet ein neues Spiel
              - add <player> <card>            : fuegt eine Karte einem Spieler hinzu
              - take <player> | + <player>     : fuegt eine Zufaellige Karte zum jeweiligen Spieler hinzu 
              - place <index> | - <index>      : Legt die Karte an diesem Index auf den Spielstapel
              - next | n                       : Beendet den Zug, der naechste Spieler ist dran       
              ${RESET}""" + "\n")

  override def update: Unit =
    print(controller.toString())

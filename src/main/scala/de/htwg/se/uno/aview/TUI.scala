package de.htwg.se.uno
package aview

import scala.io.StdIn.readLine
import controller.controllerComponent.controllerInterface
import model.gameComponent.Game
import util.Observer
import Console.{RED, GREEN, RESET}

class TUI(controller: controllerInterface) extends Observer:
  val ERROR = -1
  val EXIT = 0
  val SUCCESS = 1
  controller.add(this)
  print(
    "\n\nWillkommen zu Uno! Um zur Uebersicht der Befehle zu kommen bitte help eingeben\n\n"
  )

  def run(input: String) =
    convertinputString(input) match
      case ERROR   => printhelp()
      case EXIT    => System.exit(0)
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
        controller.newG()
        return SUCCESS

      case "take" | "+" =>
        controller.take()
        if (controller.game.ERROR < 0) {
          Console.println(
            s"${RED}!!!take oder + ist in diesem Zustand nicht möglich!!!${RESET}"
          )
          return ERROR
        }
        return SUCCESS;

      case "place" | "-" =>
        if (in.size < 2) {
          Console.println(s"${RED}Falscher place Aufruf!${RESET}")
          return ERROR
        } else {
          controller.place(in(1).toInt - 1)
          if (controller.game.ERROR < 0) {
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
      case "z" =>
        controller.undo()
        return SUCCESS
      case "y" =>
        controller.redo()
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
              - take | +                       : fuegt eine Zufaellige Karte zum jeweiligen Spieler hinzu 
              - place <index> | - <index>      : Legt die Karte an diesem Index auf den Spielstapel
              - next | n                       : Beendet den Zug, der naechste Spieler ist dran       
              ${RESET}""" + "\n")

  override def update: Unit =
    print(controller.toString())

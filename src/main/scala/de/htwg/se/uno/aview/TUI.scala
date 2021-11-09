package de.htwg.se.uno
package aview

import scala.io.StdIn.readLine
import controller.Controller
import util.Observer

class TUI(controller: Controller) extends Observer:
  controller.add(this)
  def run() =
    println(controller.game.p1.print())
    TUI()

  override def update: Unit =
    print(controller.toString())

  def TUI(): Unit =
    val in = readLine()
    in match
      case "exit" | "q" =>
        println("Hey")
        return
      case "help" | "h" =>
        print("""
                Befehlsübersicht für Uno:
                - help | h       : zeigt alle Befehle für Uno
                - exit | q       : verlässt das Spiel
                - game           : startet ein neues Spiel mit 2 Spielern
                - play <cardID>  : Legt die Karte mit dem gewünschten Index                
                """)
      case "add" =>
        controller.add()
      case _ =>
        println("Ungültige Eingabe, versuchen sie help")
    TUI()

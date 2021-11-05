package de.htwg.se.uno
package aview

import scala.io.StdIn.readLine
import model.Game

class TUI {
  def TUI(): Unit =
    val in = readLine()
    in match
      case "exit" | "q" =>
      case "help" | "h" =>
        print("""
                Befehlsübersicht für Uno:
                - help | h       : zeigt alle Befehle für Uno
                - exit | q       : verlässt das Spiel
                - game           : startet ein neues Spiel mit 2 Spielern
                - play <cardID>  : Legt die Karte mit dem gewünschten Index                
                """)
      case "game" =>
        Game()
        println("Neues Spiel gestartet")
      case _ =>
        println("Ungültige Eingabe, versuchen sie help")
    TUI()
}

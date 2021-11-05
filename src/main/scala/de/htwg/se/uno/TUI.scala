package de.htwg.se.uno

import scala.io.StdIn.readLine
import Field._

class TUI {

  def TUI(): Unit =
    val in = readLine("Befehle eingeben\n")
    in match
      case "exit" =>
      case _ =>
        println(menu(in))
        TUI()

    def menu(in: String): String = {
      val back = in match
        case "help" | "h" =>
          """
                Befehlsübersicht für Uno:
                - help | h      : zeigt alle Befehle für Uno
                - game          : startet ein neues Spiel mit 2 Spielern
                - play <Index>  : Legt die Karte mit dem gewünschten Index                
                """
        case "game" =>
          NewGame()
          "Neues Spiel gestartet"
        case _ => "Ungültige Eingabe, versuchen sie help"
      return back
    }

    def NewGame() =
      val p1 = readLine("Player1: ")
      val p2 = readLine("Player2: ")
      val cards = readLine("Kartenanzahl: ").toInt
      print(table(p1, p2, cards, cards))
}

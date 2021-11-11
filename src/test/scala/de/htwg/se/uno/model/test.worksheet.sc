import de.htwg.se.uno.model.Player
import de.htwg.se.uno.model.Card._
import de.htwg.se.uno.model.CardColor._
import de.htwg.se.uno.model.CardValue._

Player("Timo")
val p1 = Player("Spieler1")
p1.karten.size
p1.print()
p1.add(R0)
p1.karten.size
p1.karten(0)
p1.print()
p1.add(R1)

import de.htwg.se.uno.model.Game
import de.htwg.se.uno.model.Player._
import de.htwg.se.uno.model.toCard._
import de.htwg.se.uno.model.CardLayout._
import de.htwg.se.uno.model.Card._

val game = Game("player1", "player2", 0)
game.p1.karten.size
game.p2.karten.size
game.midCard.karten.size
game.midCard.karten(0)
game.midCard.karten = game.midCard.karten.updated(0, R0)
game.midCard.karten.toString()

game.add("p1", XX)
game.p1.karten.size
game.p1.karten(0)

game.add("p2", R0)
game.p2.karten.size

game.add("midstack", R0)
game.midCard.karten.size
game.midCard.karten(0)

game.add("hs", XX)

val game1 = Game("player1", "player2", 1)
game1.midCard.karten = game.midCard.karten.updated(0, R1)
game1.toString()
game1.p1.karten
game1.p1.karten = game.p1.karten.updated(0, B0)
game1.p2.karten = game.p2.karten.updated(0, G0)
game1.p1.karten
game1.midCard.karten
game1.toString()

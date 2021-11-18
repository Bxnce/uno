import de.htwg.se.uno.model.Game._
import de.htwg.se.uno.model.Game
import de.htwg.se.uno.model.Card._
import de.htwg.se.uno.model.Card
import de.htwg.se.uno.controller.Controller
import de.htwg.se.uno.controller.Controller._
import de.htwg.se.uno.aview.TUI

val game = Game("Bence", "Bence2", 7)

print(game.toString)

def place(ind: Int) =
  if (game.playerDiff % 2 == 0) { //player1

  }

def removeInd(ind: Int, vec: Vector[Card]): Vector[Card] =
  if (ind == 0) {
    return vec.drop(1)
  } else if (ind == vec.size - 1) {
    return vec.dropRight(1)
  }
  val (tmp1, tmp2) = vec.splitAt(ind)
  return tmp1.toVector ++ tmp2.toVector.drop(1)

var cardv = Vector(R0, R1, R2, R3, R4, R5)

removeInd(5, cardv)

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

val game2 = Game("player1", "player2", 0)
game2.p1.karten.size
game2.p2.karten.size
game2.midCard.karten.size
game2.midCard.karten(0)
game2.midCard.karten = game2.midCard.karten.updated(0, R0)
game2.midCard.karten.toString()

game2.add("p1", XX)
game2.p1.karten.size
game2.p1.karten(0)

game2.add("p2", R0)
game2.p2.karten.size

game2.add("midstack", R0)
game2.midCard.karten.size
game2.midCard.karten(0)

game2.add("hs", XX)

val game1 = Game("player1", "player2", 1)
game1.midCard.karten = game1.midCard.karten.updated(0, R1)
game1.toString()
game1.p1.karten
game1.p1.karten = game1.p1.karten.updated(0, B0)
game1.p2.karten = game1.p2.karten.updated(0, G0)
game1.p1.karten
game1.midCard.karten
game1.toString()

val game3 = Game("ASD", "ASD", 1)

val game4 = Game("Bence", "Timo", 0)
val controller = Controller(game4)
//controller.game.p1.karten = controller.game.p1.karten.updated(0, R0)
controller.game.add("P1", Y6) //liegt auf midStack
controller.game.add("P1", R0)
controller.game.add("P1", R1)
controller.game.add("P1", R2)
controller.game.add("P2", G0)
controller.game.add("P2", G1)
controller.game.add("P2", G2)
controller.next()
controller.place(0)
controller.next()

val tui = TUI(controller)

controller.game.p1.karten.size
controller.game.p2.karten.size

tui.convertinputString("add p1 R3") //shouldBe (tui.SUCCESS)
tui.convertinputString("add p2 G3") //shouldBe (tui.SUCCESS)
controller.game.p1.karten.size //shouldBe (4)
controller.game.p2.karten.size //shouldBe (4)
controller.game.p1.karten(3) //shouldBe (R3)
controller.game.p2.karten(3) //shouldBe (G3)

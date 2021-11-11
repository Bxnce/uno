import de.htwg.se.uno.model.Game._
import de.htwg.se.uno.model.Card
import de.htwg.se.uno.model.Card._
import de.htwg.se.uno.model.Game

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

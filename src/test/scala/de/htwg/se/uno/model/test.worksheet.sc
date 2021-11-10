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

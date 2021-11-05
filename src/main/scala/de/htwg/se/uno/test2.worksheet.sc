import de.htwg.se.uno.model.Card._
import de.htwg.se.uno.model.CardColor._
import de.htwg.se.uno.model.CardValue._
import de.htwg.se.uno.model.CardStack

//Initialisieren und Aktualisieren des Stapels von dem gezogen wird

val x = CardStack
x.fill()

print(x.stack)

val tmp = Y2

x.stack = x.stack + (tmp -> 10)

print(x.stack)

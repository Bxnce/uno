import de.htwg.se.uno.model.Card._
import de.htwg.se.uno.model.CardColor._
import de.htwg.se.uno.model.CardValue._
import de.htwg.se.uno.model.CardStack

//Initialisieren und Aktualisieren des Stapels von dem gezogen wird

val stack = CardStack()

print(stack.c)
stack.c += (Y7 -> 0)
print(stack.c)
stack.c(Y7)

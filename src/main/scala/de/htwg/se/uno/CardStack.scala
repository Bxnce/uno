package de.htwg.se.uno
import scala.collection.immutable.HashMap
import Card._

object CardStack {

  def fill(): HashMap[Card, Int] =
    val cardsArr = Card.values
    var tmp = HashMap(R0 -> 2, B0 -> 2, G0 -> 2, Y0 -> 2)

    return tmp
}

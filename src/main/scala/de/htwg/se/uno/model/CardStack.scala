package de.htwg.se.uno
package model

import scala.collection.immutable.HashMap
import Card._

class CardStack {
  var cards = HashMap[Card, Int]()
  Card.values.map(x => (x, 2)).map(x => (cards = cards + (x)))
}

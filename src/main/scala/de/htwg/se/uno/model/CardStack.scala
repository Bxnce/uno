package de.htwg.se.uno
package model

import scala.collection.immutable.HashMap
import Card._

class CardStack {
  var stack = HashMap[Card, Int]()

  def fill() =
    Card.values.map(x => (x, 2)).map(x => (stack = stack + (x)))
}

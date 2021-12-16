package de.htwg.se.uno
package model.gameComponent

import scala.collection.immutable.HashMap
import Card._

case class CardStack(cards: Map[Card, Int]) {

  def decrease(x: Card): CardStack =
    copy(cards.updated(x, cards.getOrElse(x, 2) - 1))

  def increase(x: Card): CardStack =
    copy(cards.updated(x, cards.getOrElse(x, 2) + 1))

}

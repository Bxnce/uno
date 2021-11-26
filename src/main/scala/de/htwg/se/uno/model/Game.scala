package de.htwg.se.uno
package model

import scala.io.StdIn.readLine
import Player._
import toCard._
import CardLayout._
import Card._
import scala.io.StdIn
import util._

case class Game(player1: String, player2: String, kartenAnzahl: Int)
    extends State:
  //Var's und Val's

  val p1s: State = new player1State(this)
  val p2s: State = new player2State(this)
  val p2n: State = new between12State(this)
  val p1n: State = new between21State(this)

  var currentstate: State = p1n

  var ERROR = 0

  var cardStack = CardStack()
  val midCard = Player(
    "midstack"
  )
  var cardsInDeck = Card.values.size - 1

  val r = scala.util.Random

  val p1 = Player(player1)
  val p2 = Player(player2)
  //Befüllen der Starthand der Spieler
  playerFill(kartenAnzahl)
  //1. Karte in der Mitte:
  take("midstack")
  //Funktionen des Spiels
  //added eine Spezifische Karte(als Card übergeben) auf die Hand eines Spielers
  def add(player: String, card: Card): Int =
    if (card.toString == "XX") {
      take(player)
    } else if (cardStack.cards(card) == 0) {
      take(player)
    } else if (
      player.equalsIgnoreCase("P1") || player.equalsIgnoreCase(p1.getName())
    ) {
      p1.add(card)
      cardStack.cards = cardStack.cards + (card -> (cardStack.cards(card) - 1))
      return 0;
    } else if (
      player.equalsIgnoreCase("P2") || player.equalsIgnoreCase(p2.getName())
    ) {
      p2.add(card)
      cardStack.cards = cardStack.cards + (card -> (cardStack.cards(card) - 1))
      return 0;
    } else if (player.equals("midstack")) {
      midCard.add(card)
      cardStack.cards = cardStack.cards + (card -> (cardStack.cards(card) - 1))
      return 0;
    } else {
      return -3;
    }
  //zieht eine zufällige Karte vom Stack und gibt sie dem Spieler auf die Hand -> dekrementiert die Anzahl der Karte auf dem Stack
  def take(player: String): Int =
    val rnd = r.nextInt(cardsInDeck - 1)
    return add(player, Card.values(rnd))

  def place(ind: Int, player: Player): Int =
    val tmp = midCard.karten(0)
    midCard.karten = midCard.karten.updated(0, player.karten(ind))
    player.removeInd(ind)
    cardStack.cards = cardStack.cards + (tmp -> ((cardStack.cards(tmp) + 1)))
    return 0

  //nächster Spieler ist dran
  def changeState() =
    currentstate.changeState()

  def playerFill(count: Int) =
    for (i <- 1 to count) {
      take("P1")
      take("P2")
    }
  override def toString: String =
    if (currentstate == p1s) {
      return p1.getName() + eol + p1.print() + eol + midCard.print() + eol + p2
        .printFiller() + p2
        .getName() + eol
    } else if (currentstate == p2s) {
      return p1.getName() + eol + p1.printFiller() + eol + midCard
        .print() + eol + p2
        .print() + p2
        .getName() + eol
    } else {
      return p1.getName() + eol + p1.printFiller() + eol + midCard
        .print() + eol + p2
        .printFiller() + p2
        .getName() + eol
    }

/*
case class Strategy(g: Game, s: String) {
  //var strategy = stratTake
  var strategy =
    s match
      case "take" =>
        strategy = stratTake
      case "toString" =>
        strategy = stratToStr

  def stratTake(str: String): Unit =
    g.currentstate match
      case g.p1s =>
        g.error = g.take("P1")
      case g.p2s =>
        g.error = g.take("P2")
      case _ =>
        g.error = -4

  def stratToStr(str: String): Unit =
    g.currentstate match
      case g.p1s =>
}
 */

package de.htwg.se.uno
package model

import scala.io.StdIn.readLine
import Player._
import toCard._
import CardLayout._
import Card._
import scala.io.StdIn
import util._
import controller._
import Console.{RED, GREEN, RESET}

object Game {
  def newGame(player1: String, player2: String): Game =
    new Game(player1, player2).playerFill(7).take("midstack")

}

case class Game(
    pList: List[Player],
    currentstate: State,
    ERROR: Int,
    cardStack: CardStack,
    midCard: Player
):
  def this(player1: String, player2: String) =
    this(
      List(Player(player1, Vector[Card]()), Player(player2, Vector[Card]())),
      between21State,
      0,
      new CardStack(
        Card.values.map(x => (x, 2)).toMap
      ),
      Player("midcard", Vector[Card]())
    )

  //def this(): Game = this(player1,player2,kartenAnzahl)
  //Var's und Val'

  val cardsInDeck = Card.values.size - 1
  val r = scala.util.Random

  //Funktionen des Spiels
  //added eine Spezifische Karte(als Card übergeben) auf die Hand eines Spielers
  def add(player: String, card: Card): Game =
    if (card.toString == "XX") {
      take(player)
    } else if (cardStack.cards(card) == 0) {
      take(player)
    } else if (
      player
        .equalsIgnoreCase("P1") || player.equalsIgnoreCase(pList(0).getName())
    ) {
      copy(
        pList.updated(0, pList(0).add(card)),
        currentstate,
        ERROR,
        cardStack.decrease(card),
        midCard
      )
    } else if (
      player
        .equalsIgnoreCase("P2") || player.equalsIgnoreCase(pList(1).getName())
    ) {
      copy(
        pList.updated(1, pList(1).add(card)),
        currentstate,
        ERROR,
        cardStack.decrease(card),
        midCard
      )
    } else if (player.equals("midstack")) {
      copy(
        pList,
        currentstate,
        ERROR,
        cardStack.decrease(card),
        midCard.add(card)
      )
    } else {
      this
    }

  //zieht eine zufällige Karte vom Stack und gibt sie dem Spieler auf die Hand -> dekrementiert die Anzahl der Karte auf dem Stack
  def take(player: String): Game =
    val rnd = r.nextInt(cardsInDeck - 1)
    add(player, Card.values(rnd))

  def place(ind: Int, player: Int): Game =
    if (checkPlace(ind, player)) {
      copy(
        pList.updated(player, pList(player).removeInd(ind)),
        currentstate,
        ERROR,
        cardStack.increase(pList(player).karten(ind)),
        Player(
          midCard.name,
          midCard.karten.updated(0, pList(player).karten(ind))
        )
      )
    } else {
      Console.println(
        s"${RED}!!!Karte kann nicht gelegt werden!!!${RESET}"
      )
      this
    }

  def checkPlace(ind: Int, player: Int): Boolean =
    if (
      ((midCard
        .karten(0)
        .getColor == pList(player).karten(ind).getColor) || (midCard
        .karten(0)
        .getValue == pList(player).karten(ind).getValue))
    ) {
      true
    } else {
      false
    }
  def checkWin(player: Player): Boolean =
    if (player.karten.isEmpty) {
      return true
    }
    return false
  def setError(err: Int): Game =
    copy(
      pList,
      currentstate,
      err,
      cardStack,
      midCard
    )
  def playerFill(count: Int): Game =
    var tmp = this
    for (i <- 1 to count) {
      tmp = tmp.take("P1")
      tmp = tmp.take("P2")
    }
    tmp
  override def toString: String =
    if (currentstate == player1State) {
      return pList(0).getName() + eol + pList(0).print() + eol + midCard
        .print() + eol + pList(1)
        .printFiller() + pList(1)
        .getName() + eol
    } else if (currentstate == player2State) {
      return pList(0).getName() + eol + pList(0).printFiller() + eol + midCard
        .print() + eol + pList(1)
        .print() + pList(1)
        .getName() + eol
    } else {
      return pList(0).getName() + eol + pList(0).printFiller() + eol + midCard
        .print() + eol + pList(1)
        .printFiller() + pList(1)
        .getName() + eol
    }

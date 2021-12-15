package de.htwg.se.uno
package model

import scala.io.StdIn.readLine
import Player._
import toCard._
import CardLayout._
import Card._
import scala.io.StdIn
import util._
import Console.{RED, GREEN, RESET}
import scala.util.{Try, Success, Failure}

object Game {
  def newGame(player1: String, player2: String, startstate: State): Game =
    new Game(player1, player2, startstate).playerFill(7).take("midstack")

}

case class Game(
    pList: List[Player],
    currentstate: State,
    ERROR: Int,
    cardStack: CardStack,
    midCard: Player
):
  def this(player1: String, player2: String, startstate: State) =
    this(
      List(
        Player(player1, Vector[Card](), false),
        Player(player2, Vector[Card](), false)
      ),
      startstate,
      0,
      new CardStack(
        Card.values.map(x => (x, 2)).toMap
      ),
      Player("midcard", Vector[Card](), false)
    )

  //def this(): Game = this(player1,player2,kartenAnzahl)
  //Var's und Val'

  val cardsInDeck = Card.values.size - 1
  val r = scala.util.Random

  //Funktionen des Spiels
  //added eine Spezifische Karte(als Card übergeben) auf die Hand eines Spielers
  def add(player: String, card: Card): Game =
    //evtl. erst die Abfrage ob es ein richtiger Spieler ist da man sonst unnötig einmal take aufruft
    if (card.toString == "XX") {
      take(player)
    } else if (cardStack.cards(card) == 0) {
      take(player)
    } else if (
      player
        .equalsIgnoreCase("P1") || player.equalsIgnoreCase(pList(0).name)
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
        .equalsIgnoreCase("P2") || player.equalsIgnoreCase(pList(1).name)
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
      setError(-1)
    }

  //zieht eine zufällige Karte vom Stack und gibt sie dem Spieler auf die Hand -> dekrementiert die Anzahl der Karte auf dem Stack
  def take(player: String): Game =
    val rnd = r.nextInt(cardsInDeck - 1)
    add(player, Card.values(rnd))

  def checkPlace(ind: Int, player: Int): Boolean =
    Try {
      (midCard
        .karten(0)
        .getColor == pList(player).karten(ind).getColor) || (midCard
        .karten(0)
        .getValue == pList(player).karten(ind).getValue)
    } match {
      case Success(x) => x
      case Failure(y) => false
    }

  def place(ind: Int, player: Int): Game =
    if (checkPlace(ind, player) && !pList(player).placed) {
      copy(
        pList.updated(player, pList(player).removeInd(ind)),
        currentstate,
        0,
        cardStack.increase(pList(player).karten(ind)),
        Player(
          midCard.name,
          midCard.karten.updated(0, pList(player).karten(ind)),
          false
        )
      )
    } else {
      Console.println(
        s"${RED}!!!Karte kann nicht gelegt werden!!!${RESET}"
      )
      setError(-1)
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

  def addTest(p: String, card: Card): Game =
    //val tmp = midCard.karten(0)
    copy(
      pList,
      currentstate,
      ERROR,
      cardStack.decrease(card), //.increase(tmp)
      midCard.removeInd(0).add(card)
    )

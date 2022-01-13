package de.htwg.se.uno
package model.gameComponent.gameBaseImpl

import model.gameComponent.gameInterface

import scala.io.StdIn.readLine
import Player._
import toCard._
import CardLayout._
import Card._
import scala.io.StdIn
import util._
import Console.{RED, GREEN, RESET}
import scala.util.{Try, Success, Failure}

case class Game(
    pList: List[Player],
    currentstate: State,
    ERROR: Int,
    cardStack: CardStack,
    midCard: Player,
    winner: Int
) extends gameInterface:
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
      Player("midcard", Vector[Card](), false),
      -1
    )

  def init(): Game =
    this
      .playerFill(7)
      .take("midcard")

  def getNext(game: gameInterface, player: Int, state: State): Game =
    if (player == -1) {
      Game(game.pList, state, 0, game.cardStack, game.midCard, player)
    } else {
      Game(
        game.pList
          .updated(player, game.pList(player).setFalse()),
        state,
        0,
        game.cardStack,
        game.midCard,
        player
      )
    }

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
    } else if (player.equals("midcard")) {
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
    val rnd = r.nextInt(cardsInDeck) // -1 entfernt
    add(player, Card.values(rnd))

  def checkPlace(ind: Int, player: Int): Boolean =
    Try {
      (midCard
        .karten(0)
        .getColor == pList(player).karten(ind).getColor) || (midCard
        .karten(0)
        .getValue == pList(player).karten(ind).getValue || pList(player)
        .karten(ind)
        .getColor == CardColor.Black)
    } match {
      case Success(x) => x
      case Failure(y) => false
    }
  /*if(midCard.karten(0).getColor.equals(pList(player).karten(ind).getColor) || midCard.karten(0).getValue.equals(pList(player).karten(ind).getValue))
      {true} else {
        false
      }*/

  def place(ind: Int, player: Int): Game =
    if (checkPlace(ind, player) && !pList(player).placed) {
      val tmpVal = pList(player).karten(ind).getValue
      var tmp: Game = copy(
        pList.updated(player, pList(player).removeInd(ind)),
        currentstate,
        0,
        cardStack.increase(
          pList(player).karten(ind)
        ), //warum wird die Karte vom spieler und nicht die vom midstack dazugelegt ??
        Player(
          midCard.name,
          midCard.karten.updated(0, pList(player).karten(ind)),
          false
        )
      )
      tmpVal match
        case CardValue.Take2 =>
          player match
            case 0 =>
              tmp = tmp.take("P2")
              tmp = tmp.take("P2")
              tmp.copy(tmp.pList.updated(1, tmp.pList(1).setTrue()))
            case 1 =>
              tmp = tmp.take("P1")
              tmp = tmp.take("P1")
              tmp.copy(tmp.pList.updated(0, tmp.pList(0).setTrue()))
        case CardValue.Skip =>
          player match
            case 0 =>
              tmp.copy(tmp.pList.updated(1, tmp.pList(1).setTrue()))
            case 1 =>
              tmp.copy(tmp.pList.updated(0, tmp.pList(0).setTrue()))
        case CardValue.Wildcard =>
          //chooseColor("Green")
          tmp
        case _ =>
          tmp
    } else {
      Console.println(
        s"${RED}!!!Karte kann nicht gelegt werden!!!${RESET}"
      )
      setError(-1)
    }

  def chooseColor(farbe: String): Game = //funktioniert aktuell noch nicht
    farbe match
      case "Blue" =>
        copy(pList, currentstate, ERROR, cardStack, midCard.add(B))
      case "Red" =>
        copy(pList, currentstate, ERROR, cardStack, midCard.add(R))
      case "Green" =>
        copy(pList, currentstate, ERROR, cardStack, midCard.add(G))
      case "Yellow" =>
        copy(pList, currentstate, ERROR, cardStack, midCard.add(Y))

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
    copy(
      pList,
      currentstate,
      ERROR,
      cardStack.decrease(card), //.increase(tmp)
      midCard.removeInd(0).add(card)
    )

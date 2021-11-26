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

case class Game(player1: String, player2: String, kartenAnzahl: Int):
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

  val pList = (Player(player1), Player(player2))
  /*
  val p1 = Player(player1)
  val p2 = Player(player2)*/

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
      player
        .equalsIgnoreCase("P1") || player.equalsIgnoreCase(pList(0).getName())
    ) {
      pList(0).add(card)
      cardStack.cards = cardStack.cards + (card -> (cardStack.cards(card) - 1))
      return 0;
    } else if (
      player
        .equalsIgnoreCase("P2") || player.equalsIgnoreCase(pList(1).getName())
    ) {
      pList(1).add(card)
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

  def place(ind: Int, player: Player) =
    if (checkPlace(ind, player)) {
      player.placed = true
      val tmp = midCard.karten(0)
      midCard.karten = midCard.karten.updated(0, player.karten(ind))
      player.removeInd(ind)
      cardStack.cards = cardStack.cards + (tmp -> ((cardStack.cards(tmp) + 1)))
    } else {
      Console.println(
        s"${RED}!!!Nur eine Karte legen!!!${RESET}"
      )
    }

  def checkPlace(ind: Int, player: Player): Boolean =
    if (
      ((midCard.karten(0).getColor == player.karten(ind).getColor) || (midCard
        .karten(0)
        .getValue == player.karten(ind).getValue)) && !player.placed
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
      return pList(0).getName() + eol + pList(0).print() + eol + midCard
        .print() + eol + pList(1)
        .printFiller() + pList(1)
        .getName() + eol
    } else if (currentstate == p2s) {
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

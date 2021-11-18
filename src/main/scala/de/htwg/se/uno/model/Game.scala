package de.htwg.se.uno
package model

import scala.io.StdIn.readLine
import Player._
import toCard._
import CardLayout._
import Card._
import scala.io.StdIn

case class Game(player1: String, player2: String, kartenAnzahl: Int):
  //Var's und Val's
  var playerDiff: Int = 3
  var cardStack = CardStack()
  val midCard = Player(
    "midstack"
  )
  var cardsInDeck =
    Card.values.size - 1
  val r = scala.util.Random
  val p1 = Player(player1)
  val p2 = Player(player2)
  //Befüllen der Starthand der Spieler
  playerFill(kartenAnzahl)
  //1. Karte in der Mitte:
  take("midstack")
  //Funktionen des Spiels
  //added eine Spezifische Karte(als String übergeben) auf die Hand eines Spielers
  def add(player: String, karte: String): Int =
    val card = getCard(karte)
    if (card.toString == "XX") {
      return -1;
    } else if (cardStack.cards(card) == 0) {
      return -2;
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
    } else {
      return -3;
    }

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
  //zieht eine zufällig Karte und fügt diese dem Spieler hinzu, der an der Reihe ist
  def take(): Int =
    if (playerDiff % 4 == 0) {
      return take("P1")
    } else if (playerDiff % 4 == 2) {
      return take("P2")
    } else {
      return -4
    }

  def place(ind: Int): Int =
    if (playerDiff % 4 == 0) { //player1
      val tmp = midCard.karten(0)
      //midCard.karten.updated(0, p1.karten(ind))
      midCard.karten = midCard.karten.updated(0, p1.karten(ind))
      p1.removeInd(ind)
      cardStack.cards = cardStack.cards + (tmp -> ((cardStack.cards(tmp) + 1)))
      return 0
    } else if (playerDiff % 4 == 2) {
      val tmp = midCard.karten(0)
      midCard.karten = midCard.karten.updated(0, p2.karten(ind))
      p2.removeInd(ind)
      cardStack.cards = cardStack.cards + (tmp -> ((cardStack.cards(tmp) + 1)))
      return 0
    } else {
      return -4
    }
  //nächster Spieler ist dran
  def next() =
    playerDiff += 1

  def playerFill(count: Int) =
    for (i <- 1 to count) {
      take("P1")
      take("P2")
    }
  override def toString: String =
    if (playerDiff % 4 == 0) {
      return p1.getName() + eol + p1.print() + eol + midCard.print() + eol + p2
        .printFiller() + p2
        .getName() + eol
    } else if (playerDiff % 4 == 2) {
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
    return p1.getName() + eol + p1.print() + eol + midCard.print() + eol +
      p2.print() +
      p2.getName() + eol

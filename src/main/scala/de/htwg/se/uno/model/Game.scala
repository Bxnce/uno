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
  var playerDiff: Int = 0
  var cardStack = CardStack()
  val midCard = Player(
    "midstack"
  ) //als Spieler, damit die gleiche Print benutzen kann und die Abfragen werden einfacher.
  val r = scala.util.Random
  val p1 = Player(player1)
  val p2 = Player(player2)
  val startC = kartenAnzahl
  //Befüllen der Starthand der Spieler
  for (i <- 1 to startC) {
    take("p1")
    take("p2")
  }
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
      take(
        player
      ) //Problem, falls der ganze Stack leer sein sollte, da dauerschleife.
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
    val rnd = r.nextInt(39)
    return add(player, Card.values(rnd))
  //zieht eine zufällig Karte und fügt diese dem Spieler hinzu, der an der Reihe ist
  def take(): Int =
    if (playerDiff % 2 == 0) {
      return take("P1")
    } else {
      return take("P2")
    }

  //nächster Spieler ist dran
  def next() =
    playerDiff += 1
  override def toString: String =
    return p1.getName() + eol + p1.print() + eol + midCard.print() + eol + p2
      .print() + p2
      .getName() + eol

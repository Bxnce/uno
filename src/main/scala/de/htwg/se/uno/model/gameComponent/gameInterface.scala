package de.htwg.se.uno
package model.gameComponent

import util.State

trait gameInterface {
  val pList: List[Player]
  val currentstate: State
  val ERROR: Int
  val cardStack: CardStack
  val midCard: Player
  val cardsInDeck: Int

  def add(player: String, card: Card): gameInterface
  def take(player: String): gameInterface
  def checkPlace(ind: Int, player: Int): Boolean
  def place(ind: Int, player: Int): gameInterface
  def checkWin(player: Player): Boolean
  def setError(err: Int): gameInterface
  def playerFill(count: Int): gameInterface
  def addTest(p: String, card: Card): gameInterface
  def apply(player1: String, player2: String, startstate: State): gameInterface
  def init(): gameInterface

}

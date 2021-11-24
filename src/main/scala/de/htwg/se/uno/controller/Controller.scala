package de.htwg.se.uno
package controller

import model.Game
import model.toCard._
import util.Observable
import Console.{RED, RESET}

case class Controller(var game: Game) extends Observable:
  def add(p: String, karte: String): Int =
    val err = game.add(p, karte)
    notifyObservers
    return err

  def take(player: String): Int =
    val err = game.take(player)
    notifyObservers
    return err

  def take(): Int =
    val err = game.take()
    notifyObservers
    return err

  def place(ind: Int): Int = //Index auf outofbounds checken
    val err = game.place(ind)
    notifyObservers
    return err

  def next() =
    game.next()
    notifyObservers

  override def toString: String =
    return game.toString()

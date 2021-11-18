package de.htwg.se.uno
package controller

import model.Game
import model.toCard._
import util.Observable
import Console.{RED, RESET}

case class Controller(var game: Game) extends Observable:
  def add(p: String, karte: String): Int =
    val err = game.add(p, karte)
    if (err == -3) {
      Console.println(s"${RED}!!!Falschen Namen eingegeben!!!${RESET}")
    } else if (err == -2) {
      Console.println(s"${RED}!!!Karte ist nichtmehr im Stack!!!${RESET}")
    } else if (err == -1) {
      Console.println(s"${RED}!!!Ungültige Karte!!!${RESET}")
    } else {
      notifyObservers
    }
    return err

  def take(player: String): Int =
    val err = game.take(player)
    if (err == -3) {
      Console.println(s"${RED}!!!Falschen Namen eingegeben!!!${RESET}")
    } else if (err == -2) {
      Console.println(s"${RED}!!!Karte ist nichtmehr im Stack!!!${RESET}")
    } else if (err == -1) {
      Console.println(s"${RED}!!!Ungültige Karte!!!${RESET}")
    } else {
      notifyObservers
    }
    return err
  def take(): Int =
    val err = game.take()
    if (err == -2) {
      Console.println(s"${RED}!!!Karte ist nichtmehr im Stack!!!${RESET}")
    } else if (err == -1) {
      Console.println(s"${RED}!!!Ungültige Karte!!!${RESET}")
    } else {
      notifyObservers
    }
    return err
  def place(ind: Int) =
    notifyObservers

  def next() =
    game.next()
    notifyObservers

  override def toString: String =
    return game.toString()

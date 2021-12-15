package de.htwg.se.uno
package controller.controllerComponent

import util.Invoker
import model._
import util.Observable
trait controllerInterface extends Observable {
  var game: Game
  val invoker: Invoker
  def take(): Unit
  def place(ind: Int): Unit
  def next(): Unit
  def undo(): Unit
  def redo(): Unit
  def newG(): Unit
  def newG(p1: String, p2: String): Unit
  override def toString: String
}

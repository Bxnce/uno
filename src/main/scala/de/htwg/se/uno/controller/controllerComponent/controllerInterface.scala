package de.htwg.se.uno
package controller.controllerComponent

import util.Invoker
import model.gameComponent._
import util.Observable

trait controllerInterface extends Observable {
  var game: gameInterface
  val invoker: Invoker
  def take(): Unit
  def place(ind: Int): Unit
  def next(): Unit
  def undo(): Unit
  def redo(): Unit
  def newG(p1: String, p2: String): Unit
  def WinG(p1: String, p2: String): Unit
  def colorChoose(color: String): Unit
  override def toString: String
  def load: Unit
  def save: Unit
}

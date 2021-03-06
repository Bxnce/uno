package de.htwg.se.uno
package controller.controllerComponent.controllerBaseImpl

import controller.controllerComponent._

import com.google.inject.name.Names
import com.google.inject.{Guice, Inject}
import scala.io.StdIn.readLine
import model.gameComponent.gameBaseImpl.Game
import model.gameComponent.gameBaseImpl.toCard._
import model.fileIOComponent.FileIOInterface
import util.Observable
import util.Invoker
import model.gameComponent._
import Console.{RED, RESET}

case class Controller @Inject() (var game: gameInterface)
    extends controllerInterface:
  val invoker = new Invoker

  def take() =
    game = invoker.doStep(UnoCommand(this, "take"))
    notifyObservers

  def place(ind: Int) =
    game = invoker.doStep(UnoCommand(ind, this))
    notifyObservers

  def next() =
    game = invoker.doStep(UnoCommand(this, "next"))
    notifyObservers

  def undo() =
    game = invoker.undoStep.getOrElse(game)
    notifyObservers

  def redo() =
    game = invoker.redoStep.getOrElse(game)
    notifyObservers

  def newG(p1: String, p2: String) =
    game = new Game(p1, p2, between21State).init()
    notifyObservers

  def WinG(p1: String, p2: String) =
    game = new Game(p1, p2, between21State).init()

  def colorChoose(color: String) =
    game = invoker.doStep(UnoCommand(color, this))
    notifyObservers

  def save: Unit =
    def fileIO =
      Guice.createInjector(new UnoModule).getInstance(classOf[FileIOInterface])
    fileIO.save(game)
    notifyObservers

  def load: Unit =
    def fileIO =
      Guice.createInjector(new UnoModule).getInstance(classOf[FileIOInterface])
    game = fileIO.load
    notifyObservers

  override def toString: String =
    UnoCommand(this, "print").toString

package de.htwg.se.uno
package util

import model.gameComponent.gameInterface
import controller.controllerComponent.WinCommand

class Invoker {
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  def doStep(command: Command): gameInterface = {
    command match
      case e: WinCommand =>
      case _ =>
        undoStack = command :: undoStack
    command.execute
  }

  def undoStep: Option[gameInterface] = {
    undoStack match {
      case Nil => None
      case head :: stack => {
        undoStack = stack
        redoStack = head :: redoStack
        Some(head.undoStep)
      }
    }
  }

  def redoStep: Option[gameInterface] = {
    redoStack match {
      case Nil => None
      case head :: stack => {
        redoStack = stack
        undoStack = head :: undoStack
        Some(head.redoStep)
      }
    }
  }
}

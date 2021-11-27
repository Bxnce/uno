package de.htwg.se.uno
package util

import model._

class Invoker {
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil
  def doStep(command: Command) = {
    undoStack = command :: undoStack
    command.execute;
  }

  def undoStep = {
    undoStack match {
      case Nil =>
      case head :: stack => {
        println("undo")
        head.undoStep
        undoStack = stack
        redoStack = head :: redoStack
      }
    }
  }
  def redoStep = {
    redoStack match {
      case Nil =>
      case head :: stack => {
        println("redo")
        head.redoStep
        redoStack = stack
        undoStack = head :: undoStack
      }
    }
  }
}

package de.htwg.se.uno
package util

import controller.Controller
import model.Game
import controller.UnoCommand._

trait Command(controller: Controller) {
  def execute: Game
  def undoStep: Game
  def redoStep: Game
}

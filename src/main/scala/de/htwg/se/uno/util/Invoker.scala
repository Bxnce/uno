package de.htwg.se.uno
package util

import model._

class Invoker {

  def doStep(command: Command) = {
    command.execute;
  }

}

package de.htwg.se.uno

import com.google.inject.{AbstractModule, Guice, Inject}
import net.codingwell.scalaguice.ScalaModule
import controller.controllerComponent.controllerInterface
import controller.controllerComponent.controllerBaseImpl.Controller
import model.gameComponent.gameInterface
import model.gameComponent.gameBaseImpl.Game

class UnoModule extends AbstractModule with ScalaModule {

  def configure(): Unit = {
    bind[gameInterface].to[Game]
    bind[controllerInterface].to[Controller]
    bind[gameInterface]
      .annotatedWithName("start")
      .toInstance(new Game("place_h", "place_h", between21State))
  }

}

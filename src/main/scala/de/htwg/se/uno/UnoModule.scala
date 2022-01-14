package de.htwg.se.uno

import com.google.inject.{AbstractModule, Guice, Inject}
import net.codingwell.scalaguice.ScalaModule
import controller.controllerComponent.controllerInterface
import controller.controllerComponent.controllerBaseImpl.Controller
import controller.controllerComponent.controllerBaseImpl.between21State
import model.gameComponent.gameInterface
import model.gameComponent.gameBaseImpl.Game

class UnoModule extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[gameInterface]).to(classOf[Game])
    bind(classOf[controllerInterface]).to(classOf[Controller])
    /*bind(classOf[gameInterface])
      .annotatedWithName("start")
      .toInstance(new Game("place_h", "place_h", between21State))*/
  }

}

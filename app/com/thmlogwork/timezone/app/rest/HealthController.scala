package com.thmlogwork.timezone.app.rest

import io.circe.generic.auto._
import io.circe.syntax._
import javax.inject._
import play.api.mvc._

case class Health(health: String = "ok")

@Singleton
class HealthController @Inject()(cc: ControllerComponents)
    extends AbstractController(cc) {

  def getHealth() = Action {
    Ok(Health().asJson.noSpaces)
  }

}

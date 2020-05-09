package com.thmlogwork.timezone.app.rest

import io.circe.generic.auto._
import io.circe.syntax._
import javax.inject._
import play.api.Logger
import play.api.mvc._

case class Health(health: String = "ok")

@Singleton
class HealthController @Inject()(cc: ControllerComponents)
    extends AbstractController(cc) {

  val logger = Logger(classOf[HealthController])

  def getHealth() = Action {
    logger.info(s"Health endpoint called")
    Ok(Health().asJson.noSpaces)
  }

}

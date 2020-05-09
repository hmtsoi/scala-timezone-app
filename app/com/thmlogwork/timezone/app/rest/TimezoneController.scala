package com.thmlogwork.timezone.app.rest

import com.thmlogwork.timezone.app.domain.{LatLngFactory, TimezoneService}
import com.thmlogwork.timezone.app.persistence.EntityNotFoundException
import io.circe.generic.auto._
import io.circe.syntax._
import javax.inject._
import play.api.Logger
import play.api.mvc._

import scala.concurrent.ExecutionContext
@Singleton
class TimezoneController @Inject()(
  timezoneService: TimezoneService,
  cc: ControllerComponents
)(implicit ec: ExecutionContext)
    extends AbstractController(cc) {

  val logger = Logger(classOf[TimezoneController])

  def getTimezoneInfo(param: String) =
    Action.async { implicit request =>
      val latLng = LatLngFactory.fromLatLngStrArr(param)
      timezoneService
        .getTimezoneInfo(latLng)
        .map { result =>
          val response = TimezoneInfoResponseFactory.toResponse(result)
          Ok(response.asJson.noSpaces)
        }
        .recover {
          case e: EntityNotFoundException =>
            logger.warn(s"No timezone info found for $latLng")
            NotFound(e.getMessage)
        }
    }
}

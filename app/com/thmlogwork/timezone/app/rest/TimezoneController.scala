package com.thmlogwork.timezone.app.rest

import com.thmlogwork.timezone.app.domain.{
  LatLng,
  LatLngFactory,
  TimezoneService
}
import com.thmlogwork.timezone.app.persistence.EntityNotFoundException
import io.circe.generic.auto._
import io.circe.syntax._
import javax.inject._
import play.api.Logger
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}
@Singleton
class TimezoneController @Inject()(
  timezoneService: TimezoneService,
  cc: ControllerComponents
)(implicit ec: ExecutionContext)
    extends AbstractController(cc) {

  val logger = Logger(classOf[TimezoneController])

  def getTimezoneInfo(param: String): Action[AnyContent] =
    Action.async { implicit request =>
      val i = Try(LatLngFactory.fromLatLngStrArr(param))
      i match {
        case Failure(e) => Future.successful(BadRequest(e.getMessage))
        case Success(v) => getTimezoneInfoAndMapToResponse(v)
      }
    }

  private def getTimezoneInfoAndMapToResponse(latLng: LatLng) =
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

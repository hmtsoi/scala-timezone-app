package com.thmlogwork.timezone.app.persistence

import anorm.SqlParser.get
import anorm._
import com.thmlogwork.timezone.app.domain.{
  LatLng,
  TimezoneInfo,
  TimezoneRepository
}
import javax.inject.{Inject, Singleton}
import play.api.db.DBApi

import scala.concurrent.Future

case class TimezoneInfoEntity(gid: Int,
                              utcFormat: Option[String],
                              timezoneName: Option[String])

@Singleton
class TimezoneRepositoryImpl @Inject()(dbapi: DBApi)(
  implicit ec: DatabaseExecutionContext
) extends TimezoneRepository {

  private val db = dbapi.database("default")

  private val simple = {
    get[Int]("timezones.gid") ~
      get[Option[String]]("timezones.utc_format") ~
      get[Option[String]]("timezones.tz_name1st") map {
      case gid ~ utcFormat ~ timezoneName =>
        TimezoneInfoEntity(gid, utcFormat, timezoneName)
    }
  }

  private val toGeometryPoint =
    (y: LatLng) => s"SRID=4326;POINT(${y.lng} ${y.lat})"

  def findByLatLng(latLng: LatLng): Future[TimezoneInfo] =
    Future {
      val geometry = toGeometryPoint(latLng)
      val q: SimpleSql[Row] =
        SQL"""
           SELECT * FROM timezones e
           WHERE st_contains(e.geom, $geometry::geometry) = TRUE
           """
      db.withConnection { implicit connection =>
        q.as(simple.singleOpt) match {
          case Some(e) =>
            TimezoneInfo(e.gid, e.utcFormat.get, e.timezoneName.get)
          case _ => throw new EntityNotFoundException(s"Not found for $latLng")
        }
      }
    }(ec)

}

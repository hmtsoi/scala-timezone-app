package com.thmlogwork.timezone.app.domain

import javax.inject.{Inject, Singleton}

import scala.concurrent.Future

@Singleton
class TimezoneServiceImpl @Inject()(timezoneRepo: TimezoneRepository)
    extends TimezoneService {

  override def getTimezoneInfo(latLng: LatLng): Future[TimezoneInfo] =
    timezoneRepo findByLatLng latLng

}

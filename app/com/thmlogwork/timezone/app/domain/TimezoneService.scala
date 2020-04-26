package com.thmlogwork.timezone.app.domain

import scala.concurrent.Future

trait TimezoneService {

  def getTimezoneInfo(latLng: LatLng): Future[TimezoneInfo]

}

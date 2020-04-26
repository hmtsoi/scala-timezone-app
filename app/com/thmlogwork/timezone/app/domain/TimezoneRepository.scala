package com.thmlogwork.timezone.app.domain

import scala.concurrent.Future

trait TimezoneRepository {

  def findByLatLng(latLng: LatLng): Future[TimezoneInfo]

}

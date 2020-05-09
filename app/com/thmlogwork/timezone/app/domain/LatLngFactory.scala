package com.thmlogwork.timezone.app.domain

case class LatLng(lat: Double, lng: Double)

object LatLngFactory {

  def fromLatLngStrArr(arr: String) = {

    val strArr = arr.split(",")
    val lat = strArr(0).toDouble
    val lng = strArr(1).toDouble
    if (math.abs(lat) > 90) {
      throw new IllegalArgumentException(
        "Latitude should be between -90 and 90"
      )
    }
    if (math.abs(lng) > 180) {
      throw new IllegalArgumentException(
        "Longitude should be between -180 and 180"
      )
    }

    LatLng(lat, lng)
  }

}

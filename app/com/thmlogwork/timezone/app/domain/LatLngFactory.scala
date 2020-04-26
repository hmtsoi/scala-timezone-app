package com.thmlogwork.timezone.app.domain

case class LatLng(lat: Double, lng: Double)

object LatLngFactory {

  def fromLatLngStrArr(arr: String) = {

    val strArr = arr.split(",")
    val lat = strArr(0).toDouble
    val lng = strArr(1).toDouble

    LatLng(lat, lng)
  }

}

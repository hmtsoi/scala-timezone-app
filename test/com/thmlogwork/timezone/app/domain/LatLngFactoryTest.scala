package com.thmlogwork.timezone.app.domain

import org.scalatestplus.play.PlaySpec

class LatLngFactoryTest extends PlaySpec {

  "Factory" must {
    "generate latLng correctly" in {
      val str = "23.2,-161"
      val latLng = LatLngFactory.fromLatLngStrArr(str)

      latLng.lat mustBe 23.2
      latLng.lng mustBe -161.0
    }
    "throw RuntimeException when latLng does not exist" in {
      val str = "23.2,-191"
      a[IllegalArgumentException] must be thrownBy {
        LatLngFactory.fromLatLngStrArr(str)
      }
    }
  }

}

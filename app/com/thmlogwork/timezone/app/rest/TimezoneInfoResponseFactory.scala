package com.thmlogwork.timezone.app.rest

import java.time.{Instant, ZoneId, ZoneOffset}

import com.thmlogwork.timezone.app.domain.TimezoneInfo

case class TimezoneInfoResponse(timezoneName: String,
                                utcFormat: String,
                                currentLocalTime: String,
                                currentUtcTime: String)

object TimezoneInfoResponseFactory {

  def toResponse(info: TimezoneInfo) = {

    val timezoneName = info.timezoneName
    val utcFormat = info.utcFormat
    val currentLocalTime =
      Instant.now.atZone(ZoneId.of(timezoneName)).toLocalDateTime.toString
    val currentUtcTime = Instant.now.atOffset(ZoneOffset.UTC).toString

    TimezoneInfoResponse(
      timezoneName,
      utcFormat,
      currentLocalTime,
      currentUtcTime
    )
  }

}

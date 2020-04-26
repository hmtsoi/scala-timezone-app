package com.thmlogwork.timezone.app

import com.thmlogwork.timezone.app.domain.{
  TimezoneRepository,
  TimezoneService,
  TimezoneServiceImpl
}
import com.thmlogwork.timezone.app.persistence.TimezoneRepositoryImpl
import play.api.inject.Binding
import play.api.{Configuration, Environment}

class MainModule extends play.api.inject.Module {

  override def bindings(
    environment: Environment,
    configuration: Configuration
  ): collection.Seq[Binding[_]] = {

    Seq(
      bind[TimezoneService].to[TimezoneServiceImpl],
      bind[TimezoneRepository].to[TimezoneRepositoryImpl]
    )
  }

}

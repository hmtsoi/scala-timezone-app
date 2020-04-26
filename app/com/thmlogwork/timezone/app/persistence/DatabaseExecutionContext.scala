package com.thmlogwork.timezone.app.persistence

import akka.actor.ActorSystem
import javax.inject._
import play.api.libs.concurrent.CustomExecutionContext

class DatabaseExecutionContext @Inject()(system: ActorSystem)
    extends CustomExecutionContext(system, "database.dispatcher")

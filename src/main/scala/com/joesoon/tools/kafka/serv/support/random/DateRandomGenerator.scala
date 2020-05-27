package com.joesoon.tools.kafka.serv.support.random

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

import com.joesoon.tools.kafka.serv.{Generator, PropertyConfig}

import scala.util.Random

class DateRandomGenerator extends Generator[String]{

  private[this] val random:Random = new Random

  override def genValue(propertyConfig: PropertyConfig): String = {
    val formatter = DateTimeFormatter.ofPattern(propertyConfig.formatter)
    val localDateTime = LocalDateTime.now
      .minus(random.nextInt(7),ChronoUnit.DAYS)
      .minus(random.nextInt(12),ChronoUnit.MONTHS)
      .minus(random.nextInt(60),ChronoUnit.MINUTES)
      .minus(random.nextInt(60),ChronoUnit.SECONDS)
       formatter.format(localDateTime)
  }
}
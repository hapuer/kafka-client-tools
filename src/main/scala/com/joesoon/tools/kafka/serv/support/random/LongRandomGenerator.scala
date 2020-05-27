package com.joesoon.tools.kafka.serv.support.random

import com.joesoon.tools.kafka.serv.{Generator, PropertyConfig}

import scala.util.Random

class LongRandomGenerator extends Generator[Long]{

  private[this] val random:Random = new Random

  override def genValue(propertyConfig: PropertyConfig): Long = {
      random.nextLong()
  }
}

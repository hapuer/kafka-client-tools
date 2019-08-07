package com.joesoon.tools.kafka.serv.support

import com.joesoon.tools.kafka.serv.{Generator, PropertyConfig}

import scala.util.Random


/**
  * 随机产生整形数据
  */
class IntRandomGenerator extends Generator[Int] {

   private[this] val random: Random = new Random()

   override def genValue(propertyConfig: PropertyConfig):Int = {
       random.nextInt(100)
   }
}

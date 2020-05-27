package com.joesoon.tools.kafka.serv.support

import com.joesoon.tools.kafka.serv.{Generator, PropertyConfig}

import scala.util.Random

class StringRandomGenerator extends Generator[String]{

  private[this] val random:Random = new Random
  private[this] val asciiInf:String =  "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"

  override def genValue(propertyConfig: PropertyConfig):String = {
        val str:StringBuilder = new StringBuilder
        for(i<- 1 to 30){
            str.append(asciiInf.charAt(random.nextInt(62)))
        }
        str.toString()
  }
}


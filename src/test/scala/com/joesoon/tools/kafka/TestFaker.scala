package com.joesoon.tools.kafka

import java.util.Locale

import com.github.javafaker.Faker

object TestFaker {

  def main(args: Array[String]): Unit = {

    for(i<- 1 to 10){
      val locale = new Locale("zh","CN")
      val faker = new Faker(locale)
      println(faker.number().numberBetween(1,100))
      println(faker.internet().url())
      println(faker.esports().player())
      println(faker.name().username())
    }

  }

}

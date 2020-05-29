package com.joesoon.tools.kafka

import com.github.javafaker.Faker


object RelectTest {


  def main(args: Array[String]): Unit = {


       val faker = new Faker()
       faker.file().fileName()

      val clazz = Class.forName("com.github.javafaker.Faker")
       val clazzInstance  = clazz.newInstance()
      val method = clazz.getMethod("internet")
      val res = method.invoke(clazzInstance)

      val inerClazzMethod = res.getClass.getMethod("url")
      val  finalRes = inerClazzMethod.invoke(res)
      println(finalRes)



  }

}

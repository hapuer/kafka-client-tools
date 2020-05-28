package com.joesoon.tools.kafka



object RelectTest {


  def main(args: Array[String]): Unit = {

      val clazz = Class.forName("com.github.javafaker.Faker")
       val clazzInstance  = clazz.newInstance()
      val method = clazz.getMethod("internet")
      val res = method.invoke(clazzInstance)

      val inerClazzMethod = res.getClass.getMethod("url")
      val  finalRes = inerClazzMethod.invoke(res)
      println(finalRes)

  }

}

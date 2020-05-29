package com.joesoon.tools.kafka

import java.util.Locale

import com.github.javafaker.Faker

object TestFaker {

  def main(args: Array[String]): Unit = {


      val locale = new Locale("zh","CN")
      val faker = new Faker(locale)


      val clazz = Class.forName("com.github.javafaker.Faker")
      val methods = clazz.getDeclaredMethods
      for(method<-methods){
           //print("m:"+method.getName)
           try{
                val methodObj = method.invoke(faker)
                val ms = methodObj.getClass.getDeclaredMethods
                for(m<-ms){
                    println(method.getName+", "+m.getName)
                }
           }catch {
             case ex:Exception =>
           }

      }
  }

}

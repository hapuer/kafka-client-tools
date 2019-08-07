package com.joesoon.tools.kafka.serv

/**
  * 根据数据类型产生对应的数据
  * @tparam T
  */
trait Generator[T]{

   def genValue(propertyConfig: PropertyConfig):T

}

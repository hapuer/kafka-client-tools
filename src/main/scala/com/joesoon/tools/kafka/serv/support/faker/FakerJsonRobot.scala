package com.joesoon.tools.kafka.serv.support.faker

import com.alibaba.fastjson.JSONObject
import com.joesoon.tools.kafka.serv.{JsonGenerator, TopicDescriptor}
import com.typesafe.scalalogging.slf4j.LazyLogging

class FakerJsonRobot extends LazyLogging with JsonGenerator{


   private val clazz = Class.forName("com.github.javafaker.Faker")
   private val fakerInstance = clazz.newInstance

  /**
    * 基于Faker来模拟数据
    * @param msgTopicDescriptors  消息的描述
    * @return
    */
    override def generateJson(msgTopicDescriptors: TopicDescriptor): String = {

       val jsonRes: JSONObject = new JSONObject
        try{
          for(pd<-msgTopicDescriptors.propertyDescriptors){
            val properInstance = clazz.getMethod(pd.msgType.toLowerCase).invoke(fakerInstance)
            val fakerValue = properInstance.getClass.getMethod(pd.msgProperty).invoke(properInstance)
            jsonRes.fluentPut(pd.propertyId,fakerValue)
          }
        }catch{
          case ex:Exception=>logger.error("generate msg error",ex)
        }
        jsonRes.toJSONString
    }
}

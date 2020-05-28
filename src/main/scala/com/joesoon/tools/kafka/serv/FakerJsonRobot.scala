package com.joesoon.tools.kafka.serv


import com.alibaba.fastjson.JSONObject

class FakerJsonRobot extends JsonGenerator {


   private val clazz = Class.forName("com.github.javafaker.Faker")
   private val fakerInstance = clazz.newInstance

  /**
    * 基于Faker来模拟数据
    * @param msgTopicDescriptors  消息的描述
    * @return
    */
    override def generateJson(msgTopicDescriptors: TopicDescriptor): String = {

          val jsonRes: JSONObject = new JSONObject
          for(pd<-msgTopicDescriptors.propertyDescriptors){
              val properInstance = clazz.getMethod(pd.msgType.toLowerCase).invoke(fakerInstance)
              val fakerValue = properInstance.getClass.getMethod(pd.msgProperty.toLowerCase()).invoke(properInstance)
              jsonRes.fluentPut(pd.propertyId,fakerValue)
          }
          jsonRes.toJSONString
    }
}

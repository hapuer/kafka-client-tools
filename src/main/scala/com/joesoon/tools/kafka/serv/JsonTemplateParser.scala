package com.joesoon.tools.kafka.serv

import com.alibaba.fastjson.JSONObject

import scala.collection.JavaConversions._
import scala.collection.mutable.ArrayBuffer

class JsonTemplateParser {

    /**
      * 解析消息格式
      * @param message
      * @return
      */
    def doParseMsg(message: JSONObject):ArrayBuffer[PropertyDescriptor] = {

       val propertyDescriptors = new ArrayBuffer[PropertyDescriptor]
       for(key<-message.keySet){
           val propertyJsonObject =  message.getJSONObject(key)
           if(propertyJsonObject.containsKey("mock")){
               val mockObj =  propertyJsonObject.getJSONObject("mock")
               propertyDescriptors.append(new PropertyDescriptor(key,mockObj.getString("type"),mockObj.getString("column"),new ArrayBuffer[Any]()))
           }
        }
        propertyDescriptors
    }

    /**
      * 解析JSON文件生成对应的JSON格式描述符
      *
      * @param jsonObj
      * @return
      */
    def parseTopicJsonTemplate(jsonObj:JSONObject):TopicDescriptor = {

        val topicName = jsonObj.getString("topic")
        val generator = jsonObj.getJSONObject("generator")
        val generatorInf = new GeneratorInf(generator.getString("faker"),generator.getString("locale"))
        val message = jsonObj.getJSONObject("message")
        val propertyDescriptor = doParseMsg(message)
        new TopicDescriptor(topicName,generatorInf,propertyDescriptor)
    }

}

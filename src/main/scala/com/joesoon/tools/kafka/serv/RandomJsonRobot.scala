package com.joesoon.tools.kafka.serv

import com.typesafe.scalalogging.slf4j.LazyLogging



/**
  * @author hapuer
  */
class RandomJsonRobot extends LazyLogging with JsonGenerator {


    /*def genJson(objectPropertiesDescriptor:Seq[PropertyConfig]):String = {
       val destJsonObject = new JSONObject
        if(objectPropertiesDescriptor!=null && objectPropertiesDescriptor.size>0){

            for(p<-objectPropertiesDescriptor){
                val propertyType = p.propertyType
                propertyType match {
                  case "int" => {
                    destJsonObject.fluentPut(p.property,new IntRandomGenerator().genValue(p))
                  }
                  case "long" =>{
                    destJsonObject.fluentPut(p.property,new LongRandomGenerator().genValue(p))
                  }
                  case "string" =>{
                    destJsonObject.fluentPut(p.property,new StringRandomGenerator().genValue(p))
                  }
                  case "date" =>{
                    destJsonObject.fluentPut(p.property,new DateRandomGenerator().genValue(p))
                  }
                  case "array" =>{
                    destJsonObject.fluentPut(p.property,new ArrayRandomGenerator().genValue(p))
                  }
                  case "nested" =>{
                    destJsonObject.fluentPut(p.property,new NestedRandomGenerator().genValue(p))
                  }
                  case _ => logger.warn("invalid message type")
                }
            }
        }
        destJsonObject.toJSONString
    }*/

  /**
    * 根据消息的描述，产生构造出相应的消息
    *
    * @param msgTopicDescriptors 消息的描述
    * @return
    */
  override def generateJson(msgTopicDescriptors: TopicDescriptor): String = ""
}

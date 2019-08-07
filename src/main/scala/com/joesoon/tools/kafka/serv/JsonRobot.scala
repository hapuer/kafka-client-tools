package com.joesoon.tools.kafka.serv

import com.alibaba.fastjson.JSONObject
import com.joesoon.tools.kafka.serv.support.{ArrayRandomGenerator, DateRandomGenerator, IntRandomGenerator, LongRandomGenerator, NestedRandomGenerator, StringRandomGenerator}
import com.typesafe.scalalogging.slf4j.LazyLogging



/**
  * @author hapuer
  */
class JsonRobot() extends LazyLogging{


    def genJson(objectPropertiesDescriptor:Seq[PropertyConfig]):String = {
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
                  case _ => println(propertyType)
                }
            }
        }
        destJsonObject.toJSONString
    }
}

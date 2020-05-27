package com.joesoon.tools.kafka.serv

import java.io.File
import java.nio.charset.Charset

import com.alibaba.fastjson.{JSON, JSONObject}
import com.joesoon.tools.kafka.config.KafkaConfiguration
import com.joesoon.tools.kafka.kafka.KafkaClientProducer
import com.typesafe.scalalogging.slf4j.LazyLogging
import org.apache.commons.io.FileUtils

import scala.collection.mutable.ArrayBuffer


/**
  * 读取消息构造模板，构造消息
  */
class KafkaClientServ(config: KafkaConfiguration) extends LazyLogging{

    private[this] val robot:JsonRobot = new JsonRobot()
    private[this] val kafkaClientProducer = new KafkaClientProducer(config.loadConfig())

    def doProduceMsgsAndSend(propertiesConfigs: ArrayBuffer[PropertyConfig],count:Int): Unit = {
      //  kafkaClientProducer.produce();
      val jsonString = this.robot.genJson(propertiesConfigs)
      println(jsonString)
    }

  /**
      * 根据详细模板产生消息，并根据配置的消息条数，发送消息
      */
    def produceMsgs(templateFile:String,count:Int):Unit = {
      import scala.collection.JavaConverters._
       val templateFilePath = this.config.confPath+File.separator+templateFile
       val jsonObj:JSONObject = JSON.parseObject(FileUtils.readFileToString(new File(templateFilePath),Charset.forName("utf-8")))
       val propertyObj = jsonObj.getJSONObject("properties")

       val propertiesConfigs = new ArrayBuffer[PropertyConfig]()

       for(p<-propertyObj.entrySet().asScala){
          val propertiesConfigObject = p.getValue.asInstanceOf[JSONObject]
          val typeOfProperty = propertiesConfigObject.getString("type")
          typeOfProperty match {
            case "array"|"nested" =>{
                val childrenObject = propertiesConfigObject.getJSONObject("children")
                val seq = new ArrayBuffer[PropertyConfig]()
                for(p<-childrenObject.entrySet().asScala){
                  val childObj = p.getValue.asInstanceOf[JSONObject]
                  seq.append(applyGeneratorByType(p.getKey,childObj))
                }
                propertiesConfigs.append(PropertyConfig(p.getKey,typeOfProperty,propertiesConfigObject.getBoolean("required"),
                    propertiesConfigObject.getString("generator"),
                    propertiesConfigObject.getString("formatter"),seq))
            }
            case _ => propertiesConfigs.append(applyGeneratorByType(p.getKey,propertiesConfigObject))
          }
       }
       doProduceMsgsAndSend(propertiesConfigs,count)
    }


    private def applyGeneratorByType(property:String,propertyJsonObject:JSONObject): PropertyConfig ={
            PropertyConfig(property,
              propertyJsonObject.getString("type"),
              propertyJsonObject.getBoolean("required"),
              propertyJsonObject.getString("generator"),
              propertyJsonObject.getString("formatter"),null)
    }
}

case class PropertyConfig(property:String, propertyType:String, required:Boolean, generator:String, formatter:String, children:Seq[PropertyConfig])
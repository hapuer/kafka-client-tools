package com.joesoon.tools.kafka.serv

import java.io.File
import java.nio.charset.Charset
import java.util.LinkedHashMap

import com.alibaba.fastjson.{JSON, JSONObject, TypeReference}
import com.joesoon.tools.kafka.config.KafkaConfiguration
import com.joesoon.tools.kafka.kafka.KafkaClientProducer
import com.typesafe.scalalogging.slf4j.LazyLogging
import org.apache.commons.io.FileUtils

import scala.collection.mutable.ArrayBuffer


/**
  * 读取消息构造模板，构造消息
  */
class KafkaClientServ(config: KafkaConfiguration) extends LazyLogging{

    private[this] val kafkaClientProducer = new KafkaClientProducer(config.loadConfig())
    private[this] val jsonTemplateParser:JsonTemplateParser = new JsonTemplateParser


  /**
      * 根据详细模板产生消息，并根据配置的消息条数，发送消息
      */
    def produceMsgs(templateFile:String,count:Int):Unit = {

       val jsonString = FileUtils.readFileToString(new File(this.config.confPath+File.separator+templateFile), Charset.forName("utf-8"))
       val jsonObj:JSONObject = JSON.parseObject(jsonString)
       //解析JSON消息模板
       val descriptor = jsonTemplateParser.parseTopicJsonTemplate(jsonObj)
       //生成消息生成器
       val generator = GeneratorFactory.generator(descriptor)

       val msgs = new ArrayBuffer[String](count)
       for(_ <- 1 to count){
          //根据模板生成对应的JSON格式的消息
          val jsonMsg = generator.generateJson(descriptor)
          msgs.append(jsonMsg)
       }

      kafkaClientProducer.produce(descriptor.getTopicName,msgs)
    }

}





package com.joesoon.tools.kafka.serv

import java.io.File

import com.alibaba.fastjson.{JSON, JSONArray, JSONObject}
import com.joesoon.tools.kafka.config.KafkaConfiguration
import com.joesoon.tools.kafka.kafka.KafkaClientAdmin
import com.typesafe.scalalogging.slf4j.LazyLogging
import org.apache.commons.io.{Charsets, FileUtils}


class KafkaTopicAdminServ extends LazyLogging{

  /**
    * 根据topic文件创建schema
    * @param schemaFile
    */
    def createTopics(schemaFile: File): Unit = {
        logger.debug("schemaFile: {}",schemaFile.getName)
        val jsonStr =  FileUtils.readFileToString(schemaFile,Charsets.toCharset("utf8"))
        val jsonObj = JSON.parseObject(jsonStr)
        val jsonArray:JSONArray = jsonObj.getJSONArray("topics")
        val iterator = jsonArray.iterator()
        while(iterator.hasNext){
              val jsonObj = iterator.next()
              val kafkaSchema = parseEntity(jsonObj.asInstanceOf[JSONObject])
              createTopic(kafkaSchema)
        }

    }

  /**
    * 创建单个topic的schema
    * @param kafkaSchema
    */
    def createTopic(kafkaSchema: TopicSchema):Unit = {
        val kafkaConfig = new KafkaConfiguration().loadConfig()
        val kafkaClientAdmin = new KafkaClientAdmin(kafkaConfig)
        logger.debug("create topic schema for: [{}]",kafkaSchema.topicName)
        kafkaClientAdmin.createTopic(kafkaSchema)
    }


  /**
    * JSON convert object
    * @param jsonObj
    * @return
    */
    private def parseEntity(jsonObj: JSONObject):TopicSchema = {
         TopicSchema(jsonObj.getString("compression.type"),
           jsonObj.getLong("max.message.bytes"),
           jsonObj.getString("topic"),
           jsonObj.getInteger("partition"),
           jsonObj.getInteger("replication-factor"),
           jsonObj.getLong("retention.ms"))
    }

}


case class TopicSchema(compressionType:String, maxMsgBytes:Long, topicName:String,partition:Int, repliaFactor:Int, retention:Long)


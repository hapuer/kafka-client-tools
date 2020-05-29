package com.joesoon.tools.kafka.kafka

import java.util.Properties

import com.joesoon.tools.kafka.config.KafkaConfig
import com.joesoon.tools.kafka.serv.TopicSchema
import com.typesafe.scalalogging.slf4j.LazyLogging
import kafka.admin.{AdminUtils, RackAwareMode}
import kafka.utils.ZkUtils
import org.apache.kafka.common.security.JaasUtils

class KafkaClientAdmin(kafkaConfigIn: KafkaConfig) extends LazyLogging {

  var kafkaConfig:KafkaConfig = kafkaConfigIn
  val sessionTimeout:Int = 3000
  val connectTimeout:Int = 3000
  val isZkSecurityEnabled:Boolean = false


  /**
    * 自动创建Topic
    */
   def createTopic(topicSchema: TopicSchema):Unit = {
      logger.info("create schema of topic: {}",topicSchema.topicName)
      val zkUtils = ZkUtils.apply(this.kafkaConfig.zkInf,sessionTimeout,connectTimeout,JaasUtils.isZkSecurityEnabled)
      AdminUtils.createTopic(zkUtils,topicSchema.topicName,topicSchema.partition,topicSchema.repliaFactor,new Properties,RackAwareMode.Disabled)
      zkUtils.close
   }

  /**
    * 先删除Topic，然后再创建
    */
  def createTopicWithOverride(topicSchema: TopicSchema):Unit = {

        val zkUtils = ZkUtils.apply(this.kafkaConfig.kafkaBroker,sessionTimeout,connectTimeout,JaasUtils.isZkSecurityEnabled)
        //先删除
        AdminUtils.deleteTopic(zkUtils, topicSchema.topicName)
        zkUtils.close()
        createTopic(topicSchema)
   }

  /**
    * 先删除topic
    * @param topicSchema
    */
  def deleteTopic(topicSchema: TopicSchema):Unit = {
        val zkUtils = ZkUtils.apply(this.kafkaConfig.kafkaBroker,sessionTimeout,connectTimeout,JaasUtils.isZkSecurityEnabled)
        //先删除
        AdminUtils.deleteTopic(zkUtils, topicSchema.topicName)
        zkUtils.close()
  }

}



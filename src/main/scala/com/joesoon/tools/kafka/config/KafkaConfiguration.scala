package com.joesoon.tools.kafka.config

import java.io.File

import com.typesafe.scalalogging.slf4j.LazyLogging
import org.apache.commons.configuration2.{Configuration, PropertiesConfiguration}
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder
import org.apache.commons.configuration2.builder.fluent.Configurations


/**
  * 通过KafkaConfiguration构造出KafkaConfig
  * @author hapuer
  */
class KafkaConfiguration extends LazyLogging {
  var config: Configuration  = _
  var confPath: String = System.getProperty("user.dir") + File.separator+ "conf"
  var confFile: String = confPath+ File.separator+"conf.properties"
  /**
    * 返回配置对象
    * @return
    */
  def loadConfig(): KafkaConfig = {
    val configs = new Configurations()
    FileBasedConfigurationBuilder.setDefaultEncoding(classOf[PropertiesConfiguration], "UTF-8")
    val config:PropertiesConfiguration = configs.properties(new File(this.confFile))
    val kafkaConfig = KafkaConfig(
      config.getString("kafka.broker")
    )
    logger.info("kafkaconfig: {}",kafkaConfig.toString)
    kafkaConfig
  }
}

case class KafkaConfig(kafkaBroker:String)


object KafkaConfiguration {

  def apply():KafkaConfiguration = {
        new KafkaConfiguration()
  }
}
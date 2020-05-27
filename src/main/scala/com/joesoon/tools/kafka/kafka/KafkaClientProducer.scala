package com.joesoon.tools.kafka.kafka

import java.util.Properties
import java.util.concurrent.TimeUnit

import com.joesoon.tools.kafka.config.KafkaConfig
import com.typesafe.scalalogging.slf4j.LazyLogging
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer


/***
  * @author hapuer
  */
class KafkaClientProducer(kafkaConfig:KafkaConfig) extends LazyLogging{

  case class KafkaProducerConfigs(brokerList: String = kafkaConfig.kafkaBroker) {
    val properties = new Properties()
    properties.put("bootstrap.servers", brokerList)
    properties.put("key.serializer", classOf[StringSerializer])
    properties.put("value.serializer", classOf[StringSerializer])
  }

  val producer = new KafkaProducer[String, String](KafkaProducerConfigs().properties)

  def produce(topic: String, messages: Iterable[String]): Unit = {
      messages.foreach { m =>
        producer.send(new ProducerRecord[String, String](topic, m))
      }
      producer.close(100L, TimeUnit.MILLISECONDS)
  }

}
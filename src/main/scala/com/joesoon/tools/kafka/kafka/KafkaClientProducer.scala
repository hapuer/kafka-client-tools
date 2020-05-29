package com.joesoon.tools.kafka.kafka

import java.util.Properties

import com.joesoon.tools.kafka.config.KafkaConfig
import com.typesafe.scalalogging.slf4j.LazyLogging
import org.apache.kafka.clients.producer.{ KafkaProducer,ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

import scala.collection.mutable.ArrayBuffer


/***
  * @author hapuer
  */
class KafkaClientProducer(kafkaConfig:KafkaConfig) extends LazyLogging{

    case class KafkaProducerConfigs(brokerList: String = kafkaConfig.kafkaBroker) {
      val properties = new Properties
      properties.put("bootstrap.servers", brokerList)
      properties.put("key.serializer", classOf[StringSerializer])
      properties.put("value.serializer", classOf[StringSerializer])
    }

    val producer = new KafkaProducer[String, String](KafkaProducerConfigs().properties)

    def produce(topic: String, messages: ArrayBuffer[String]): Unit = {
        for(msg<-messages){
           producer.send(new ProducerRecord[String,String](topic,msg))
        }
        producer.close
    }

}
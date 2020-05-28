package com.joesoon.tools.kafka

import java.util.Properties
import java.util.concurrent.TimeUnit

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

object TestKafkaClient {

  def main(args: Array[String]): Unit = {


    val properties = new Properties
    properties.put("bootstrap.servers", "192.168.10.227:9092")
    properties.put("key.serializer", classOf[StringSerializer])
    properties.put("value.serializer", classOf[StringSerializer])

    val producer = new KafkaProducer[String, String](properties)

    producer.send(new ProducerRecord[String,String]("joesoon-social-twitter-status-v1","buhuibadsfdsfdsf"))

    producer.flush()

    producer.close(1000,TimeUnit.MICROSECONDS)

  }

}

package com.joesoon.tools.kafka.serv.support.random

import com.joesoon.tools.kafka.serv.{JsonGenerator, TopicDescriptor}

class RandomJsonRobot extends JsonGenerator{
  /**
    * 根据消息的描述，产生构造出相应的消息
    *
    * @param msgTopicDescriptors 消息的描述
    * @return
    */
  override def generateJson(msgTopicDescriptors: TopicDescriptor): String = {
     ""
  }
}

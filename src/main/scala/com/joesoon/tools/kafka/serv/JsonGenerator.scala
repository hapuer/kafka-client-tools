package com.joesoon.tools.kafka.serv

trait JsonGenerator {

  /**
    * 根据消息的描述，产生构造出相应的消息
    * @param msgTopicDescriptors  消息的描述
    * @return
    */
  def generateJson(msgTopicDescriptors:TopicDescriptor):String


}

package com.joesoon.tools.kafka.serv

import com.joesoon.tools.kafka.serv.support.faker.FakerJsonRobot

object GeneratorFactory {
    def generator(descriptor: TopicDescriptor):JsonGenerator = {
         descriptor.generatorInf.generatorType match {
            case "random" =>  new RandomJsonRobot
            case "faker" => new FakerJsonRobot
            case _ => new FakerJsonRobot
      }
    }

}

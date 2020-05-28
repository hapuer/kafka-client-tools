package com.joesoon.tools.kafka.serv

object GeneratorFactory {
    def generator(descriptor: TopicDescriptor):JsonGenerator = {
         descriptor.generatorInf.generatorType match {
            case "random" =>  new RandomJsonRobot
            case "faker" => new FakerJsonRobot
            case _ => new FakerJsonRobot
      }
    }

}

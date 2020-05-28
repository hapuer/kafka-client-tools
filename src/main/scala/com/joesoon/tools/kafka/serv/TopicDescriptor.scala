package com.joesoon.tools.kafka.serv

import scala.beans.BeanProperty
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

class TopicDescriptor{

      @BeanProperty
       var topicName:String  = _
       @BeanProperty
      var generatorInf:GeneratorInf = _
      @BeanProperty
      var propertyDescriptors:IndexedSeq[PropertyDescriptor] = _

      def this(topicName:String, generatorInf: GeneratorInf, propertiesOfTopic:IndexedSeq[PropertyDescriptor]){
          this()
          this.topicName = topicName
          this.generatorInf = generatorInf
          this.propertyDescriptors = propertiesOfTopic
      }

}

class PropertyDescriptor{

    var propertyId :String = _
    var msgType:String = _
    var msgProperty:String = _
    var msgParams:ArrayBuffer[Any] = _

    def this(propertyId:String,msgType:String,msgProperty:String,msgParams:ArrayBuffer[Any]){
        this()
        this.propertyId = propertyId
        this.msgProperty = msgProperty
        this.msgParams = msgParams
        this.msgType = msgType
    }
}

case class GeneratorInf(var generatorType:String,var localInf:String)




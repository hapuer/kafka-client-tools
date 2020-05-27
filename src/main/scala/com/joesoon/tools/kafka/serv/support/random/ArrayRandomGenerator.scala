package com.joesoon.tools.kafka.serv.support

import com.alibaba.fastjson.{JSONArray, JSONObject}
import com.joesoon.tools.kafka.serv.{Generator, PropertyConfig}


class ArrayRandomGenerator extends Generator[JSONArray]{


  override def genValue(propertyConfig: PropertyConfig): JSONArray = {
    val jsonaArray = new JSONArray()
    val jsonObject = new JSONObject()
    for (p <- propertyConfig.children) {
      val propertyType = p.propertyType
      propertyType match {
        case "int" => {
          jsonObject.put(p.property,new IntRandomGenerator().genValue(p))
        }
        case "long" => {
          jsonObject.put(p.property,new LongRandomGenerator().genValue(p))
        }
        case "string" => {
          jsonObject.put(p.property,new StringRandomGenerator().genValue(p))
        }
        case "date" => {
          jsonObject.put(p.property,new StringRandomGenerator().genValue(p))
        }
        case _ => println(propertyType)
      }
    }
    jsonaArray.add(jsonObject)
    jsonaArray
  }
}

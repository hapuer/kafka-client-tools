package com.joesoon.tools.kafka

class TestSplit {

}


object TestSplit{
  def main(args: Array[String]): Unit = {

      val testString = ""
      val splits = testString.split("\\|")
      println(splits.length)
  }
}
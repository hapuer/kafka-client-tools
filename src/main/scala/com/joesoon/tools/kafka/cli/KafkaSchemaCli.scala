package com.joesoon.tools.kafka.cli

import java.io.{File, IOException}

import com.joesoon.tools.kafka.serv.KafkaTopicAdminServ
import com.typesafe.scalalogging.slf4j.LazyLogging
import org.apache.commons.cli.{CommandLine, Options}



class KafkaSchemaCli extends CliRunner with LazyLogging {

  private[this] val CONF_DIR:String = "conf"

  /**
    * 初始化一个命令
    * @return
    */
  override def initOptions: Options = {
     val options = new Options
     options.addOption("f",true,"必须要指定schema文件")
     options.addOption("d",false,"文件所在目录,默认在conf目录下")
     options
  }

  /**
    * 验证命令是否有效
    * @return
    */
  override def validateOptions(cmdLine: CommandLine): Boolean = {
      cmdLine.hasOption("f")||cmdLine.hasOption("d")
  }

  /**
    * 启动命令
    * @param cmdLine
    */
  override def start(cmdLine: CommandLine): Unit = {
      //获取schema文件目录,如果不存在则为默认目录
      var topicSchemaFilePath = cmdLine.getOptionValue("d")
      if(Option(topicSchemaFilePath).getOrElse("").isEmpty){
          topicSchemaFilePath = System.getProperty("user.dir")+File.separator+CONF_DIR
      }else{
          val filePath = new File(topicSchemaFilePath)
        try{
          if((!filePath.exists()) || (!filePath.isDirectory)){
            logger.warn("can't find directory. [{}]",topicSchemaFilePath)
            return
          }
        }catch {
          case ex:IOException => logger.error("can't find directory.{}",ex.getMessage)
        }
      }

      //获得schema文件
      val topicSchemaFileName = cmdLine.getOptionValue("f")
      val topicSchemaFile = new File(topicSchemaFilePath,topicSchemaFileName)
      if(!topicSchemaFile.exists()){
         logger.warn("can't find topic schema file. pls check.")
          return
      }
      val topicAdminServ = new KafkaTopicAdminServ()
      topicAdminServ.createTopics(topicSchemaFile)
  }
}


object KafkaSchemaCli{
  def main(args: Array[String]): Unit = {
    SkynetCli.initRunner(args,this.getClass.getSimpleName,new KafkaSchemaCli())
  }
}
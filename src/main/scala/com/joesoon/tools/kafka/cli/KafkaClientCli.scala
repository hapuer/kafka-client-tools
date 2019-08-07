package com.joesoon.tools.kafka.cli

import com.joesoon.tools.kafka.config.KafkaConfiguration
import com.joesoon.tools.kafka.serv.KafkaClientServ
import org.apache.commons.cli.{CommandLine, Options}

class KafkaClientCli extends CliRunner {

  /**
    * 初始化一个命令
    *
    * @return
    */
  override def initOptions: Options = {
        val options = new Options
        options.addOption("t",true,"指定发送消息的模板")
        options.addOption("n","指定发送消息的条数")
        options.addOption("d","指定模板文件所在目录,默认文件目录是conf目录下")
  }

  /**
    * 验证命令是否有效
    *
    * @return
    */
  override def validateOptions(cmdLine: CommandLine): Boolean = {
      cmdLine.hasOption("t")
  }

  /**
    * 启动命令
    *
    * @param cmdLine
    */
  override def start(cmdLine: CommandLine): Unit = {
       //读取命令参数，准备消息模板
       val topic = cmdLine.getOptionValue("t")
       val eventCount = cmdLine.getOptionValue("n","0")
       //给serv处理
       val kafkaClientServ = new KafkaClientServ(new KafkaConfiguration().loadConfig())

  }
}


object KafkaClientCli{
  def main(args: Array[String]): Unit = {
    def className = this.getClass.getSimpleName
    SkynetCli.initRunner(args,className,new KafkaClientCli())
  }
}

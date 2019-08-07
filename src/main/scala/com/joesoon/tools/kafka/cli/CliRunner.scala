package com.joesoon.tools.kafka.cli

import org.apache.commons.cli.{CommandLine, Options}

trait CliRunner {

  /**
    * 初始化一个命令
    * @return
    */
  def initOptions:Options

  /**
    * 验证命令是否有效
     * @return
    */
  def validateOptions(cmdLine: CommandLine):Boolean

  /**
    * 启动命令
    * @param cmdLine
    */
  def start(cmdLine: CommandLine)
}

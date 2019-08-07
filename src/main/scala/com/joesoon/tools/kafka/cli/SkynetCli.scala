package com.joesoon.tools.kafka.cli

import org.apache.commons.cli.{DefaultParser, HelpFormatter, ParseException}

object SkynetCli {

      val CLI_PARAM_HELP:String = "help"; //help
      val formatter:HelpFormatter = new HelpFormatter

      def initRunner(args: Array[String],cmdName: String,cliRunner: CliRunner):Unit = {
          val parser = new DefaultParser()
          val options = cliRunner.initOptions
           try{
             val cmdLine = parser.parse(options,args)
             if(!cliRunner.validateOptions(cmdLine) || cmdLine.hasOption(CLI_PARAM_HELP)){
                  formatter.printHelp(cmdName, options);
                  return
             }
             cliRunner.start(cmdLine)
           }catch{
             case ex:ParseException => println(ex)
           }
      }

}

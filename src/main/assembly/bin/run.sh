#!/usr/bin/env bash

dir=$(cd "$(dirname "$0")"; pwd)
cd $dir
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=${DEPLOY_DIR}/conf
LIB_DIR=${DEPLOY_DIR}/lib

print_usage()
{
    echo "Usage: sh run.sh COMMAND"
    echo "where COMMAND is one of the follows: "

    echo "  KafkaClientCli"
    echo "  KafkaSchemaCli"
    exit 1
}

if [ $# = 0 ] || [ $# = "help" ]; then
    print_usage
fi

COMMAND=$1
shift

if [ "$JAVA_HOME" = "" ]; then
    JAVA_HOME=/usr/java
    echo $JAVA_HOME
fi

JAVA=${JAVA_HOME}/bin/java
HEAP_OPTS="-Xmx1024m"

JAR_NAME=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

CLASSPATH=${CLASSPATH}:${JAVA_HOME}/lib/tools.jar
CLASSPATH=${CLASSPATH}:${CONF_DIR}
CLASSPATH=${CLASSPATH}:${JAR_NAME}

for f in JAR_NAME; do
  CLASSPATH=${CLASSPATH}:${f};
done

params=$@
if [ "$COMMAND" = "KafkaClientCli" ]; then
    CLASS=com.joesoon.tools.kafka.cli.KafkaClientCli
elif [ "$COMMAND" = "KafkaSchemaCli" ]; then
    CLASS=com.joesoon.tools.kafka.cli.KafkaSchemaCli
else
    CLASS=$COMMAND
fi

"$JAVA" -Djava.awt.headless=true ${HEAP_OPTS} -classpath "$CLASSPATH" ${CLASS} ${params}
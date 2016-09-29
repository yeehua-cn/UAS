#!/usr/bin/env bash
###################################
#
# 本shell脚本为 UAS系统 Hazelcast服务linux启动脚本
#
###################################
cd `dirname $0`
cd ..

#  检测是否具有logs 日志文件夹
if [ ! -d ./logs ] ;  then
        mkdir ./logs/
fi

# 判断JAVA_HOME是否设置
if [ -z "$JAVA_HOME" ] ; then
  echo "Error: JAVA_HOME environment variable is not set."
fi

#JDK所在路径-----------------------------------------------------------
JDK_HOME="${JAVA_HOME}"

#需要启动的Java主程序（main方法类或唯一标识）----------------------------------------
APP_MAIN_CLASS=org.uas.hazelcast.server.Server

#CLASSPATH 参数，包括指定lib目录下所有的jar
CLASSPATH=$CLASSPATH:./libs/*  # 依赖Jar
CLASSPATH=$CLASSPATH:./config  # 配置文件

#java虚拟机启动参数
JAVA_OPTS=""

#指定 java 启动命令
if [ -z "$JAVA_CMD" ] ; then
  if [ -n "$JDK_HOME"  ] ; then
    if [ -x "$JDK_HOME/jre/sh/java" ] ; then
      # IBM's JDK on AIX uses strange locations for the executables
      JAVA_CMD="$JDK_HOME/jre/sh/java"
    else
      JAVA_CMD="$JDK_HOME/bin/java"
    fi
  else
    JAVA_CMD="`which java`"
  fi
fi
# 判断java命令是否具有执行权限
if [ ! -x "$JAVA_CMD" ] ; then
  echo "Error: JAVA_HOME is not defined correctly." >&2
  echo "  We cannot execute $JAVA_CMD" >&2
  exit 1
fi

###################################
#(函数)判断程序是否已启动
#
#说明：
#使用JDK自带的JPS命令及grep命令组合，准确查找pid
#jps 加 l 参数，表示显示java的完整包路径
#使用awk，分割出pid ($1部分)，及Java程序名称($2部分)
###################################
#初始化psid变量（全局）
psid=0

checkpid() {
   javaps=`$JAVA_HOME/bin/jps -l | grep $APP_MAIN_CLASS`

   if [ -n "$javaps" ]; then
      psid=`echo $javaps | awk '{print $1}'`
   else
      psid=0
   fi
}

###################################
#(函数)启动程序
#
#说明：
#1. 首先调用checkpid函数，刷新$psid全局变量
#2. 如果程序已经启动（$psid不等于0），则提示程序已启动
#3. 如果程序没有被启动，则执行启动命令行
#4. 启动命令执行后，再次调用checkpid函数
#5. 如果步骤4的结果能够确认程序的pid,则打印[OK]，否则打印[Failed]
#注意：echo -n 表示打印字符后，不换行
#注意: "nohup 某命令 >/dev/null 2>&1 &" 的用法
###################################
start() {
   checkpid

   if [ $psid -ne 0 ]; then
      echo "================================"
      echo "warn: $APP_MAIN_CLASS already started! (pid=$psid)"
      echo "================================"
   else
      echo -n "Starting $APP_MAIN_CLASS ..."
      echo
      echo -n "CLASSPATH=$CLASSPATH"
      echo
      echo -n "JAVA_OPTS=$JAVA_OPTS"
      echo
      exec "$JAVA_CMD"  -cp $CLASSPATH  $JAVA_OPTS  $APP_MAIN_CLASS &

      checkpid
      if [ $psid -ne 0 ]; then
         echo "Starting $APP_MAIN_CLASS (pid=$psid) [OK]"
      else
         echo "Starting $APP_MAIN_CLASS [Failed]"
      fi
   fi
}

###################################
#(函数)停止程序
#
#说明：
#1. 首先调用checkpid函数，刷新$psid全局变量
#2. 如果程序已经启动（$psid不等于0），则开始执行停止，否则，提示程序未运行
#3. 使用kill -9 pid命令进行强制杀死进程
#4. 执行kill命令行紧接其后，马上查看上一句命令的返回值: $?
#5. 如果步骤4的结果$?等于0,则打印[OK]，否则打印[Failed]
#6. 为了防止java程序被启动多次，这里增加反复检查进程，反复杀死的处理（递归调用stop）。
#注意：echo -n 表示打印字符后，不换行
#注意: 在shell编程中，"$?" 表示上一句命令或者一个函数的返回值
###################################
stop() {
   checkpid

   if [ $psid -ne 0 ]; then
      echo -n "Stopping $APP_MAIN_CLASS ...(pid=$psid) "
      kill -15 $psid
      if [ $? -eq 0 ]; then
         echo "[OK]"
      else
         echo "[Failed]"
      fi

      checkpid
      if [ $psid -ne 0 ]; then
         stop
      fi
   else
      echo "================================"
      echo "warn: $APP_MAIN_CLASS is not running"
      echo "================================"
   fi
}

###################################
#(函数)检查程序运行状态
#
#说明：
#1. 首先调用checkpid函数，刷新$psid全局变量
#2. 如果程序已经启动（$psid不等于0），则提示正在运行并表示出pid
#3. 否则，提示程序未运行
###################################
status() {
   checkpid

   if [ $psid -ne 0 ];  then
      echo "$APP_MAIN_CLASS is running! (pid=$psid)"
   else
      echo "$APP_MAIN_CLASS is not running"
   fi
}

###################################
#(函数)打印系统环境参数
###################################
info() {
   echo "System Information:"
   echo "****************************"
   echo `head -n 1 /etc/issue`
   echo `uname -a`
   echo
   echo "JAVA_HOME=$JAVA_HOME"
   echo `$JAVA_HOME/bin/java -version`
   echo
   echo "APP_MAIN_CLASS=$APP_MAIN_CLASS"
   echo "****************************"
}

###################################
#读取脚本的第一个参数($1)，进行判断
#参数取值范围：{start|stop|restart|status|info}
#如参数不在指定范围之内，则打印帮助信息
###################################
case "$1" in
   'start')
      start
      ;;
   'stop')
     stop
     ;;
   'restart')
     stop
     start
     ;;
   'status')
     status
     ;;
   'info')
     info
     ;;
  *)
     echo "Usage: $0 {start|stop|restart|status|info}"
     exit 1
esac
exit 0
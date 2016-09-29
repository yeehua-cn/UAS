#Hazelcast服务

Hazelcast服务是一个独立的应用服务，独立部署、运行Hazelcast服务。目标程序包中集合了启动脚本、jar包及依赖包、配置文件等。


##目录说明

    ./logs  日志目录（若有）

    ./config    配置文件目录

    ./bin   启动脚本目录

    ./libs   应用程序jar及依赖jar存放目录
    
##配置说明

###配置文件

    ./config/hazelcast.xml
        
##运行需求

JDK：1.8或以上版本。

设置JAVA_HOME变量，指定JDK的目录。

启动脚本会根据JAVA_HOME变量，选择其中的Java命令，执行相应的Java程序。

##命令脚本

在执行启动脚本之前，请先给启动脚本添加执行权限，添加执行权限命令如下：

    chmod +x ./bin/hazelcast-service.sh

###Linux

查看命令用法：

    ./bin/hazelcast-service.sh

查看程序运行状态：

    ./bin/hazelcast-service.sh status
    
启动命令:

    ./bin/hazelcast-service.sh start

出现“Starting \*\*\*.\*\*\*.\*\*\*.\*\*\*Main (pid=***) [OK]”字样，则表示启动成功。其它则表示失败。

停止命令：

    ./bin/hazelcast-service.sh stop
    
出现“[OK]”字样，则表示停止成功。其它则表示失败。    

重启命令：

    ./bin/hazelcast-service.sh restart

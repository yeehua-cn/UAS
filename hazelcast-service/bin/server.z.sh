#!/bin/sh
cd `dirname $0`

java -cp ../lib/hazelcast-all-3.1.3.jar:../target/classes  com.zhuyiqi.hazelcast.server.Server




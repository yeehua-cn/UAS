#!/bin/sh

java -server -Xms1G -Xmx1G -Djava.net.preferIPv4Stack=true -cp ../lib/hazelcast-3.1.3.jar com.hazelcast.examples.StartServer



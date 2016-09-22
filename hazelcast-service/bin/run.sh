#!/bin/sh

java -server -Djava.net.preferIPv4Stack=true -cp ../lib/hazelcast-3.1.3.jar com.hazelcast.examples.TestApp


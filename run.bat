@echo off

set main="service.Main"

java -classpath ";jars/*;classes/" %main% %*

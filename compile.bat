@echo off

set outputdir="classes"

set packages="@paths.txt"

mkdir %outputdir%
javac -d "%outputdir%" -classpath "jars/*" %packages% 

#!/bin/bash
outputdir="classes"
packages="src/main/com/*/*"
mkdir $outputdir
javac -d $outputdir $packages -classpath "jars/*"

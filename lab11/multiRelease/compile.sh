#!/bin/bash

for i in "$@"; do
	javac --release "$i" java"$i"/src/main/java/*.java -d compiled_java"$i"/
done

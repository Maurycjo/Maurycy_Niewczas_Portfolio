#!/bin/bash

jar --create --file app2.jar --main-class Main \
-C compiled_java11 . \
--release 15 -C compiled_java15 . \
--release 17 -C compiled_java17 . \

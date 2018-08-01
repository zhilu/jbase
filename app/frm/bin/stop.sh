#!/bin/sh
cd ..
PID=pid

kill -9 `cat $PID`  
rm -rf $PID  
#! /bin/bash

PID=$(ps -aux | awk '/[s]pid-deploy/{print $2}')
for i in $PID
do
    kill -9 "$i"
    echo "Chiuso Processo Spid Integration con PID: $i"
done

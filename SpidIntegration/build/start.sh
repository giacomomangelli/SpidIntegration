#! /bin/bash

directory="spid-deploy"

if [ ! -d "$directory" ]; then
  mkdir -p "$directory"
  echo "Directory '$directory' created."
else
  echo "Directory '$directory' already exists."
fi

java_directory="spid-deploy/jdk/"

if [ ! -d "$java_directory" ]; then
  mkdir -p "$java_directory"
  echo "Directory '$java_directory' created."
  if [ -z "$(ls -A ./jdk-21_linux-aarch64_bin.tar.gz)" ]; then
    wget https://download.oracle.com/java/21/latest/jdk-21_linux-aarch64_bin.tar.gz
    echo "JDK downloaded"
    tar xvf jdk-21_linux-aarch64_bin.tar.gz
    echo "JDK extraction ended"
    rm -r -f ./jdk-21_linux-aarch64_bin.tar.gz
    mv jdk-21*/ spid-deploy/jdk/
  else
    tar xvf jdk-21_linux-aarch64_bin.tar.gz
    echo "JDK extraction ended"
    rm -r -f ./jdk-21_linux-aarch64_bin.tar.gz
    mv jdk-21*/ spid-deploy/jdk/
  fi
else
  echo "Directory '$java_directory' already exists."
fi

mv ./spid-deploy/jdk/jdk-21*/ ./spid-deploy/jdk/jdk-21/

cp -r -f ../target/SpidIntegration-0.0.1-SNAPSHOT.jar ./spid-deploy/jdk/jdk-21/

chmod +x spid-deploy/jdk/jdk-21/bin/java

  if [ ! -z "$1" ]; then
    echo "Avvio SpidIntegration con file di configurazione $1"
    spid-deploy/jdk/jdk-21/bin/java -Dspring.config.location=$1 -jar spid-deploy/jdk/jdk-21/SpidIntegration.jar > todelete.log &
  else
    echo "Avvio SpidIntegration con file di configurazione di default"
    spid-deploy/jdk/jdk-21/bin/java -jar spid-deploy/jdk/jdk-21/SpidIntegration.jar > todelete.log &
  fi

sleep 2
rm todelete.log
echo "Spid Integration avviato correttamente!"
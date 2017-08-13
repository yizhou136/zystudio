#!/bin/bash
CURPWD=`pwd`
LOCATION=${1:-"bj"}
SRV_TYPE=${2:-"common"}

dockerd -D -H=unix:///var/run/docker.sock -H tcp://0.0.0.0:2375 --label location=${LOCATION} --label service.type=${SRV_TYPE}


#docker  run    -d --name  jenkins   -p 50000:50000  -p 8080:8080  jenkins

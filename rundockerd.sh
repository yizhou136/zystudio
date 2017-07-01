#!/bin/bash
CURPWD=`pwd`
LOCATION=${1:-"bj"}
SRV_TYPE=${2:-"common"}

dockerd -D -H=unix:///var/run/docker.sock -H tcp://0.0.0.0:2375 --label location=${LOCATION} --label service.type=${SRV_TYPE}

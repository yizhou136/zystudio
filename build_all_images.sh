#!/bin/bash

function progress() {
    local GREEN CLEAN
    GREEN='\033[0;32m'
    CLEAN='\033[0m'
    printf "\n${GREEN}$@  ${CLEAN}\n" >&2
}

set -e

# Docker image prefix
REGPREFIX=registry.aliyuncs.com/jingshanlb/

#progress "Building discovery-server(1/5) jar file ..."
cd common/discovery-server
./gradlew build
progress "Building discovery-server(1/5) docker image ..."
docker tag $(docker build -t ${REGPREFIX}discovery-server -q .) ${REGPREFIX}discovery-server:$(date -ju "+%Y%m%d-%H%M%S")
cd -
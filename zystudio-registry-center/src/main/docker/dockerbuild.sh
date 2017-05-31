#!/bin/bash

PWD=`pwd`

set -e

# Docker image prefix
REGPREFIX=registry.cn-qingdao.aliyuncs.com/zystudio/micro_services
IMAGE_NAME=${REGPREFIX}:registry_center_0.0.1
DEP_IMAGE_NAME=${REGPREFIX}:registry_center_dep_0.0.1

echo "make ${DEP_IMAGE_NAME} dependency image"

mvn dependency:copy-dependencies

docker build -t ${DEP_IMAGE_NAME} -f src/main/docker/DockerfileDep  .


mvn  clean -Dmaven.test.skip=true package

echo "pwd ${PWD}"

docker build -t ${IMAGE_NAME} -f src/main/docker/Dockerfile  .

#docker tag $(docker build -t ${IMAGE_NAME} -f src/main/docker/Dockerfile  -q .) ${IMAGE_NAME}:$(date -ju "+%Y%m%d-%H%M%S")
#cd -

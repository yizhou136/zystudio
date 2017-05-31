#!/bin/bash

set -e

# Docker image prefix
REGPREFIX=registry.cn-qingdao.aliyuncs.com/zystudio/micro_services
IMAGE_NAME=${REGPREFIX}/registry_center


:[镜像版本号]

mvn  clean -Dmaven.test.skip=true package
docker tag $(docker build -t ${IMAGE_NAME} -q .) ${IMAGE_NAME}:$(date -ju "+%Y%m%d-%H%M%S")
#cd -
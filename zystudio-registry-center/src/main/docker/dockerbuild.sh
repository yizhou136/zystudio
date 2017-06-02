#!/bin/bash

PWD=`pwd`

set -e

# Docker image prefix
REGISTRY=my.docker.registry.com:5000
REPOSITORY=/micro_services

IMG_PREF=${REGISTRY}${REPOSITORY}
IMG_VER=0.0.1
IMAGE_BASE_NAME=${IMG_PREF}/registry_center
DEP_IMAGE_NAME=${IMAGE_BASE_NAME}_dep:${IMG_VER}
IMAGE_NAME=${IMAGE_BASE_NAME}:${IMG_VER}

echo "make ${DEP_IMAGE_NAME} dependency image"

mvn dependency:copy-dependencies



docker build -t ${DEP_IMAGE_NAME} -f src/main/docker/DockerfileDep  .


mvn  clean -Dmaven.test.skip=true package

echo "make ${IMAGE_NAME} image"

docker build -t ${IMAGE_NAME} -f src/main/docker/Dockerfile --build-arg  FROM_DEP=${DEP_IMAGE_NAME}  .

#docker tag $(docker build -t ${IMAGE_NAME} -f src/main/docker/Dockerfile  -q .) ${IMAGE_NAME}:$(date -ju "+%Y%m%d-%H%M%S")
#cd -

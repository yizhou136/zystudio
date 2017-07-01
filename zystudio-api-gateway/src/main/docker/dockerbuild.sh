#!/bin/bash

PWD=`pwd`

set -e

# Docker image prefix
REGISTRY=reg.docker.zystudio.site:5000/
REPOSITORY=micro-services

IMG_PREF=${REGISTRY}${REPOSITORY}
IMG_VER=0.0.1
IMAGE_BASE_NAME=${IMG_PREF}/api-gateway
DEP_IMAGE_NAME=${IMAGE_BASE_NAME}-dep:${IMG_VER}
IMAGE_NAME=${IMAGE_BASE_NAME}:${IMG_VER}

echo "make ${DEP_IMAGE_NAME} dependency image"

mvn dependency:copy-dependencies


#docker rmi  ${DEP_IMAGE_NAME}

docker tag $(docker build -t ${DEP_IMAGE_NAME} -f src/main/docker/DockerfileDep -q  .)  ${REGISTRY}${DEP_IMAGE_NAME}


mvn  clean -Dmaven.test.skip=true package

echo "make ${IMAGE_NAME} image"

#docker rmi  ${IMAGE_NAME}
docker tag $(docker build -t ${IMAGE_NAME} -f src/main/docker/Dockerfile --build-arg  FROM_DEP=${DEP_IMAGE_NAME} -q .) ${REGISTRY}${IMAGE_NAME}

#docker build -t ${IMAGE_NAME} -f src/main/docker/Dockerfile  -q .) ${IMAGE_NAME}:$(date -ju "+%Y%m%d-%H%M%S")


docker push ${DEP_IMAGE_NAME}
docker push ${IMAGE_NAME}


docker rmi ${DEP_IMAGE_NAME}  ${IMAGE_NAME}


docker system  prune -f

docker images

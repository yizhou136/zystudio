#!/bin/bash
#Docker image prefix
REGISTRY=reg.docker.zystudio.site:5000/
REPOSITORY=micro-services
SERVICE_NAME=weibo-service
SERVICE_VER=0.0.1

IMG_PREF=${REGISTRY}${REPOSITORY}
IMAGE_BASE_NAME=${IMG_PREF}/${SERVICE_NAME}
DEP_IMAGE_NAME=${IMAGE_BASE_NAME}-dep:${SERVICE_VER}
IMAGE_NAME=${IMAGE_BASE_NAME}:${SERVICE_VER}

NETWORK=zystudio_common
LOCATION=${location:"bj"}

echo "remove ${SERVICE_NAME} service for ${IMG_PREF}"

docker  service  rm  ${SERVICE_NAME}
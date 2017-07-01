#!/bin/bash

#Docker image prefix
REGISTRY=reg.docker.zystudio.site:5000/
REPOSITORY=micro-services
SERVICE_NAME=api-gateway
SERVICE_VER=0.0.1

IMG_PREF=${REGISTRY}${REPOSITORY}
IMAGE_BASE_NAME=${IMG_PREF}/${SERVICE_NAME}
DEP_IMAGE_NAME=${IMAGE_BASE_NAME}-dep:${SERVICE_VER}
IMAGE_NAME=${IMAGE_BASE_NAME}:${SERVICE_VER}

NETWORK=zystudio_common
LOCATION=${location:"bj"}

#HOSTNAME_TEMPLATE="${ZONE}{{.Task.Slot}}.{{.Service.Name}}.zystudio.com"
echo "create ${SERVICE_NAME} service for ${IMG_PREF}"

SET_COMMS="--publish 80:80 --replicas 2 --network ${NETWORK} --label service.name=${SERVICE_NAME} --hostname="{{.Task.Name}}" --name ${NAME}"
SET_ENV="--env location=${LOCATION} --env slot=\"{{.Task.Slot}}\" "
SET_CONSTRAINT="--constraint engine.labels.location==${LOCATION} --constraint engine.labels.service.type==common"


echo "docker service create ${SET_COMMS} ${SET_ENV} ${SET_CONSTRAINT} ${IMAGE}"
docker  service  create ${SET_COMMS} ${SET_ENV} ${SET_CONSTRAINT}  ${IMAGE}
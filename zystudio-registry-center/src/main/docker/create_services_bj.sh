#!/bin/bash

#Docker image prefix
REGISTRY=reg.docker.zystudio.site:5000/
REPOSITORY=micro-services
ORG_SERVICE_NAME=registry-center
SERVICE_VER=0.0.1

IMG_PREF=${REGISTRY}${REPOSITORY}
IMAGE_BASE_NAME=${IMG_PREF}/${ORG_SERVICE_NAME}
DEP_IMAGE_NAME=${IMAGE_BASE_NAME}-dep:${SERVICE_VER}
IMAGE_NAME=${IMAGE_BASE_NAME}:${SERVICE_VER}

NETWORK=zystudio_common
LOCATION=${location:-"bj"}
#HOSTNAME_TEMPLATE="${ZONE}{{.Task.Slot}}.{{.Service.Name}}.zystudio.com"
#SET_ENV="--env zone=${ZONE} --env slot=\"{{.Task.Slot}}\"
#--env registry.peers=\"http://regcen-bjyw1:1100/eureka\"  --env registry.hostname=${NAME}"

PROFILE="${LOCATION}dx01"
SERVICE_NAME="${ORG_SERVICE_NAME}-${PROFILE}"
echo "create ${SERVICE_NAME} service for ${IMG_PREF}"
#HOST_NAME="${SERVICE_NAME}-${LOCATION}{{.Task.Slot}}"
SET_COMMS="--publish 1100:1100 --replicas 1 --network ${NETWORK} --label service.name=${SERVICE_NAME} --hostname=${SERVICE_NAME} --name ${SERVICE_NAME}"
SET_ENV="--env service=${SERVICE_NAME} --env location=${LOCATION} --env slot={{.Task.Slot}} --env profile=${PROFILE}"
SET_CONSTRAINT="--constraint engine.labels.location==${LOCATION} --constraint engine.labels.service.type==common"
echo "docker service create ${SET_COMMS} ${SET_ENV} ${SET_CONSTRAINT} ${IMAGE_NAME}"
docker network  create  -d  overlay  ${NETWORK}
docker  service  create ${SET_COMMS} ${SET_ENV} ${SET_CONSTRAINT}  ${IMAGE_NAME}


PROFILE="${LOCATION}yw01"
SERVICE_NAME="${ORG_SERVICE_NAME}-${PROFILE}"
echo "create ${SERVICE_NAME} service for ${IMG_PREF}"
SET_COMMS="--publish 1101:1101 --replicas 1 --network ${NETWORK} --label service.name=${SERVICE_NAME} --hostname=${SERVICE_NAME} --name ${SERVICE_NAME}"
SET_ENV="--env service=${SERVICE_NAME} --env location=${LOCATION} --env slot={{.Task.Slot}} --env profile=${PROFILE}"
SET_CONSTRAINT="--constraint engine.labels.location==${LOCATION} --constraint engine.labels.service.type==common"
echo "docker service create ${SET_COMMS} ${SET_ENV} ${SET_CONSTRAINT} ${IMAGE_NAME}"
docker network  create  -d  overlay  ${NETWORK}
docker  service  create ${SET_COMMS} ${SET_ENV} ${SET_CONSTRAINT}  ${IMAGE_NAME}


#PUBLISHEDIP=$(docker-machine   ip   bjcommon2)
#echo "the PUBLISHEDIP is ${PUBLISHEDIP}"
#iptables -t nat -I PREROUTING -p tcp --dport  1100 -j DNAT --to ${PUBLISHEDIP}
#iptables -t nat -I POSTROUTING -p tcp --dport 1100  -j MASQUERADE
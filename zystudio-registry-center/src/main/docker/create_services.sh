#!/bin/bash


#HOSTNAME_TEMPLATE="${ZONE}{{.Task.Slot}}.{{.Service.Name}}.zystudio.com"
echo "create registry conter services for zystudio"

NETWORK=zystudio_common
IMAGE="my.docker.registry.com:5000/zystudio/registry_center:0.0.1"
PROJECT="regcen"


ZONE="bjyw1"
NAME="${ZONE}.${PROJECT}.zystudio.com"
docker-machine ssh manager1 docker  service  create  --replicas 1 --network ${NETWORK} --label service_name=${NAME} --hostname=${NAME}  --env zone=${ZONE} --env slot="{{.Task.Slot}}" --constraint engine.labels.location==${ZONE} --constraint engine.labels.type==common --name ${NAME}  ${IMAGE}

ZONE="bjdx1"
NAME="${ZONE}.${PROJECT}.zystudio.com"
docker-machine ssh manager1 docker  service  create  --replicas 1 --network ${NETWORK} --label service_name=${NAME} --hostname=${NAME}  --env zone=${ZONE} --env slot="{{.Task.Slot}}" --constraint engine.labels.location==${ZONE} --constraint engine.labels.type==common --name ${NAME}  ${IMAGE}

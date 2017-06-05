#!/bin/bash


echo "create registry conter services for zystudio"

NETWORK=zystudio_common
IMAGE="my.docker.registry.com:5000/zystudio/registry_center:0.0.1"
PROJECT="regcen"
ZONE="bj_yw_1"
HOSTNAME_TEMPLATE="${ZONE}{{.Task.Slot}}.{{.Service.Name}}.zystudio.com"
NAME=${PROJECT}-${ZONE}
echo $HOSTNAME_TEMPLATE

docker-machine ssh manager1 docker  service  create  --replicas 1 --network ${NETWORK} --label service_name=${NAME} --hostname=${HOSTNAME_TEMPLATE}  --env zone=${ZONE} --env slot="{{.Task.Slot}}" --constraint engine.labels.location==${ZONE} --constraint engine.labels.type==common --name ${NAME}  ${IMAGE}

ZONE="bj_dx_1"
HOSTNAME_TEMPLATE="${ZONE}{{.Task.Slot}}.{{.Service.Name}}.zystudio.com"
NAME=${PROJECT}-${ZONE}
docker-machine ssh manager1 docker  service  create  --replicas 1 --network ${NETWORK} --label service_name=${NAME} --hostname=${HOSTNAME_TEMPLATE}  --env zone=${ZONE} --env slot="{{.Task.Slot}}" --constraint engine.labels.location==${ZONE}  --name ${NAME}  ${IMAGE}

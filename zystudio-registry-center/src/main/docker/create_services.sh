#!/bin/bash


#HOSTNAME_TEMPLATE="${ZONE}{{.Task.Slot}}.{{.Service.Name}}.zystudio.com"
echo "create registry conter services for zystudio"

NETWORK=zystudio_common
IMAGE="my.docker.registry.com:5000/zystudio/registry_center:0.0.1"
PROJECT="regcen"

SET_COMMS="--replicas 1 --network ${NETWORK} "


ZONE="bjyw1"
NAME="${PROJECT}-${ZONE}"
#SET_ENV="--env zone=${ZONE} --env slot=\"{{.Task.Slot}}\" --env registry.peers=\"http://regcen-bjyw1:1100/eureka\"  --env registry.hostname=${NAME}"
SET_ENV="--env zone=${ZONE} --env slot=\"{{.Task.Slot}}\" "
SET_CONSTRAINT="--constraint engine.labels.location==${ZONE} --constraint engine.labels.type==common"


echo "xxx ${SET_ENV} ${SET_CONSTRAINT}"
docker-machine ssh manager1 docker  service  create ${SET_COMMS} --publish 1100:1100 --label service_name=${NAME} --hostname=${NAME}  ${SET_ENV} ${SET_CONSTRAINT} --name ${NAME}  ${IMAGE}

ZONE="bjdx1"
NAME="${PROJECT}-${ZONE}"
#SET_ENV="--env zone=${ZONE} --env slot=\"{{.Task.Slot}}\" --env registry.peers=\"http://www.163.com/eureka,http://192.168.1.1/eureka\"  --env registry.hostname=${NAME}"
SET_ENV="--env zone=${ZONE} --env slot=\"{{.Task.Slot}}\" "
SET_CONSTRAINT="--constraint engine.labels.location==${ZONE} --constraint engine.labels.type==common"

docker-machine ssh manager1 docker  service  create ${SET_COMMS}  --label service_name=${NAME} --hostname=${NAME}  ${SET_ENV} ${SET_CONSTRAINT} --name ${NAME}  ${IMAGE}



PUBLISHEDIP=$(docker-machine   ip   bjcommon2)
echo "the PUBLISHEDIP is ${PUBLISHEDIP}"
#iptables -t nat -I PREROUTING -p tcp --dport  1100 -j DNAT --to ${PUBLISHEDIP}
#iptables -t nat -I POSTROUTING -p tcp --dport 1100  -j MASQUERADE

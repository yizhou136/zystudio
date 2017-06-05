#!/bin/bash


BASE_PROP="-d virtualbox  --virtualbox-host-dns-resolver   --engine-insecure-registry  my.docker.registry.com:5000  --engine-registry-mirror  http://my.docker.registry.com:5000 "

MANAGER_LABELS="--engine-label os=boot2docker --engine-label type=manager  --engine-label location=bj"

BJ_COMMON_LABELS="--engine-label os=boot2docker --engine-label type=common --engine-label location=bj"
BJ_SERVICE_LABELS="--engine-label os=boot2docker --engine-label type=service --engine-label location=bj"

SH_COMMON_LABELS="--engine-label os=boot2docker --engine-label type=common   --engine-label location=sh"
SH_SERVICE_LABELS="--engine-label os=boot2docker --engine-label type=service --engine-label location=sh"


MANAGER_NAME=manager1
docker-machine  create ${BASE_PROP} ${MANAGER_LABELS}  ${MANAGER_NAME}
docker-machine ssh ${MANAGER_NAME}  docker swarm init  --advertise-addr  eth1


MANAGER_IP=`docker-machine  ssh  ${MANAGER_NAME}  ifconfig eth1 |awk -F '[ :]+' 'NR==2 {print $4}' `
MANAGER_SWARM_TOKEN=`docker-machine ssh  ${MANAGER_NAME}  docker swarm join-token worker  -q`
SWARM_JOIN_CMD=" docker swarm join --token ${MANAGER_SWARM_TOKEN} ${MANAGER_IP}:2377 "

echo "the ${MANAGER_NAME} ip: ${MANAGER_IP} SWARM_TOKEN:${MANAGER_SWARM_TOKEN} \n SWARM_JOIN_CMD:${SWARM_JOIN_CMD}"



WORK_NAME=bjcommon1
docker-machine  create ${BASE_PROP} ${BJ_COMMON_LABELS}dx1 ${WORK_NAME}
echo "${WORK_NAME} join swarm ${MANAGER_NAME} ip: ${MANAGER_IP}"
docker-machine  ssh  ${WORK_NAME} ${SWARM_JOIN_CMD} 

WORK_NAME=bjcommon2
docker-machine  create ${BASE_PROP} ${BJ_COMMON_LABELS}yw1 ${WORK_NAME}
echo "${WORK_NAME} join swarm ${MANAGER_NAME} ip: ${MANAGER_IP}"
docker-machine  ssh  ${WORK_NAME} ${SWARM_JOIN_CMD} 



#WORK_NAME=shcommon1
#docker-machine  create ${BASE_PROP} ${SH_COMMON_LABELS} ${WORK_NAME}
#echo "${WORK_NAME} join swarm ${MANAGER_NAME} ip: ${MANAGER_IP}"
#docker-machine  ssh  ${WORK_NAME} ${SWARM_JOIN_CMD} 





#docker-machine  create ${BASE_PROP} ${BJ_SERVICE_LABELS}  --swarm   bjservice1 


#docker-machine  create ${BASE_PROP} ${SH_COMMON_LABELS}   --swarm   shcommon1 
#docker-machine  create ${BASE_PROP} ${SH_SERVICE_LABELS}  --swarm   shservice1 

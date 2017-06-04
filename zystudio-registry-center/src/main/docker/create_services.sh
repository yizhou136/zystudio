#!/bin/bash


echo "create registry conter services for zystudio"

IMAGE="my.docker.registry.com:5000/zystudio/registry_center:0.0.1"
PROJECT="regcen"
ZONE="bj"
HOSTNAME_TEMPLATE="{{.Service.Name}}-${ZONE}{{.Task.Slot}}"

NAME=${PROJECT}-${ZONE}


echo $HOSTNAME_TEMPLATE


#docker-machine ssh manager1 docker  service  create  --label  service=regcen-shdx  --hostname="{{.Service.Name}}{{.Task.Slot}}"  --env spring.profiles.active="{{.Service.Name}}{{.Task.Slot}}"    --constraint engine.labels.location==sh    --name regcen-shdx  my.docker.registry.com:5000/zystudio/registry_center:0.0.1 
#docker-machine ssh manager1 docker  service  create  --replicas 2  --label  service=regcen-bj  --hostname="{{$var := .Task.Slot}}{{if eq 1 $var}}  {{.Service.Name}}{{.Task.Slot}} {{ else }} {{.Service.Name}}{{.Task.Slot}} {{end}}"  --env spring.profiles.active="xxxx" --constraint engine.labels.type=common    --name regcen-shdx  my.docker.registry.com:5000/zystudio/registry_center:0.0.1 

docker-machine ssh manager1 docker  service  create  --replicas 2  --label service_name=${NAME} --hostname=${HOSTNAME_TEMPLATE}  --env zone=${ZONE} --env slot="{{.Task.Slot}}" --constraint engine.labels.type==common  --name ${NAME}  ${IMAGE}

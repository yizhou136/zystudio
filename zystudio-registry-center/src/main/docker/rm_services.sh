#!/bin/bash


echo "remove registry conter services for zystudio"

PROJECT="regcen"

ZONE="bjyw1"
NAME="${PROJECT}-${ZONE}"

echo "remove the service ${NAME}"
docker-machine ssh manager1 docker  service  rm  ${NAME} 

ZONE="bjdx1"
NAME="${PROJECT}-${ZONE}"

echo "remove the service ${NAME}"
docker-machine ssh manager1 docker  service  rm  ${NAME} 

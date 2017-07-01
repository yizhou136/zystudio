#!/bin/bash

CURPWD=`pwd`

docker    service  rm  registry-center-bj

docker  rmi reg.docker.zystudio.site:5000/micro-services/registry-center:0.0.1
docker  rmi reg.docker.zystudio.site:5000/micro-services/registry-center-dep:0.0.1

sh  ${CURPWD}/src/main/docker/dockerbuild.sh

sh  ${CURPWD}/src/main/docker/create_services_bj.sh


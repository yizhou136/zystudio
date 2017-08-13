#!/bin/bash

CURPWD=`pwd`

cd ..

git pull

sh  ${CURPWD}/src/main/docker/dockerbuild.sh

sh  ${CURPWD}/src/main/docker/create_services.sh


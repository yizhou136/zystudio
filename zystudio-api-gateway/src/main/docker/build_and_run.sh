#!/bin/bash

git pull

PWD=`pwd`
cd zystudio-api-gateway

set -e
sh  src/main/docker/dockerbuild.sh
sh  src/main/docker/create_services.sh
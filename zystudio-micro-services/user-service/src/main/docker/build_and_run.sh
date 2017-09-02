#!/bin/bash

git pull

PWD=`pwd`
cd zystudio-micro-services/user-service

set -e
sh  src/main/docker/dockerbuild.sh
sh  src/main/docker/create_services.sh
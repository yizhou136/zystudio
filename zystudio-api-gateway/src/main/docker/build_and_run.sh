#!/bin/bash

git pull

PWD=`pwd`
set -e

sh  zystudio-api-gateway/src/main/docker/dockerbuild.sh

sh  zystudio-api-gateway/src/main/docker/create_services.sh


#!/bin/bash

git pull

sudo sh zystudio-commons-depend-build.sh

PWD=`pwd`
cd zystudio-micro-services/user-service

set -e

sh  src/main/docker/dockerbuild.sh
sh  src/main/docker/create_services.sh
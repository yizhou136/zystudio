#!/bin/bash

git pull

#sh zystudio-commons-depend-build.sh

PWD=`pwd`
cd zystudio-micro-services/weibo-service

set -e

sh  src/main/docker/dockerbuild.sh
sh  src/main/docker/create_services.sh
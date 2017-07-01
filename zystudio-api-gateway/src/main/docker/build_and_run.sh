#!/bin/bash

CURPWD=`pwd`


sh  ${CURPWD}/src/main/docker/dockerbuild.sh

sh  ${CURPWD}/src/main/docker/create_services.sh


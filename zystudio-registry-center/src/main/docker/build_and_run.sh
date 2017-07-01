#!/bin/bash

CURPWD=`pwd`


sh  ${CURPWD}/dockerbuild.sh

sh  ${CURPWD}/create_services_bj.sh


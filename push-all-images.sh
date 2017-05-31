#!/bin/bash

set -e

# Docker image prefix
REGPREFIX=registry.aliyuncs.com/jingshanlb/

docker push ${REGPREFIX}discovery-server
docker push ${REGPREFIX}gateway
docker push ${REGPREFIX}foo
docker push ${REGPREFIX}bar
docker push ${REGPREFIX}foobar
# docker push $(docker images -q)
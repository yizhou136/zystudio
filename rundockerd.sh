#!/bin/bash


dockerd -D -H=unix:///var/run/docker.sock -H tcp://0.0.0.0:2375 --label is_local_host=true --label type=service

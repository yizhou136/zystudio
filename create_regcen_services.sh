#!/bin/bash


echo "create registry conter services for zystudio"


docker service   create --replicas=2  --hostname="{{.Node.ID}}-{{.Service.Name}}"  --name mybusybox  --constraint engin_name=work2   busybox 




docker service create  --label profile=dev  --hostname="{{.Node.ID}}-{{.Service.Name}}-{{.Task.Name}}-{{.Task.Slot}}-{{.Task}}}"  --env spring.active.profile="dev-{{.Service.Labels}}"  --name mybusybox  --constraint en
gine.labels.engin_name==work2   busybox  top

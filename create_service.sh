


docker service   create --replicas=2  --hostname="{{.Node.ID}}-{{.Service.Name}}"  --name mybusybox  --constraint engin_name=work2   busybox 





 create --replicas=2  --label profile=dev  --hostname="{{.Node.ID}}-{{.Service.Name}}" --env spring.active.profile="dev-{{.Service.Labels}}"  --name mybusybox  --constraint
  engine.labels.engin_name==work2   busybox  top

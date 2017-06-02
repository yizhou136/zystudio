#FROM  registry.cn-qingdao.aliyuncs.com/zystudio/micro_services:registry_center_dep_0.0.1

ARG FROM_DEP="my.docker.registry.com:5000/micro_services/registry_center_dep" 
FROM ${FROM_DEP}

VOLUME /tmp
COPY target/*.jar app.jar


RUN sh -c 'touch /app.jar'

RUN echo $(date) > /image_built_at
ENTRYPOINT ["java","-Dloader.path=/dependency","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.profiles.active=${SPRING_PROFILES_ACTIVE}"]

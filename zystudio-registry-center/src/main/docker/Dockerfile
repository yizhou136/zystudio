#FROM  registry.cn-qingdao.aliyuncs.com/zystudio/micro_services:registry_center_dep_0.0.1

ARG FROM_DEP="my.docker.registry.com:5000/micro_services/registry_center_dep" 
FROM ${FROM_DEP}

VOLUME /tmp
COPY target/*.jar app.jar

COPY src/main/docker/enterpoint.sh  enterpoint.sh


RUN sh -c 'touch /app.jar'
#RUN sh -c 'touch /enterpoint.sh'

RUN sh -c 'chmod +x /enterpoint.sh'

RUN echo $(date) > /image_built_at

USER root

#CMD ["sudo","sh","-c","/enterpoint.sh"]
CMD /enterpoint.sh
#ENTRYPOINT ["java","-Dloader.path=/dependency","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

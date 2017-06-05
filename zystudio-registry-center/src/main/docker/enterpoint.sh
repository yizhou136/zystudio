#/bin/bash


env
cat /etc/hostname


#PROFILE=`$(spring.profiles.active)`

PROJECT="regcen"
ZONE="$zone"
SLOT="$slot"

echo "the zone:$ZONE slot:$SLOT"


if [ "${ZONE}" = "bj" ]; then
    if [ ${SLOT} = 1 ] ; then
        PROFILE="bj_dx_${SLOT}"
    else
        PROFILE="bj_yw_${SLOT}"
    fi
else 
  PROFILE="sh_${SLOT}"
fi

echo "show spring.profiles.active:${PROFILE} and run app.jar"

java -Dloader.path=/dependency -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=${PROFILE} -jar /app.jar 

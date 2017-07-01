#/bin/bash
env
cat /etc/hostname

SERVICE="$service"
LOCATION="$location"
SLOT="$slot"
PROFILE="$profile"

if [ "${LOCATION}" = "bj" ]; then
    if [ ${SLOT} = 1 ] ; then
        PROFILE="bjdx${SLOT}"
    else
        PROFILE="bjyw1"
    fi
fi

echo "run the ${SERVICE} at ${LOCATION}@${SLOT} ${PROFILE}"
java -Dloader.path=/dependency -Dspring.profiles.active=${PROFILE} -jar /app.jar
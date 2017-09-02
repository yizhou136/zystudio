#/bin/bash

env
cat /etc/hostname

SERVICE="$service"
LOCATION="$location"
SLOT="$slot"
PROFILE="$profile"

echo "run the ${SERVICE} at ${LOCATION}@${SLOT} ${PROFILE}"
java -Dloader.path=/dependency -Dspring.profiles.active=${PROFILE} -jar /app.jar
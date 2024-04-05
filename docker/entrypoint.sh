#!/bin/bash
echo "java -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE:-default} -Dfile.enconding=UTF-8 -jar /app/text-to-image-demo.jar $@"
exec java -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE:-default} -Dfile.enconding=UTF-8 -jar /app/text-to-image-demo.jar $@

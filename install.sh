#!/bin/bash
dir=(api-gateway config registry product-service
     user-service statistic-service domain
     hazelcast-cache-client hazelcast-cache)
       
echo "Starting build modules"
for i in ${dir[*]}
do
    printf "Module %s\n" $i
    cd $i
    echo "entering $(pwd)"
    eval "mvn -U clean install"
    eval "docker build -t project-$i . && docker tag project-$i alkonoriev/distributed-lab-$i && \
          docker push alkonoriev/distributed-lab-$i"
    cd ..
done
echo "Done"

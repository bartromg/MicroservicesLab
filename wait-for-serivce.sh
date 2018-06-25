#!/bin/bash
host="$1"
port=
case $host in
    config)port=8888;;
    registry)port=8761;;
esac

curl http://localhost:$port &>/dev/null
while [[ $? != 0 ]]
do
    >&2 echo "$host is unavailavle - sleeping"
    sleep 1
    curl http://localhost:$port &>/dev/null
done
>&2 echo "$host is up"

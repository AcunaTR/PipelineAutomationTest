#!/bin/sh

function createFail {
    echo "Creation of docker image failed"
}
trap createFail ERROR

docker tag jowi:latest jowi:redundant
docker build -t jowi:latest .

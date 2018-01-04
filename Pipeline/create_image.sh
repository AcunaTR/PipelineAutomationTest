#!/bin/sh

function createFail {
    echo "Creation of docker image failed"
}
trap createFail ERROR

docker build -t jowi:latest .

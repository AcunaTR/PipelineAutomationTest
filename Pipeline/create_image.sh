#!/bin/sh

function createFail {
    echo "Creation of docker image failed"
}
trap createFail ERR

docker build -t jowi:latest .

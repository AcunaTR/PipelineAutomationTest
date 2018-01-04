#!/bin/sh

function deployFail {
    echo "Deploy to AWS Lambda failed"
}
trap deployFail ERROR

docker tag jowi:latest 015887481462.dkr.ecr.us-east-1.amazonaws.com/acuna-jowi:latest
$(aws ecr get-login --no-include-email --region us-east-1)
docker push 015887481462.dkr.ecr.us-east-1.amazonaws.com/acuna-jowi:latest
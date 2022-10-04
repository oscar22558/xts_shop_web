#!/bin/bash
image="xts-storage-dev";
storageRoot="./file-server";
if [ -z "$(docker -v)" ]; then
    printf "Please install docker first.\n";
else
    if [[ $1 == "-r" ]]; then
        printf "Rebuild docker image $image...\n";
        docker build -t xts-storage-dev:latest $storageRoot;
    else
        if [ -z "$(docker image ls $image -q)" ]; then
            printf "Build docker image $image...\n";
            docker build -t xts-storage-dev:latest $storageRoot;
        fi
    fi
    printf "Start container for $image...\n";
    containerId="$(docker run --rm  -it -d -p 8081:80 -v $(pwd)/$storageRoot/images:/www/images $image)";
    printf "Container id: $containerId\n"
fi
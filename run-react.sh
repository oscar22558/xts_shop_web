#!/bin/bash
source $HOME/.profile
if [ -z $XTS_APP_ROOT ]
then 
    echo "app root directory env variable is not defined."
    exit 0
fi
cd $XTS_APP_ROOT/frontend
yarn start
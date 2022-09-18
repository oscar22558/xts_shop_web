source $HOME/.profile
if [ -z $XTS_APP_ROOT ]
then
    echo "export XTS_APP_ROOT=\"$(pwd)\"" >> $HOME/.profile
    source $HOME/.profile
fi

open run-react.sh
open run-spring.sh
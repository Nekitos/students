#!/bin/sh

JAVA_PATH=`which java`
SCRIPT_LOCATION=$0
READLINK=`which readlink`
DIRNAME=`which dirname`

if [ -z $JAVA_PATH ]; then
    echo "Java not found! Please install JVM and run program."
    exit 1
fi

JAVA_VERSION=$(java -version 2>&1 | sed 's/java version "\(.*\)\.\(.*\)\..*"/\1\2/; 1q')

if [ "$JAVA_VERSION" -ge 16 ]; then
    echo "Java version done"
else
    echo "Need java version greater or equal 1.6"
    exit 2
fi


if [ -x "$READLINK" ]; then
    while [ -L "$SCRIPT_LOCATION" ]; do
        SCRIPT_LOCATION=`"$READLINK" -e "$SCRIPT_LOCATION"`
    done
fi

STUD_APP_HOME=`dirname "$SCRIPT_LOCATION"`/..

eval "$JAVA_PATH" -jar $STUD_APP_HOME/lib/students.jar

exit 0
#!/bin/bash

timeout=30

function isContainerRunning() {
  echo "Checking if $1 has finished running"
  currentTimeout=$2
  while [ "$currentTimeout" -gt 0 ] ; do
    if [ "$( docker container inspect -f '{{.State.Running}}' "$1" )" == "true" ]; then
      echo "$1 is running, exiting..."
      exit
    else
      echo "$1 not running, countdown: $currentTimeout"
    fi
    sleep 1
    currentTimeout=$((currentTimeout-1))
  done
}

for arg in "$@"; do
  isContainerRunning "$arg" "$timeout" &
done

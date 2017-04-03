#!/usr/bin/env bash

#usage: run search.sh with file extension as 1st argument
#and the desired path as the second

#change to the directory
cd $2

#$1 is the first argument given to the command, i.e. file type
cmd="*."$1
ls -r | grep $cmd
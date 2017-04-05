#!/usr/bin/env bash

#usage: run searchByFileType.sh with file extension as 1st argument
#and the desired path as the second

#Returns all files of this type, with no regard to names

#change to the directory. This does not search sub-directories
cd $2

#$1 is the first argument given to the command, i.e. file type
cmd="*."$1
ls | grep $cmd
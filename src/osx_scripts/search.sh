#!/usr/bin/env bash

#usage: run search.sh with file extension as argument
#run it in whatever directory you want to search, it includes
#sub-directories
cmd = '*.' + $1
ls -r | grep cmd
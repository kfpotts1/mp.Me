#!/usr/bin/env bash

#search for files of a certain type by name
#parameter: name format incl. type; directory
#ex:  *_*_*_(*).mp3 /Users/Eric/Music/Nikhilback/

cd $2

ls | grep $1

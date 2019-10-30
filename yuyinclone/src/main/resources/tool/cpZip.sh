#!/usr/bin/expect -f
set timeout 30
set path [lindex $argv 0]
spawn scp -r -P 22 $path  clone@192.168.1.72:/home/clone/datasets
expect "*password:"
send "clone123clone\r"
expect eof

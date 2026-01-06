#!/bin/bash
set -e

service mysql start
sleep 5

mysql -u root <<EOF
CREATE DATABASE IF NOT EXISTS theralink;
EOF

exec java -jar app.jar

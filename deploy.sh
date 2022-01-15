#!/bin/bash
mvn clean install
docker build -t zilioti.dev/daofab .
docker-compose up -d

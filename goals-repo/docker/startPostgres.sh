#!/bin/bash

docker build -t mypg .
docker run --name postgres-goals -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d mypg


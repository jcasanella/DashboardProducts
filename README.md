# DashboardProducts

[<img src="https://img.shields.io/travis/playframework/play-scala-starter-example.svg"/>](https://travis-ci.org/playframework/play-scala-starter-example)

This is an app built using play framework.
At this moment is still based with start play app.

Used technologies:

- play framework (scala)
- bootstrap
- jquery
- chartJs

## Running

Run this using [sbt](http://www.scala-sbt.org/).  If you downloaded this project from <http://www.playframework.com/download> then you'll find a prepackaged version of sbt in the project directory:

```bash
sbt run
```

And then go to <http://localhost:9000> to see the running web application.

There are several demonstration files available in this template.

## Objective

The objective of this app is to show some data from a database.
TODO:

- Work with single page

- Remove any reference to the start play app

- Create a dashboard using websockets to create a Real Time dashboard

- Start to use React js

## Postgres using docker

These are all the steps to download and use postgres from a docker container.

- pull the postgres image. If we don't specify the version, will pull the lastest.
```
docker pull postgres
```

- create a folder where we store the database files
```
mkdir -p $HOME/docker/volumes/postgres
```

- run the postgres container from the image.
```
docker run --rm   --name pg-docker -e POSTGRES_PASSWORD=docker -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data  postgres
```
We have provided several options to the docker run command:  
**--rm:** Automatically removes the container and it's associated file system when exit  
**--name:** Id name for the container  
**-e** Expose environment variable. In this case the variable is the password for the postgres user. PASSWORD is docker  
**-d** launches the container in background mode  
**-p** bind port 5432 with localhost port 5432  
**-v** mount the folder with /var/lib/postgres  

- let's connect with the container command line
```
docker exec -it pg-docker bash
```
Now we're connected to the container terminal.

- Connection with pgsql
```
psql -h localhost -U postgres -d postgres
```

- Create db
```
CREATE DATABASE mytestdb;
```
**Note:** to exit \q

- Install pgadmin4
```
docker pull fenglc/pgadmin4
```

- Run pgadmin4
```
docker run --name some-pgadmin4 -p 5050:5050 -d fenglc/pgadmin4
```

*pgadmin4 user:* pgadmin4@pgadmin.org
*pgadmin4 password:* admin

- To create the server with pgadmin4, we need to know the container ip (postgres)
```
docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' container_name_or_id
```

## Changes

1. [2018-12-24]
- Enable chartJS
- Page using bootstrap - (first version of the page)
- Play json serialization

2. [2018-12-25]
- Play send json to the client and show using jquery

3. [2018-12-29]
- First prototype layout

4. [2018-12-30]
- Refinament prototype layout - Show graph in the dashboard and maximize button

5. [2019-01-01]
- Unit test using H2 in memory database and slick

6. [2019-01-10]
- Populate listbox from postgresql + unit test postgresql

7. [2019-01-15]
- Enable scalaFmt and format the code

8. [2019-01-20]
- Multiproject: DashboardApp (Web) and DashboardHttp (Client to web service)

9. [2019-01-22]
- Parser command line httpClient
- Get properties from build.sbt

10. [2019-02-10]
- Creation jenkinsFile

10. [2019-02-18]
- Enable dockerCompose

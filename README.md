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


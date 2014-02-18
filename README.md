redditsearcher
===

The searcher for the front page of the Internet

===

[![Build Status](http://adapter.afterburna.com/jenkins/buildStatus/icon?job=redditsearcher)](http://adapter.afterburna.com/jenkins/job/redditsearcher/)

### To build:

Prerequisites:

    * Java 7 or newer
    * Maven 3.0.4 or newer
    * Tomcat 7 or newer

Install:

    mvn clean install
    cp target/redditsearcher-*.war into Tomcat's webapps directory
    start your tomcat

### To run the tests:

    mvn test

<!--
Resources for Newcomers
---
  - [The Wiki](https://...)
  -->

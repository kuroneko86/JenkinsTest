package com.kukan

import groovy.json.JsonSlurperClassic

@NONCPS
def getSonarQubeResults(String url)
{
    println url

    def jsonSlurper = new JsonSlurperClassic()
    def getSonarResults = steps.sh(script: "curl " + url, returnStdout: true)
    def reportStatus = jsonSlurper.parseText(getSonarResults)

    println reportStatus
    println reportStatus[0]
    println reportStatus[1]
    println reportStatus[0][0]
    println reportStatus[1][0]
}
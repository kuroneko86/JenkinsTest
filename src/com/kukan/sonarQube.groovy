package com.kukan

import com.cloudbees.groovy.cps.NonCPS
import groovy.json.JsonSlurperClassic

class sonarQube {

    def steps

    sonarQube(steps)
    {
        this.steps = steps
    }

    @NonCPS
    def getSonarQubeResults(String url)
    {
        println url

        def jsonSlurper = new JsonSlurperClassic()
        def getSonarResults = this.steps.sh(script: "curl " + url, returnStdout: true)
        def reportStatus = jsonSlurper.parseText(getSonarResults)

        return reportStatus
    }
}
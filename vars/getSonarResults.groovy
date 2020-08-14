import groovy.json.JsonSlurper

def call() {
    def loopLimit = 12
    def loopinterator = 0
    def proceed = 0
    def severityLimit = 25

    def jsonSlurper = new JsonSlurper()

    while (!proceed) {
        println "Checking report readiness..."
        def getSonarResults = steps.sh(script: "curl $sonarTaskID", returnStdout: true)
        def reportStatus = jsonSlurper.parseText(getSonarResults)
        println reportStatus
        println reportStatus[0]
        println reportStatus[1]
        println reportStatus[0][0]
        println reportStatus[1][0]
        if(reportStatus == "OK") {
            println "Report ready"
            proceed = 1
        }
        else if (loopinterator >= loopLimit) {
            error ("Report is taking too long, aborting")            
        }
        else {
            println "Report not ready yet, waiting 5 seconds";
            loopinterator++
            sleep(5000)
        }
    }

    def checkQualityGate = steps.sh(script: "curl 'http://192.168.10.106:9000/api/qualitygates/project_status?projectKey=$sonarProjectKey'", returnStdout: true)
    def qualityGate = jsonSlurper.parseText(checkQualityGate)
    if(qualityGate == "OK") {
        println "Quality gate passed"
    }
    else {
        error("Quality gate failed, aborting")
    }
}
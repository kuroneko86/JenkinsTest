def call() {
    def loopLimit = 12
    def loopinterator = 0
    def checkStatusPattern = '"status":"SUCCESS"'
    def proceed = 0
    def resultsPattern = 'total":([^(,|})]*)'
    def severityLimit = 25

    while (proceed) {
        printlin "Checking report readiness..."
        def getSonarResults = steps.sh(script: "curl $sonarTaskID", returnStdout: true)
        if(getSonarResults =~ checkStatusPattern) {
            printlin "Report ready"
            proceed = 1
        }
        else if (loopinterator >= loopLimit) {
            error ("Report is taking too long, aborting")            
        }
        else {
            printlin "Report not ready yet, waiting 5 seconds";
            loopinterator++
            sleep(5000)
        }
    }

    def checkBlockers = steps.sh(script: "curl 'http://192.168.10.106:9000/api/issues/search?pageSize=500&componentKeys=$sonarProjectKey&ps=500&p=1&severities=BLOCKER'", returnStdout: true)
    def resultsBlockers = (checkBlockers =~ resultsPattern).findAll().first()
    regex = resultsBlockers.first()
    result = regex.substring((regex.indexOf(':') + 1))
    resultint = result as int
    println result
    println resultint
    if(resultint > 0) {
        error("Blocker found in results, aborting")
    }
    else {
        println "No blockers found"
    }


    def checkSeverity = steps.sh(script: "curl 'http://192.168.10.106:9000/api/issues/search?pageSize=500&componentKeys=$sonarProjectKey&ps=500&p=1&severities=CRITICAL,MAJOR'", returnStdout: true)
    def resultsSeverity = (checkSeverity =~ resultsPattern).findAll().first()
    regex = resultsBlockers.first()
    result = regex.substring((regex.indexOf(':') + 1))
    resultint = result as int
    println result
    println resultint
    if(resultint > severityLimit) {
        error("Too many errors reported, aborting")
    }
    else {
        println "Number of errors (" + resultsSeverity + ") below limit (" + severityLimit + ") found"
    }
}
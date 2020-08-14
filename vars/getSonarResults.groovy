def call() {
    def loopLimit = 12
    def loopinterator = 0
    def checkStatusPattern = '"status":"SUCCESS"'
    def proceed = 0

    while ($proceed) {
        printlin "Checking report readiness..."
        def getSonarResults = steps.sh(script: "curl $sonarTaskID", returnStdout: true)
        if(getSonarResults =~ checkStatusPattern) {
            printlin "Report ready"
            $proceed = 1
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

    if(!$abort) {
        def resultsPattern = 'total":([^,]*)'
        def severityLimit = 25

        def checkBlockers = steps.sh(script: "curl http://192.168.10.106:9000/api/issues/search?pageSize=500&componentKeys=$sonarProjectKey&ps=500&p=1&severities=BLOCKER", returnStdout: true)
        def resultsBlockers = (checkBlockers =~ taskIDPattern).findAll().first()
        if($resultsBlockers > 0)
        {
            error("Blocker found in results, aborting")
        }

        def checkSeverity = steps.sh(script: "curl http://192.168.10.106:9000/api/issues/search?pageSize=500&componentKeys=$sonarProjectKey&ps=500&p=1&severities=CRITICAL,MAJOR", returnStdout: true)
        def resultsSeverity = (checkSeverity =~ taskIDPattern).findAll().first()
        if($resultsSeverity > $severityLimit)
        {
            error("Too many errors reported, aborting")
        }
    }
}
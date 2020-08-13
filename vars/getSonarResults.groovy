def call() {
    def loopLimit = 12
    def loopinterator = 0
    def checkStatusPattern = '"status":"SUCCESS"'
    def continue = false
    
    do {
        printlin "Checking results..."
        def getSonarResults = steps.sh(script: "curl $sonarTaskID", returnStdout: true)
        if(getSonarResults =~ checkStatusPattern) {
            printlin "Results completed"
            $continue = true
        }
        elseif (loopinterator >= loopLimit{
            printlin "Results are still not ready, aborting"
            $continue = true
        }
        else {
            printlin "Results not ready yet, waiting 5 seconds";
            loopinterator++
            sleep(5000)
        }
    } while ($continue)
}
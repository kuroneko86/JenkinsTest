import com.kukan.sonarQube

def call(String sonarProjectKey, String sonarTaskID) {
    def loopLimit = 12
    def loopinterator = 0
    def proceed = 0
    def severityLimit = 25

    def sonarQube = new sonarQube(steps)

    while (!proceed) {
        println "Checking report readiness..."
        
        json = sonarQube.getSonarQubeResults(sonarTaskID)
        println json
        reportStatus = readJSON text: json

        if(reportStatus['task']['status'] == "SUCCESS") {
            println "Report ready"
            proceed = 1
        }
        else if (loopinterator >= loopLimit) {
            error ("Report is taking too long, aborting")            
        }
        else {
            println "Report not ready yet, waiting 5 seconds";
            loopinterator++
            sleep(5)
        }
    }

    qualityGateUrl = "http://192.168.10.37:9000/api/qualitygates/project_status?projectKey=$sonarProjectKey"
    json = sonarQube.getSonarQubeResults(qualityGateUrl)
    println json
    qualityGateStatus = readJSON text: json
    println qualityGateStatus['projectStatus']['status']
    if(qualityGateStatus['projectStatus']['status'] == "OK") {
        println "Quality gate passed"
    }
    else {
        error("Quality gate failed, aborting")
    }
}
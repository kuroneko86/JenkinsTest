def call() {
    def taskIDPattern = "INFO: More about the report processing at (.*)"
    def execSonar = steps.sh(script: "sonar-scanner-4.4.0.2170-linux/bin/sonar-scanner -Dsonar.projectKey=${sonarProjectKey} -Dsonar.sources=${sonarFolder} -Dsonar.host.url=http://192.168.10.106:9000 -Dsonar.login=${sonarLogin}"
    def sonarTaskID = (execSonar =~ taskIDPattern)
    println sonarTaskID  
}
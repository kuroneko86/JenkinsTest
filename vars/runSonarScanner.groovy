def call(String sonarProjectKey, String sonarLogin) {
    def workspace = WORKSPACE
    def taskIDPattern = "INFO: More about the report processing at (.*)"
    def execSonar = steps.sh(script: "sonar-scanner-5.0.1.3006-linux/bin/sonar-scanner -X -Dsonar.projectKey=${sonarProjectKey} -Dsonar.sources=. -Dsonar.host.url=http://192.168.10.37:9000 -Dsonar.login=${sonarLogin}", returnStdout: true)
    println execSonar
    def sonarTaskID = (execSonar =~ taskIDPattern).findAll().first()
    println sonarTaskID
    env.sonarTaskID = sonarTaskID[1]
}

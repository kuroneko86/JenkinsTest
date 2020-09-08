def call(String sonarProjectKey, String sonarLogin) {
    def workspace = WORKSPACE
    def taskIDPattern = "INFO: More about the report processing at (.*)"
    def execSonar = steps.sh(script: "sonar-scanner-4.4.0.2170-linux/bin/sonar-scanner -Dsonar.projectKey=${sonarProjectKey} -Dsonar.sources=. -Dsonar.host.url=http://192.168.10.106:9000 -Dsonar.login=${sonarLogin}", returnStdout: true)
    println execSonar
    def sonarTaskID = (execSonar =~ taskIDPattern).findAll().first()
    println sonarTaskID
    env.sonarTaskID = sonarTaskID[1]
}
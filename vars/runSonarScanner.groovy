def call(String sonarLogin) {
    def workspace = WORKSPACE
    def taskIDPattern = "INFO: More about the report processing at (.*)"
    def execSonar = steps.sh(script: "sonar-scanner-6.2.1.4610-linux-x64/bin/sonar-scanner -Dsonar.host.url=http://192.168.10.37:9000 -Dsonar.login=${sonarLogin}", returnStdout: true)
    println execSonar
    def sonarTaskID = (execSonar =~ taskIDPattern).findAll().first()
    println sonarTaskID
    env.sonarTaskID = sonarTaskID[1]
    def removeSonar = steps.sh(script: "rm sonar-scanner-6.2.1.4610-linux-x64/ -r")
}

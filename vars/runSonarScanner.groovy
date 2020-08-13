def call() {
    def workspace = WORKSPACE
    def taskIDPattern = "INFO: More about the report processing at (.*)"
    //def execSonar = steps.sh(script: "sonar-scanner-4.4.0.2170-linux/bin/sonar-scanner -Dsonar.projectKey=${sonarProjectKey} -Dsonar.sources=${sonarFolder} -Dsonar.host.url=http://192.168.10.106:9000 -Dsonar.login=${sonarLogin}")
    def sout = new StringBuilder(), serr = new StringBuilder()
    def proc = "$workspace/sonar-scanner-4.4.0.2170-linux/bin/sonar-scanner -Dsonar.projectKey=$sonarProjectKey -Dsonar.sources=$sonarFolder -Dsonar.host.url=http://192.168.10.106:9000 -Dsonar.login=$sonarLogin".execute()
    proc.consumeProcessOutput(sout, serr)
    proc.waitForOrKill(30000)
    println "out> $sout err> $serr"
    def sonarTaskID = (sout =~ taskIDPattern).findAll()
    println sonarTaskID.first();
}
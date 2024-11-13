def call() {
    sh "curl https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-6.2.1.4610-linux-x64.zip --output sonarscanner.zip"
    //sh "apt install unzip"
    sh "unzip sonarscanner.zip"
}

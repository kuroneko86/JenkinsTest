def call() {
    sh "curl https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-4.8.1.3023-linux.zip --output sonarscanner.zip"
    //sh "apt install unzip"
    sh "unzip sonarscanner.zip"
}

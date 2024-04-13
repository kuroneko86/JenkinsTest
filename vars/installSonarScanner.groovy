def call() {
    sh "curl https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-5.0.1.3006-linux.zip --output sonarscanner.zip"
    //sh "apt install unzip"
    sh "unzip sonarscanner.zip"
}

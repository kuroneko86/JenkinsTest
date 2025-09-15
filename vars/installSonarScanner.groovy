def call() {
    sh "rm -rf sonarscanner.zip"
    sh "curl https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-6.2.1.4610-linux-x64.zip --output sonarscanner.zip"
    sh "unzip -q sonarscanner.zip"
}

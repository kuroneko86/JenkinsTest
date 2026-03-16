def call() {
    sh "rm -rf sonar-scanner*"
    sh "curl https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-8.0.1.6346-linux-x64.zip --output sonarscanner.zip"
    sh "unzip -q sonarscanner.zip"
}

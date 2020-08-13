def call() {
    sh "curl https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-4.4.0.2170-linux.zip --output sonarscanner.zip"
    sudo apt install unzip
    unzip sonarscanner -d sonarscanner
}
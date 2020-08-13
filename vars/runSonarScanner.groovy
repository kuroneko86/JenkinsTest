def call() {
    echo "sonarscanner/bin/sonar-scanner -Dsonar.projectKey=${sonarProjectKey} -Dsonar.sources=${sonarFolder} -Dsonar.host.url=http://192.168.10.106:9000 -Dsonar.login=${sonarLogin}"
}
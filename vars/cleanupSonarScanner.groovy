def call() {
    def removeSonar2 = steps.sh(script: "rm .scannerwork -rf")
    def removeSonar2 = steps.sh(script: "rm sonar* -rf")
} 
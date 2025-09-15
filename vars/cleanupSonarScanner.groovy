def call() {
    def removeSonar = steps.sh(script: "rm .scannerwork -rf")
    def removeSonar2 = steps.sh(script: "rm sonar* -rf")
} 
def call() {
    def execSonar = steps.sh(script: "curl http://192.168.10.106:9000/api/ce/task?id=AXPpWR2kr3-nW6vSSpXd")
}
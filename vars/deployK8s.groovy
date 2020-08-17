def call() {
    println steps.sh(script: "kubectl apply -f kube-pod.yaml", returnStdout: true)
}
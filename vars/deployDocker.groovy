def call() {
    def checkInstance = steps.sh(script: "docker ps -f name=${containerName}", returnStdout: true)
    if(checkInstance.indexOf(containerName))
    {
        println steps.sh(script: "docker stop ${containerName}", returnStdout: true)
        println steps.sh(script: "docker rm ${containerName}", returnStdout: true)
    }

    println steps.sh(script: "docker create --name ${containerName} --restart always -p 8181:8080 $registry:$BUILD_NUMBER", returnStdout: true)
    println steps.sh(script: "docker start ${containerName}", returnStdout: true)
}
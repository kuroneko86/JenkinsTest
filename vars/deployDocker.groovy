def call() {
    def sout = new StringBuilder(), serr = new StringBuilder()
    def proc = 'docker ps -f name=${containerName}'.execute()
    proc.consumeProcessOutput(sout, serr)
    proc.waitForOrKill(1000)
    println "out> $sout err> $serr"
    if($sout.indexOf(${containerName}))
    {
        //kill it
    }
}
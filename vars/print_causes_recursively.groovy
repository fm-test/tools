#!/usr/bin/env groovy

def printCausesRecursively(cause) {
    if (cause.class.toString().contains("UpstreamCause")) {
        echo "This job was caused by " + cause.toString()
        for (upCause in cause.getUpstreamCauses()) {
            printCausesRecursively(upCause)
        }
    } else {
        job_chain_names = cause.toString()
    }
}
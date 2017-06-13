#!/usr/bin/env groovy

def call(LinkedHashMap para) {
    arg = [:]
    def para_key_set = para.keySet()
    for (int i = 0; i < para.size(); i++){
        arg[para_key_set[i]] = para[para_key_set[i]]
    }
    return arg
}
#!/usr/bin/env groovy

def call(LinkedHashMap para){
    arg = []
    def para_key_set = para.keySet()
    for (int i = 0; i < para.size(); i ++){
        def tmp = [$class: 'StringParameterValue', name: para_key_set[i], value: para[para_key_set[i]]]
        arg.add(tmp)
    }
    return arg
}
#!/usr/bin/env groovy

def call(LinkedHashMap para){
    arg = []
    def para_key_set = para.keySet()
    for (int i = 0; i < para.size(); i++){
        if ( para[para_key_set[i]] instanceof String){
            def tmp = [$class: 'StringParameterValue', name: para_key_set[i], value: para[para_key_set[i]]]
            arg.add(tmp)
        }
        else{
            def tmp = [$class: 'BooleanParameterValue', name: para_key_set[i], value: para[para_key_set[i]]]
            arg.add(tmp)
        }
    }
    return arg
}
#!/usr/bin/env groovy

def call(HashMap para){
    arg = []
    def para_key_set = para.KeySet()
    for (int i = 0; i < para.size(); i ++){d
        def tmp = [$class: 'StringParameterValue', name: para_key_set[i], value: para[para_key_set[i]]]
        arg.add(tmp)
    }
    return arg
}
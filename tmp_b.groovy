node{
    stage('build GGA1') {
        fm_ver = params.FM_VER
        port = params.PORT
        build_gga = params.BUILD_GGA
        
        def all_res = "SUCCESS"
        def arg_mgae_svp = [
            [$class: 'StringParameterValue', name: 'FM_VER', value: "${fm_ver}"],
            [$class: 'StringParameterValue', name: 'MODEL', value: "svp"],
            [$class: 'StringParameterValue', name: 'ANDROID_VER', value: "6.0.1"]
          /*FM_VER : fm_ver,
          MODEL : "svp",
          ANDROID_VER : '6.0.1'*/
        ]
        mgae_b_svp = build job: build_gga, parameters: arg_mgae_svp
        //mgae_b_svp = build(arg_mgae_svp, build_gga)
        def arg_nightly_svp_601 = [
            [$class: 'StringParameterValue', name: 'RECONCILER', value: "${build_gga}"],
            [$class: 'StringParameterValue', name: 'SHIM', value: "${build_gga}"],
            [$class: 'StringParameterValue', name: 'BUILD_NO', value: "${mgae_b_svp.getNumber()}"],
            [$class: 'StringParameterValue', name: 'PORT', value: "${port}"],
            [$class: 'StringParameterValue', name: 'FM_VER', value: "${fm_ver}"],
            [$class: 'StringParameterValue', name: 'GGA_SETTINGS', value: ""],
            [$class: 'StringParameterValue', name: 'ADDITIONAL_OPTS', value: "-s"],
            [$class: 'StringParameterValue', name: 'GLES2_PARTITIONS', value: "32"],
            [$class: 'StringParameterValue', name: 'GLES3_PARTITIONS', value: "50"],
            [$class: 'StringParameterValue', name: 'KHRONOS_PARTITIONS', value: "16"],
            [$class: 'StringParameterValue', name: 'ANDROID_VER', value: "6.0.1"]
        ]
        echo "hei!"
        arg_nightly_svp_601 = setDisplayName arg_nightly_svp_601
        echo "no!"
        //b_dpyname = set_display_name(arg_nightly_svp_601)
        //arg_nightly_svp_601.add([$class: 'StringParameterValue', name: 'DISPLAY_NAME', value: b_dpyname.description])
        //writeFile file: "output/usefulfile.txt", text: "This file is useful, need to archive it."
    
        // Write an useless file, which is not needed to be archived.
        //writeFile file: "output/uselessfile.md", text: "This file is useless, no need to archive it."
    
        //stage "Archive build output"
        
        // Archive the build output artifacts.
        //archive excludes: 'output/*.md', includes: 'output/*.txt'
    
        //build job: 'shawn_leaf', parameters: [[$class: 'StringParameterValue', name: 'test_para', value: 'hello from pipeline']]
    }
    stage('build GGA2'){
        def arg_mgae = [
            [$class: 'StringParameterValue', name: 'FM_VER', value: "${fm_ver}"],
            [$class: 'StringParameterValue', name: 'MODEL', value: "fvp"],
            [$class: 'StringParameterValue', name: 'ANDROID_VER', value: "4.4.2, 6.0.1, 7.0.0, "]
        ]
        mgae_b = build job: build_gga, parameters: arg_mgae
        def arg_nightly = [
            [$class: 'StringParameterValue', name: 'RECONCILER', value: "${build_gga}"],
            [$class: 'StringParameterValue', name: 'SHIM', value: "${build_gga}"],
            //[$class: 'StringParameterValue', name: 'BUILD_NO', value: "${mgae_b_svp.getNumber()}"],
            [$class: 'StringParameterValue', name: 'PORT', value: "${port}"],
            [$class: 'StringParameterValue', name: 'FM_VER', value: "${fm_ver}"],
            [$class: 'StringParameterValue', name: 'GGA_SETTINGS', value: ""],
            [$class: 'StringParameterValue', name: 'ADDITIONAL_OPTS', value: ""],
            [$class: 'StringParameterValue', name: 'GLES2_PARTITIONS', value: "32"],
            [$class: 'StringParameterValue', name: 'GLES3_PARTITIONS', value: "50"],
            [$class: 'StringParameterValue', name: 'GLES31_PARTITIONS', value: "50"],
            [$class: 'StringParameterValue', name: 'KHRONOS_PARTITIONS', value: "16"],
            [$class: 'StringParameterValue', name: 'RETRY', value: "2"]
        ]

        arg_nightly_442 = arg_nightly.clone()
        arg_nightly_442.add([$class: 'StringParameterValue', name: 'ANDROID_VER', value: "4.4.2"])
        arg_nightly_601 = arg_nightly.clone()
        arg_nightly_601.add([$class: 'StringParameterValue', name: 'ANDROID_VER', value: "6.0.1"])
        arg_nightly_700 = arg_nightly.clone()
        arg_nightly_601.add([$class: 'StringParameterValue', name: 'ANDROID_VER', value: "7.0.0"])
        
        b_dpyname = setDisplayName(arg_nightly_442)
        arg_nightly_442.add([$class: 'StringParameterValue', name: 'DISPLAY_NAME', value: b_dpyname.description])
        b_dpyname = setDisplayName(arg_nightly_601)
        arg_nightly_601.add([$class: 'StringParameterValue', name: 'DISPLAY_NAME', value: b_dpyname.description])
        b_dpyname = setDisplayName(arg_nightly_700)
        arg_nightly_700.add([$class: 'StringParameterValue', name: 'DISPLAY_NAME', value: b_dpyname.description])
    
        def arg_mgae_css = [
            [$class: 'StringParameterValue', name: 'FM_VER', value: "${fm_ver}"],
            [$class: 'StringParameterValue', name: 'MODEL', value: "subsys"],
            [$class: 'StringParameterValue', name: 'PLATFORM', value: "Linux64_GCC-4.8"],
            [$class: 'StringParameterValue', name: 'SLAVE_LABEL', value: "ubuntu1604xGCC540"],
            [$class: 'StringParameterValue', name: 'ANDROID_VER', value: "6.0.1.css"]
        ]
        echo "green beans"
        mgae_b_css = build job: build_gga, parameters: arg_mgae_css
        echo "mongo"
    }
    
    stage('parallel'){
        def arg_nightly_css = [
            [$class: 'StringParameterValue', name: 'RECONCILER', value: "${build_gga}"],
            [$class: 'StringParameterValue', name: 'SHIM', value: "${build_gga}"],
            [$class: 'StringParameterValue', name: 'BUILD_NO', value: "${mgae_b_css.getNumber()}"],
            [$class: 'StringParameterValue', name: 'PORT', value: "${port}"],
            [$class: 'StringParameterValue', name: 'FM_VER', value: "${fm_ver}"],
            [$class: 'StringParameterValue', name: 'EGL_PARTITIONS', value: "4"],
            [$class: 'StringParameterValue', name: 'EGL_TEST_LIST_DB', value: "egl-master-nv.csv"],
            [$class: 'StringParameterValue', name: 'KHRONOS_PARTITIONS', value: "16"],
            [$class: 'StringParameterValue', name: 'KHR_TEST_LIST_DB', value: "khronos-nv.csv"],
            [$class: 'StringParameterValue', name: 'GLES2_PARTITIONS', value: "40"],
            [$class: 'StringParameterValue', name: 'GLES2_TEST_LIST_DB', value: "gles2-master-nv.csv"],
            [$class: 'StringParameterValue', name: 'GLES3_PARTITIONS', value: "80"],
            [$class: 'StringParameterValue', name: 'GLES2_TEST_LIST_DB', value: "gles2-master-nv.csv"],
            [$class: 'StringParameterValue', name: 'GLES31_PARTITIONS', value: "40"],
            [$class: 'StringParameterValue', name: 'GLES2_TEST_LIST_DB', value: "gles31-master-nv.csv"],
            [$class: 'StringParameterValue', name: 'GGA_SETTINGS', value: ""],
            [$class: 'StringParameterValue', name: 'ADDITIONAL_OPTS', value: "-g buzz -t 80"],
            [$class: 'StringParameterValue', name: 'ANDROID_VER', value: "6.0.1.css"],
            [$class: 'StringParameterValue', name: 'SLAVE_LABEL', value: "nvidia && ubuntu1604xGCC540 && css"],
            [$class: 'StringParameterValue', name: 'RETRY', value: "2"],
            [$class: 'StringParameterValue', name: 'IS_GRM', value: "0"],
            [$class: 'StringParameterValue', name: 'ANDROID_VER', value: "6.0.1.css"]
        ]
        echo "checkpoint potato pancake"
        b_dpyname = setDisplayName(arg_nightly_css)
        arg_nightly_css.add([$class: 'StringParameterValue', name: 'DISPLAY_NAME', value: b_dpyname.description])
        ///////////////////////////////////////

        arg_nightly_601_batch = arg_nightly_601.clone()
        
        arg_nightly_601_batch[5] = [$class: 'StringParameterValue', name: 'GGA_SETTINGS', value: "batchCommands=1"]
        b_dpyname = setDisplayName(arg_nightly_601_batch)
        arg_nightly_601_batch.add([$class: 'StringParameterValue', name: 'DISPLAY_NAME', value: b_dpyname.description])

        ///////////////////////////////////////
        join = parallel(
            '1':{build job: 'Cube', parameters: arg_nightly_442, propagate: false}
        )
        join.each {
            all_res = all_res.combine(it.getLastBuild().getResult())
        }
        currentBuild.currentResult = all_res
        /*dothings = [:]
        args.each{
            arg ->
            def block_tmp ={
                node{
                    sh "echo hstream"
                    build job: 'shawn_leaf', parameters: arg
                }
            }
            dothings['test'] = block_tmp
        }
        echo 'hei!'
        parallel(
        "stream 1":{ 
                        node {
                               sh "echo hstream1"
                               build job: 'shawn_leaf', parameters: [[$class: 'StringParameterValue', name: 'test_para', value: 'hello from nu']]
                           } 
                       },
        "stream 2" : { 
                         node {
                               sh "echo hstream2"  
                               build job: 'shawn_leaf', parameters: [[$class: 'StringParameterValue', name: 'test_para', value: 'hello from nu']]
                           } 
                    }
        )*/
    }
}

def setDisplayName(List para){
  args = [:]
  for (int i = 0; i < para.size(); i++){
      args[para[i].name] = para[i].value
  }
  def suffix = ""

  if (args.GGA_SETTINGS ==~ /.*callOnTargetAPI=1.*/) {
    suffix = suffix + ".GRM"
  } else {
    suffix = suffix + ".GGA"
  }
  if (args.ADDITIONAL_OPTS ==~ /.*-s.*/) {
    suffix = suffix + ".svp"
  } else {
    suffix = suffix + ".fvp"
  }
  switch (args.ANDROID_VER) {
    case "4.4.2":
      suffix = suffix + ".442"
      break
    case "7.0.0":
      suffix = suffix + ".700"
      break        
    case "6.0.1.css":
      suffix = suffix + ".601B"
      break
    case "6.0.1.css.cernan.dp":
      suffix = suffix + ".601C"
      break
	case ~/.*6.0.1.*/:
      suffix = suffix + ".601"
      break
    default:
      suffix = suffix + ".xxx" 
  }
  
  if (args.GGA_SETTINGS ==~ /.*batchCommands=1.*/) {
    suffix = suffix + ".batch"
  }
  if (args.ADDITIONAL_OPTS ==~ /.*forceHostEGLConfig.*/) {
    suffix = suffix + ".ForceHostCfg"
  }
  // This suffix will be concatnated at the end of build.display_name
  para.add([$class: 'StringParameterValue', name: 'DISPLAY_NAME', value: suffix])
  return para
}
def converter(HashMap para){
    arg = []
    def para_key_set = para.KeySet()
    for (int i = 0; i < para.size(); i ++){d
        def tmp = [$class: 'StringParameterValue', name: para_key_set[i], value: para[para_key_set[i]]]
        arg.add(tmp)
    }
    return arg
}
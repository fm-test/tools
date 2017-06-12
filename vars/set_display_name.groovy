#!/usr/bin/env groovy

def set_display_name( List para ){
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
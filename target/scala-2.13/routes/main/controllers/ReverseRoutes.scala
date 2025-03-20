// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:10
package controllers {

  // @LINE:10
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:10
    def simulationPage: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "simulation")
    }
  
    // @LINE:11
    def evaluateDate: Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "evaluate-date")
    }
  
  }


}

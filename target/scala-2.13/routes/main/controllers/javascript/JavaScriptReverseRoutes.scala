// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:10
package controllers.javascript {

  // @LINE:10
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:10
    def simulationPage: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.simulationPage",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "simulation"})
        }
      """
    )
  
    // @LINE:11
    def evaluateDate: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.evaluateDate",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "evaluate-date"})
        }
      """
    )
  
  }


}

// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:7
package controllers.javascript {

  // @LINE:7
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:10
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:10
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }

  // @LINE:13
  class ReversePortfolioControllerTest(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:13
    def getPortfolio: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PortfolioControllerTest.getPortfolio",
      """
        function(userId0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "portfolio/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("userId", userId0))})
        }
      """
    )
  
    // @LINE:14
    def addAsset: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PortfolioControllerTest.addAsset",
      """
        function(userId0,symbol1,quantity2) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "portfolio/add/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("userId", userId0)) + "/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("symbol", symbol1)) + "/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Int]].javascriptUnbind + """)("quantity", quantity2))})
        }
      """
    )
  
    // @LINE:15
    def removeAsset: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PortfolioControllerTest.removeAsset",
      """
        function(userId0,symbol1,quantity2) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "portfolio/remove/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("userId", userId0)) + "/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("symbol", symbol1)) + "/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Int]].javascriptUnbind + """)("quantity", quantity2))})
        }
      """
    )
  
  }


}

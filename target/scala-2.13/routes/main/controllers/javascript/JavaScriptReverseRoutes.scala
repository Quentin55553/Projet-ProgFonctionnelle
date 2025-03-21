// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:7
package controllers.javascript {

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
  class ReverseUserController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:13
    def register: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.register",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "register"})
        }
      """
    )
  
    // @LINE:14
    def login: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.login",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
        }
      """
    )
  
    // @LINE:16
    def getUserInfo: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.getUserInfo",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "userInfo"})
        }
      """
    )
  
  }

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

  // @LINE:21
  class ReversePriceController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:21
    def getPrice: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PriceController.getPrice",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getPrice"})
        }
      """
    )
  
    // @LINE:23
    def getHistoricalData: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PriceController.getHistoricalData",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getHistoricalData"})
        }
      """
    )
  
  }

  // @LINE:17
  class ReversePortfolioController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
    def getPortfolios: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PortfolioController.getPortfolios",
      """
        function(userId0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "portfolios/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Int]].javascriptUnbind + """)("userId", userId0))})
        }
      """
    )
  
    // @LINE:18
    def createPortfolio: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PortfolioController.createPortfolio",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "createPortfolio"})
        }
      """
    )
  
    // @LINE:19
    def addAsset: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PortfolioController.addAsset",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addAsset"})
        }
      """
    )
  
    // @LINE:20
    def removeAsset: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PortfolioController.removeAsset",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "removeAsset"})
        }
      """
    )
  
  }

  // @LINE:24
  class ReverseSimulationController2(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:24
    def simulatePortfolio: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SimulationController2.simulatePortfolio",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "simulate"})
        }
      """
    )
  
  }


}


package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

object simulation extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.4*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Simulation</title>
    </head>
    <body>
        <h1>Simulation de Prix</h1>
        <form action=""""),_display_(/*10.24*/controllers/*10.35*/.routes.SimulationController.evaluate(_)),format.raw/*10.75*/("""" method="GET">
            <label for="date">Entrez une date (YYYY-MM-DD):</label>
            <input type="text" id="date" name="date">
            <button type="submit">Évaluer</button>
        </form>
    </body>
</html>"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/simulation.scala.html
                  HASH: c0f9842efe10f52c9a7bd72e19d476e3321f0336
                  MATRIX: 727->1|823->3|851->5|1077->204|1097->215|1158->255
                  LINES: 21->1|26->1|27->2|35->10|35->10|35->10
                  -- GENERATED --
              */
          
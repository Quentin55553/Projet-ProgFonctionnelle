
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

object evaluation extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(evaluation: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.22*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Évaluation</title>
    </head>
    <body>
        <h1>Évaluation pour la Date</h1>
        <pre>"""),_display_(/*10.15*/evaluation),format.raw/*10.25*/("""</pre>
        <a href=""""),_display_(/*11.19*/routes/*11.25*/.SimulationController.index),format.raw/*11.52*/("""">Retour</a>
    </body>
</html>"""))
      }
    }
  }

  def render(evaluation:String): play.twirl.api.HtmlFormat.Appendable = apply(evaluation)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (evaluation) => apply(evaluation)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/evaluation.scala.html
                  HASH: 703c78360adbc2c0c497dcf4ec0aaac35d332ca0
                  MATRIX: 734->1|849->21|877->23|1099->218|1130->228|1183->254|1198->260|1246->287
                  LINES: 21->1|26->1|27->2|35->10|35->10|36->11|36->11|36->11
                  -- GENERATED --
              */
          
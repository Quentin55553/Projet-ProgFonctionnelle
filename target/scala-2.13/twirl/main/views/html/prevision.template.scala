
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
/*1.2*/import java.time.LocalDate

object prevision extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[LocalDate,Double,Double,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(date: LocalDate, predictedPriceRegression: Double, predictedPriceMA: Double)(implicit request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.112*/("""

"""),format.raw/*4.1*/("""<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <title>Prévisions</title>
    </head>
    <body>
        <h1>Prévisions pour la date : """),_display_(/*11.40*/date),format.raw/*11.44*/("""</h1>
        <p>Prix prédit (Régression Linéaire) : """),_display_(/*12.49*/predictedPriceRegression),format.raw/*12.73*/(""" """),format.raw/*12.74*/("""€</p>
        <p>Prix prédit (Moyenne Mobile) : """),_display_(/*13.44*/predictedPriceMA),format.raw/*13.60*/(""" """),format.raw/*13.61*/("""€</p>

        <a href=""""),_display_(/*15.19*/routes/*15.25*/.HomeController.simulationPage),format.raw/*15.55*/("""">Retour à la simulation</a>
    </body>
</html>"""))
      }
    }
  }

  def render(date:LocalDate,predictedPriceRegression:Double,predictedPriceMA:Double,request:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(date,predictedPriceRegression,predictedPriceMA)(request)

  def f:((LocalDate,Double,Double) => (RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (date,predictedPriceRegression,predictedPriceMA) => (request) => apply(date,predictedPriceRegression,predictedPriceMA)(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/prevision.scala.html
                  HASH: aa929b581c9d2dbb06f3a5760a3af3db23957475
                  MATRIX: 432->1|798->30|1004->140|1034->144|1239->322|1264->326|1346->381|1391->405|1420->406|1497->456|1534->472|1563->473|1617->500|1632->506|1683->536
                  LINES: 17->1|22->2|27->2|29->4|36->11|36->11|37->12|37->12|37->12|38->13|38->13|38->13|40->15|40->15|40->15
                  -- GENERATED --
              */
          
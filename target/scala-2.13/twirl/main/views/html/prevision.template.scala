
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
        <style>
                body """),format.raw/*10.22*/("""{"""),format.raw/*10.23*/("""
                    """),format.raw/*11.21*/("""font-family: Arial, sans-serif;
                    background-color: #f4f4f9;
                    color: #333;
                    margin: 0;
                    padding: 20px;
                """),format.raw/*16.17*/("""}"""),format.raw/*16.18*/("""
                """),format.raw/*17.17*/("""h1 """),format.raw/*17.20*/("""{"""),format.raw/*17.21*/("""
                    """),format.raw/*18.21*/("""color: #4CAF50;
                """),format.raw/*19.17*/("""}"""),format.raw/*19.18*/("""
                """),format.raw/*20.17*/("""p """),format.raw/*20.19*/("""{"""),format.raw/*20.20*/("""
                    """),format.raw/*21.21*/("""font-size: 1.1em;
                    margin: 10px 0;
                """),format.raw/*23.17*/("""}"""),format.raw/*23.18*/("""
                """),format.raw/*24.17*/("""a """),format.raw/*24.19*/("""{"""),format.raw/*24.20*/("""
                    """),format.raw/*25.21*/("""display: inline-block;
                    margin-top: 20px;
                    padding: 10px 15px;
                    background-color: #4CAF50;
                    color: white;
                    text-decoration: none;
                    border-radius: 5px;
                """),format.raw/*32.17*/("""}"""),format.raw/*32.18*/("""
                """),format.raw/*33.17*/("""a:hover """),format.raw/*33.25*/("""{"""),format.raw/*33.26*/("""
                    """),format.raw/*34.21*/("""background-color: #45a049;
                """),format.raw/*35.17*/("""}"""),format.raw/*35.18*/("""
        """),format.raw/*36.9*/("""</style>
    </head>
    <body>
        <h1>Prévisions pour la date : """),_display_(/*39.40*/date),format.raw/*39.44*/("""</h1>
        <p>Prix prédit (Régression Linéaire) : <strong>"""),_display_(/*40.57*/predictedPriceRegression),format.raw/*40.81*/(""" """),format.raw/*40.82*/("""€</strong></p>
        <p>Prix prédit (Moyenne Mobile) : <strong>"""),_display_(/*41.52*/predictedPriceMA),format.raw/*41.68*/(""" """),format.raw/*41.69*/("""€</strong></p>

        <a href=""""),_display_(/*43.19*/routes/*43.25*/.HomeController.simulationPage),format.raw/*43.55*/("""">Retour à la simulation</a>
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
                  HASH: 66724af62f5e5cd7661f2fea72828ad30c3f5f52
                  MATRIX: 432->1|798->30|1004->140|1034->144|1214->296|1243->297|1293->319|1520->518|1549->519|1595->537|1626->540|1655->541|1705->563|1766->596|1795->597|1841->615|1871->617|1900->618|1950->640|2050->712|2079->713|2125->731|2155->733|2184->734|2234->756|2550->1044|2579->1045|2625->1063|2661->1071|2690->1072|2740->1094|2812->1138|2841->1139|2878->1149|2979->1223|3004->1227|3094->1290|3139->1314|3168->1315|3262->1382|3299->1398|3328->1399|3391->1435|3406->1441|3457->1471
                  LINES: 17->1|22->2|27->2|29->4|35->10|35->10|36->11|41->16|41->16|42->17|42->17|42->17|43->18|44->19|44->19|45->20|45->20|45->20|46->21|48->23|48->23|49->24|49->24|49->24|50->25|57->32|57->32|58->33|58->33|58->33|59->34|60->35|60->35|61->36|64->39|64->39|65->40|65->40|65->40|66->41|66->41|66->41|68->43|68->43|68->43
                  -- GENERATED --
              */
          
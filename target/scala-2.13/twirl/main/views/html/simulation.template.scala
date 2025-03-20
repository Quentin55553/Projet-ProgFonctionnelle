
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
/*1.2*/import play.api.mvc.RequestHeader
/*2.2*/import play.api.i18n.Messages

object simulation extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[List[PriceDate],RequestHeader,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*4.2*/(prices: List[PriceDate])(implicit request: RequestHeader, messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.80*/("""

"""),format.raw/*6.1*/("""<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <title>Simulation</title>
        <style>
                body """),format.raw/*12.22*/("""{"""),format.raw/*12.23*/("""
                    """),format.raw/*13.21*/("""font-family: Arial, sans-serif;
                    background-color: #f4f4f9;
                    color: #333;
                    margin: 0;
                    padding: 20px;
                """),format.raw/*18.17*/("""}"""),format.raw/*18.18*/("""
                """),format.raw/*19.17*/("""h1 """),format.raw/*19.20*/("""{"""),format.raw/*19.21*/("""
                    """),format.raw/*20.21*/("""color: #4CAF50;
                """),format.raw/*21.17*/("""}"""),format.raw/*21.18*/("""
                """),format.raw/*22.17*/("""table """),format.raw/*22.23*/("""{"""),format.raw/*22.24*/("""
                    """),format.raw/*23.21*/("""width: 100%;
                    border-collapse: collapse;
                    margin: 20px 0;
                """),format.raw/*26.17*/("""}"""),format.raw/*26.18*/("""
                """),format.raw/*27.17*/("""th, td """),format.raw/*27.24*/("""{"""),format.raw/*27.25*/("""
                    """),format.raw/*28.21*/("""padding: 10px;
                    text-align: left;
                    border-bottom: 1px solid #ddd;
                """),format.raw/*31.17*/("""}"""),format.raw/*31.18*/("""
                """),format.raw/*32.17*/("""th """),format.raw/*32.20*/("""{"""),format.raw/*32.21*/("""
                    """),format.raw/*33.21*/("""background-color: #4CAF50;
                    color: white;
                """),format.raw/*35.17*/("""}"""),format.raw/*35.18*/("""
                """),format.raw/*36.17*/("""tr:hover """),format.raw/*36.26*/("""{"""),format.raw/*36.27*/("""
                    """),format.raw/*37.21*/("""background-color: #f1f1f1;
                """),format.raw/*38.17*/("""}"""),format.raw/*38.18*/("""
                """),format.raw/*39.17*/("""form """),format.raw/*39.22*/("""{"""),format.raw/*39.23*/("""
                    """),format.raw/*40.21*/("""margin-top: 20px;
                """),format.raw/*41.17*/("""}"""),format.raw/*41.18*/("""
                """),format.raw/*42.17*/("""label """),format.raw/*42.23*/("""{"""),format.raw/*42.24*/("""
                    """),format.raw/*43.21*/("""font-size: 1.1em;
                    margin-right: 10px;
                """),format.raw/*45.17*/("""}"""),format.raw/*45.18*/("""
                """),format.raw/*46.17*/("""input[type="date"], button """),format.raw/*46.44*/("""{"""),format.raw/*46.45*/("""
                    """),format.raw/*47.21*/("""padding: 10px;
                    font-size: 1em;
                    border: 1px solid #ccc;
                    border-radius: 5px;
                """),format.raw/*51.17*/("""}"""),format.raw/*51.18*/("""
                """),format.raw/*52.17*/("""button """),format.raw/*52.24*/("""{"""),format.raw/*52.25*/("""
                    """),format.raw/*53.21*/("""background-color: #4CAF50;
                    color: white;
                    cursor: pointer;
                """),format.raw/*56.17*/("""}"""),format.raw/*56.18*/("""
                """),format.raw/*57.17*/("""button:hover """),format.raw/*57.30*/("""{"""),format.raw/*57.31*/("""
                    """),format.raw/*58.21*/("""background-color: #45a049;
                """),format.raw/*59.17*/("""}"""),format.raw/*59.18*/("""
        """),format.raw/*60.9*/("""</style>
    </head>
    <body>
        <h1>Prix historiques</h1>
        <table>
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Prix</th>
                </tr>
            </thead>
            <tbody>
                """),_display_(/*72.18*/for(price <- prices) yield /*72.38*/ {_display_(Seq[Any](format.raw/*72.40*/("""
                    """),format.raw/*73.21*/("""<tr>
                        <td>"""),_display_(/*74.30*/price/*74.35*/.date),format.raw/*74.40*/("""</td>
                        <td>"""),_display_(/*75.30*/price/*75.35*/.price),format.raw/*75.41*/(""" """),format.raw/*75.42*/("""€</td>
                    </tr>
                """)))}),format.raw/*77.18*/("""
            """),format.raw/*78.13*/("""</tbody>
        </table>

        <h2>Entrez une date pour évaluer les indicateurs</h2>
        <form action=""""),_display_(/*82.24*/routes/*82.30*/.HomeController.evaluateDate),format.raw/*82.58*/("""" method="POST">
            <label for="date">Date (YYYY-MM-DD):</label>
            <input type="date" id="date" name="date" required>
            <button type="submit">Évaluer</button>
        </form>
    </body>
</html>"""))
      }
    }
  }

  def render(prices:List[PriceDate],request:RequestHeader,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(prices)(request,messages)

  def f:((List[PriceDate]) => (RequestHeader,Messages) => play.twirl.api.HtmlFormat.Appendable) = (prices) => (request,messages) => apply(prices)(request,messages)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/simulation.scala.html
                  HASH: dfac8211ef20186dd541031691ee224ceb4e0652
                  MATRIX: 432->1|473->37|844->71|1017->149|1047->153|1227->305|1256->306|1306->328|1533->527|1562->528|1608->546|1639->549|1668->550|1718->572|1779->605|1808->606|1854->624|1888->630|1917->631|1967->653|2110->768|2139->769|2185->787|2220->794|2249->795|2299->817|2450->940|2479->941|2525->959|2556->962|2585->963|2635->985|2742->1064|2771->1065|2817->1083|2854->1092|2883->1093|2933->1115|3005->1159|3034->1160|3080->1178|3113->1183|3142->1184|3192->1206|3255->1241|3284->1242|3330->1260|3364->1266|3393->1267|3443->1289|3547->1365|3576->1366|3622->1384|3677->1411|3706->1412|3756->1434|3939->1589|3968->1590|4014->1608|4049->1615|4078->1616|4128->1638|4273->1755|4302->1756|4348->1774|4389->1787|4418->1788|4468->1810|4540->1854|4569->1855|4606->1865|4916->2148|4952->2168|4992->2170|5042->2192|5104->2227|5118->2232|5144->2237|5207->2273|5221->2278|5248->2284|5277->2285|5360->2337|5402->2351|5545->2467|5560->2473|5609->2501
                  LINES: 17->1|18->2|23->4|28->4|30->6|36->12|36->12|37->13|42->18|42->18|43->19|43->19|43->19|44->20|45->21|45->21|46->22|46->22|46->22|47->23|50->26|50->26|51->27|51->27|51->27|52->28|55->31|55->31|56->32|56->32|56->32|57->33|59->35|59->35|60->36|60->36|60->36|61->37|62->38|62->38|63->39|63->39|63->39|64->40|65->41|65->41|66->42|66->42|66->42|67->43|69->45|69->45|70->46|70->46|70->46|71->47|75->51|75->51|76->52|76->52|76->52|77->53|80->56|80->56|81->57|81->57|81->57|82->58|83->59|83->59|84->60|96->72|96->72|96->72|97->73|98->74|98->74|98->74|99->75|99->75|99->75|99->75|101->77|102->78|106->82|106->82|106->82
                  -- GENERATED --
              */
          
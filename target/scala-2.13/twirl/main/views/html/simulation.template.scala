
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
/*2.2*/import play.filters.csrf.CSRF
/*3.2*/import play.api.mvc.RequestHeader
/*4.2*/import play.api.i18n.Messages

object simulation extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[List[PriceDate],RequestHeader,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*6.2*/(prices: List[PriceDate])(implicit request: RequestHeader, messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.80*/("""

"""),format.raw/*8.1*/("""<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <title>Simulation</title>
    </head>
    <body>
        <h1>Prix historiques</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Prix</th>
                </tr>
            </thead>
            <tbody>
                """),_display_(/*24.18*/for(price <- prices) yield /*24.38*/ {_display_(Seq[Any](format.raw/*24.40*/("""
                    """),format.raw/*25.21*/("""<tr>
                        <td>"""),_display_(/*26.30*/price/*26.35*/.date),format.raw/*26.40*/("""</td>
                        <td>"""),_display_(/*27.30*/price/*27.35*/.price),format.raw/*27.41*/(""" """),format.raw/*27.42*/("""€</td>
                    </tr>
                """)))}),format.raw/*29.18*/("""
            """),format.raw/*30.13*/("""</tbody>
        </table>

        <h2>Entrez une date pour évaluer les indicateurs</h2>
        <form action=""""),_display_(/*34.24*/routes/*34.30*/.HomeController.evaluateDate),format.raw/*34.58*/("""" method="POST">
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
                  HASH: cae19294423ac35183644f3bf94680346ecd4352
                  MATRIX: 432->3|469->35|510->71|881->105|1054->183|1084->187|1509->585|1545->605|1585->607|1635->629|1697->664|1711->669|1737->674|1800->710|1814->715|1841->721|1870->722|1953->774|1995->788|2138->904|2153->910|2202->938
                  LINES: 17->2|18->3|19->4|24->6|29->6|31->8|47->24|47->24|47->24|48->25|49->26|49->26|49->26|50->27|50->27|50->27|50->27|52->29|53->30|57->34|57->34|57->34
                  -- GENERATED --
              */
          
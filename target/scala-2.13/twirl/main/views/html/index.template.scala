
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
/*1.2*/import data.FinancialAsset
/*2.2*/import java.time.format.DateTimeFormatter

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[FinancialAsset,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*4.2*/(financialAsset: FinancialAsset):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.1*/("""

"""),format.raw/*7.1*/("""<!DOCTYPE html>
<html>
    <head>
        <title>Index de la plateforme</title>
        <link rel="icon" type="image/x-icon" href=""""),_display_(/*11.53*/routes/*11.59*/.Assets.versioned("images/favicon.ico")),format.raw/*11.98*/("""">
    </head>

    <body>
        <h1>Ceci n'est qu'un test de l'API sur la page d'index, cela pourra être retiré à l'avenir</h1>
        <h3>Informations boursières pour l'action AAPL (Apple)</h3>

        <table>
            <tr>
                <td>Prix actuel :</td>
                <td>$ """),_display_(/*21.24*/financialAsset/*21.38*/.currentPrice),format.raw/*21.51*/("""</td>
            </tr>
            <tr>
                <td>Variation :</td>
                <td>$ """),_display_(/*25.24*/financialAsset/*25.38*/.priceChange),format.raw/*25.50*/("""</td>
            </tr>
            <tr>
                <td>Variation (%) :</td>
                <td>"""),_display_(/*29.22*/financialAsset/*29.36*/.percentChange),format.raw/*29.50*/("""</td>
            </tr>
            <tr>
                <td>Prix le plus haut :</td>
                <td>$ """),_display_(/*33.24*/financialAsset/*33.38*/.highPrice),format.raw/*33.48*/("""</td>
            </tr>
            <tr>
                <td>Prix le plus bas :</td>
                <td>$ """),_display_(/*37.24*/financialAsset/*37.38*/.lowPrice),format.raw/*37.47*/("""</td>
            </tr>
            <tr>
                <td>Prix d'ouverture :</td>
                <td>$ """),_display_(/*41.24*/financialAsset/*41.38*/.openPrice),format.raw/*41.48*/("""</td>
            </tr>
            <tr>
                <td>Prix de clôture précédent :</td>
                <td>$ """),_display_(/*45.24*/financialAsset/*45.38*/.closePrice),format.raw/*45.49*/("""</td>
            </tr>
            <tr>
                <td>Date et heure :</td>
                <td>"""),_display_(/*49.22*/financialAsset/*49.36*/.dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'à' HH'h'mm"))),format.raw/*49.107*/("""</td>
            </tr>
        </table>
    </body>
</html>
"""))
      }
    }
  }

  def render(financialAsset:FinancialAsset): play.twirl.api.HtmlFormat.Appendable = apply(financialAsset)

  def f:((FinancialAsset) => play.twirl.api.HtmlFormat.Appendable) = (financialAsset) => apply(financialAsset)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/index.scala.html
                  HASH: 919f5112fc95f08a87df0f5bf9899e0c4ddbc736
                  MATRIX: 432->1|466->29|820->73|946->106|974->108|1133->240|1148->246|1208->285|1530->580|1553->594|1587->607|1715->708|1738->722|1771->734|1901->837|1924->851|1959->865|2095->974|2118->988|2149->998|2284->1106|2307->1120|2337->1129|2472->1237|2495->1251|2526->1261|2670->1378|2693->1392|2725->1403|2855->1506|2878->1520|2971->1591
                  LINES: 17->1|18->2|23->4|28->5|30->7|34->11|34->11|34->11|44->21|44->21|44->21|48->25|48->25|48->25|52->29|52->29|52->29|56->33|56->33|56->33|60->37|60->37|60->37|64->41|64->41|64->41|68->45|68->45|68->45|72->49|72->49|72->49
                  -- GENERATED --
              */
          
<xsl:stylesheet version = "1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match = "/">
	    <html>
            <body>
                <h2> Card List </h2>
                
                <table>
                    <tr>
                        <th> Link </th>
                        <th> Id </th>
                        <th> Data </th>
                        <th> Skrót organizacji </th>
                        <th> Komponent Środowiska </th>
                        <th> Typ karty </th>
                        <th> Rodzaj karty </th>
                        <th> Nr wpisu </th>
                        <th> Znak sprawy </th>
                        <th> Dane wnioskodawcy</th>
                    </tr>
                        <xsl:for-each select="bip.poznan.pl/data/karty_informacyjne/items/karta_informacyjna">
                        <tr>
                            <td><xsl:value-of select="link"/></td>
                            <td><xsl:value-of select="id"/></td>
                            <td><xsl:value-of select="data"/></td>
                            <td><xsl:value-of select="skrot_organizacja"/></td>
                            <td><xsl:value-of select="komponent_srodowiska"/></td>
                            <td><xsl:value-of select="typ_karty"/></td>
                            <td><xsl:value-of select="rodzaj_karty"/></td>
                            <td><xsl:value-of select="nr_wpisu"/></td>
                            <td><xsl:value-of select="znak_sprawy"/></td>
                            <td><xsl:value-of select="dane_wnioskodawcy"/></td>
                    </tr>
                    </xsl:for-each>
                </table>
            </body>	
	    </html>
    </xsl:template>
</xsl:stylesheet>

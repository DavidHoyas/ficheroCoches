<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Listado de Coches</title>
            </head>
            <body>
                <h2>Tabla de Coches</h2>
                <table border="1">
                    <tr>
                        <th>Nombre</th>
                        <th>Tipo</th>
                        <th>Precio</th>
                        <th>Disponible</th>
                    </tr>
                    <xsl:for-each select="Coches/Coche">
                        <tr>
                            <td><xsl:value-of select="Nombre"/></td>
                            <td><xsl:value-of select="Tipo"/></td>
                            <td><xsl:value-of select="Precio"/></td>
                            <td><xsl:value-of select="Disponible"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>

/*
********************************************************************************
* ARCHIVO : enuevo_usuario.java
* DESCRIPCIÓN :proyecto final
cocectra con base de datos en sql
*
* AUTOR :Jesus Ernesto De Leon Gallegos
* integrantes:Laila Ximena AUmad,Juan Diego Banda Moran,Estafania beserril,Cescar chaverri,Kristian Castillo,
*
* FECHA DE INICIO : 06-noviembre-2018
* FECHA DE ENTREGA : 21-noviembre-2018
*********************************************************************************
*/
package paquete;

import java.sql.*;
import javax.swing.*;

public class Conexion {
Connection conect = null;
   public Connection conexion()
    {
      try {
             
           //Cargamos el Driver MySQL
           Class.forName("org.gjt.mm.mysql.Driver");
           conect = DriverManager.getConnection("jdbc:mysql://localhost/tecnologico","netomix","leslie");
         
           //Cargamos el Driver Access
           //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           //Conectar en red base 
           //String strConect = "jdbc:odbc:Driver=Microsoft Access Driver (*.mdb);DBQ=//servidor/bd_cw/cw.mdb";
           //Conectar Localmente
           //String strConect = "jdbc:odbc:Driver=Microsoft Access Driver (*.mdb);DBQ=D:/cwnetbeans/cw.mdb";
          //conect = DriverManager.getConnection(strConect,"",""); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error "+e);
        }
        return conect;
     
}



}

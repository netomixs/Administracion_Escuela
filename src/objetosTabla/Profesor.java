package objetosTabla;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import paquete.Conexion;
import paquete.Consultas;

public class Profesor {

    Conexion conexion;
    Connection cn;
    private Persona persona;
    private String estudio, numTrabajo;
    boolean existe = false;

    public Profesor(String num) {
        conexion = new Conexion();
        cn = conexion.conexion();
        String sql;
        ResultSet rs = null;
        Statement st = null;
        sql = "SELECT * FROM profesores where  num_Trabajo = \"" + num + "\"";
        System.out.println(sql);
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                estudio = rs.getString("estudio");
                existe = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);

        }
        if (existe) {
            persona = new Persona(num);
            numTrabajo = num;
        }

    }

    public Persona getPersona() {
        return persona;
    }

    void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public String getNumTrabajo() {
        return numTrabajo;
    }

    public String getNombreCompleto() {
        return estudio + "." + persona.getNombre() + " " + persona.getPaterno() + " " + persona.getMaterno();
    }

}

package objetosTabla;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import paquete.Conexion;
import paquete.Consultas;

public class Alumno {

    Conexion conexion;
    Connection cn;
    private Persona persona;
    private String carrera, matricula;
    boolean existe = false;

    public Alumno(String matricula) {
        conexion = new Conexion();
        cn = conexion.conexion();
        String sql;
        ResultSet rs = null;
        Statement st = null;
        sql = "SELECT * FROM alumnos where  id = \"" + matricula + "\"";
        System.out.println(sql);
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                carrera = rs.getString("carrera");
                existe = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);

        }
        if (existe) {
            persona = new Persona(matricula);
            this.matricula = matricula;
        }

    }

    public Persona getPersona() {
        return persona;
    }

    void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getCarrera() {
        return carrera;
    }

    void setCarrera(String carrera) {
        this.carrera = carrera;
    }

}

package objetosTabla;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import paquete.Conexion;

public class Persona {

    String nombre, paterno, materno, nacimiento, sexo, civil;
    Direccion direccion;
    Conexion conexion;
    Connection cn;

    public Persona(String id) {
        conexion = new Conexion();
        cn = conexion.conexion();
        getInfoPersona(id);
        
    }


    public String getNombre() {
        return nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public String getCivil() {
        return civil;
    }

    public Direccion getDireccion() {
        return direccion;
    }

     void setNombre(String nombre) {
        this.nombre = nombre;
    }

     void setPaterno(String paterno) {
        this.paterno = paterno;
    }

     void setMaterno(String materno) {
        this.materno = materno;
    }

     void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

     void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setCivil(String civil) {
        this.civil = civil;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    private void getInfoPersona(String id) {
        String sql;
        ResultSet rs = null;
        Statement st = null;
        sql = "SELECT * FROM personas where  id = \"" + id + "\"";
        System.out.println(sql);
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                setNombre(rs.getString("nombre"));
                setPaterno(rs.getString("paterno"));
                setMaterno(rs.getString("Materno"));
                setNacimiento(rs.getString("fecha_nac"));
                setSexo(rs.getString("sexo"));
                setCivil(rs.getString("estado_Civil"));
                direccion=new Direccion(rs.getString("direccion"));
            }
        } catch (SQLException ex) {
         

        }
       
    }
}

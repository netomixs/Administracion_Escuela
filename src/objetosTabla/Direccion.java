/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosTabla;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import paquete.Conexion;
import paquete.Consultas;

/**
 *
 * @author netom
 */
class Direccion {

    String estado, municipio, localidad, calle, num, cp;
    Conexion conexion;
    Connection cn;

    public Direccion(String id) {
        conexion = new Conexion();
        cn = conexion.conexion();
        consultarDireccion(id) ;
    }

    public String getEstado() {
        return estado;
    }

    void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getLocalidad() {
        return localidad;
    }

    void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCalle() {
        return calle;
    }

    void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNum() {
        return num;
    }

    void setNum(String num) {
        this.num = num;
    }

    public String getCp() {
        return cp;
    }

    void setCp(String cp) {
        this.cp = cp;
    }

    private void consultarDireccion(String id) {
        String sql;
        ResultSet rs = null;
        Statement st = null;
        sql = "SELECT * FROM direcciones where  id = \"" + id + "\"";
        System.out.println(sql);
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                setEstado(rs.getString("estado"));
                setMunicipio(rs.getString("municipio"));
                setLocalidad(rs.getString("localidad"));
                setCalle(rs.getString("calle"));
                setNum("num");
                setCp("cp");

            }
        } catch (SQLException ex) {
            Logger.getLogger(Direccion.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
}

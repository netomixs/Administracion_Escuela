package paquete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import objetosTabla.Materias;
import objetosTabla.Profesor;

/**
 *
 * @author netom
 */
public class Consultas {

    Conexion conexion;
    Connection cn;

    public Consultas() {
        conexion = new Conexion();
        cn = conexion.conexion();
    }

    public boolean isAdministradorExist(String id) {
        String sql;
        ResultSet rs = null;
        Statement st = null;
        sql = "SELECT * FROM administradores where  id = \"" + id + "\"";
        System.out.println(sql);
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public boolean accesoAminitrador(String user, String clave, JLabel error) {
        String sql;
        ResultSet rs = null;
        Statement st = null;
        sql = "SELECT * FROM administradores where usuario =\"" + user + "\" AND clave = \"" + clave + "\"";
        System.out.println(sql);
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            error.setVisible(true);
            error.setText("Ocurrio un error con el servidor, intenta mas tarde");

        }
        error.setText("No hay conicidencias con usuarios registrados");
        return false;
    }

    String registrarDireccion(String estado, String municipio, String localidad, String calle, String num, String cp) {
        String sql;
        ResultSet rs = null;
        try {
            sql = "INSERT INTO  direcciones (estado,municipio,localidad,calle,num,cp) VALUES (?,?,?,?,?,?)";
            System.out.println(sql);
            System.out.println(estado);
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, estado);
            pst.setString(2, municipio);
            pst.setString(3, localidad);
            pst.setString(4, calle);
            pst.setString(5, num);
            pst.setString(6, cp);
            System.out.println(pst.getParameterMetaData());
            pst.executeUpdate();
            return getDireccionRegistro(estado, municipio, localidad, calle, num, cp);
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);

        }

        return getDireccionRegistro(estado, municipio, localidad, calle, num, cp);
    }

    String getDireccionRegistro(String estado, String municipio, String localidad, String calle, String num, String cp) {
        String sql;
        ResultSet rs = null;
        PreparedStatement pst = null;
        //sql = "SELECT * FROM direcciones WHERE estado = "+estado+" AND municipio = "+municipio+" AND localidad= "+localidad+" AND calle= "+calle+" AND num= "+num+" AND cp= "+cp+"";

        sql = "SELECT * FROM direcciones WHERE estado = ? AND municipio = ? AND localidad=? AND calle=? AND num=? AND cp=? order by id desc\n"
                + " limit 1";

        System.out.println(sql);
        try {
            pst = cn.prepareStatement(sql);
            pst.setString(1, estado);
            pst.setString(2, municipio);
            pst.setString(3, localidad);
            pst.setString(4, calle);
            pst.setString(5, num);
            pst.setString(6, cp);
            rs = pst.executeQuery();
            while (rs.next()) {

                return rs.getString("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    boolean isPersona(String id) {
        String sql;
        ResultSet rs = null;
        PreparedStatement pst = null;
        sql = "SELECT * FROM personas where id =? ";

        System.out.println(sql);
        try {
            pst = cn.prepareStatement(sql);
            pst.setString(1, id);

            rs = pst.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    String registarPersona(String id, String nombre, String paterno, String materno, String nacimiento, String sexo, String estadocivil, String direccion, JLabel error) {
        if (isPersona(id) == false) {
            String sql;
            ResultSet rs = null;
            PreparedStatement pst = null;

            sql = "INSERT INTO  personas  VALUES (?,?,?,?,?,?,?,?)";
            System.out.println(sql);
            try {
                pst = cn.prepareStatement(sql);
                pst.setString(1, id);
                pst.setString(2, nombre);
                pst.setString(3, paterno);
                pst.setString(4, materno);
                pst.setString(5, nacimiento);;
                pst.setString(6, sexo);
                pst.setString(7, estadocivil);
                pst.setString(8, direccion);
                pst.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
                error.setVisible(true);
                error.setText("Ocurrio un error con la conexion a la base de datos");
                return "";
            }

        } else {
            error.setVisible(true);
            error.setText("La clave ingresada ya se encuentra dentro de la base de datos");
            return id;
        }
        return id;
    }

    boolean registrarAdministrador(String id, String user, String clave, JLabel error) {
        String sql;
        ResultSet rs = null;
        PreparedStatement pst = null;
        if (isAdministradorExist(id)) {
            error.setVisible(true);
            error.setText("Ya se encuentra un usuario con esa clave");
            return false;
        } else {
            sql = "INSERT INTO  administradores  VALUES (?,?,?)";
            System.out.println(sql);
            try {
                pst = cn.prepareStatement(sql);
                pst.setString(1, user);
                pst.setString(2, clave);
                pst.setString(3, id);

                pst.executeUpdate();
                error.setVisible(true);
                error.setText("Se registro el usuario correctamente");
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
                error.setVisible(true);
                error.setText("No es posible registrar el usuario");
                return false;
            }

        }

    }

    public boolean isAlumnoExist(String id) {
        String sql;
        ResultSet rs = null;
        Statement st = null;
        sql = "SELECT * FROM alumnos where  matricula = \"" + id + "\"";
        System.out.println(sql);
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    boolean registrarAlumno(String matriculos, String carrera, JLabel error) {
        String sql;
        ResultSet rs = null;
        PreparedStatement pst = null;
        if (isAlumnoExist(matriculos)) {
            error.setVisible(true);
            error.setText("Ya se encuentra un Alumno con esa clave");
            return false;
        } else {
            sql = "INSERT INTO  alumnos  VALUES (?,?)";
            System.out.println(sql);
            try {
                pst = cn.prepareStatement(sql);
                pst.setString(1, matriculos);
                pst.setString(2, carrera);
                pst.executeUpdate();
                error.setVisible(true);
                error.setText("Se registro el Alumno correctamente");
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
                error.setVisible(true);
                error.setText("No es posible registrar el Alumno");
                return false;
            }

        }

    }

    public boolean isProfesorExist(String id) {
        String sql;
        ResultSet rs = null;
        Statement st = null;
        sql = "SELECT * FROM profesores where  num_Trabajo = \"" + id + "\"";
        System.out.println(sql);
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    boolean registarProfesor(String matriculos, String estudio, JLabel error) {
        String sql;
        ResultSet rs = null;
        PreparedStatement pst = null;
        if (isProfesorExist(matriculos)) {
            error.setVisible(true);
            error.setText("Ya se encuentra un Profesor con esa clave");
            return false;
        } else {
            sql = "INSERT INTO  profesores  VALUES (?,?)";
            System.out.println(sql);
            try {
                pst = cn.prepareStatement(sql);
                pst.setString(1, matriculos);
                pst.setString(2, estudio);
                pst.executeUpdate();
                error.setVisible(true);
                error.setText("Se registro el Profesor correctamente");
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
                error.setVisible(true);
                error.setText("No es posible registrar el Profesor");
                return false;
            }

        }

    }

    List<Profesor> getInfoProfesor() {
        List<Profesor> maestro = new ArrayList<>();
        String sql;
        ResultSet rs = null;
        Statement st = null;
        sql = "SELECT * FROM profesores";
        System.out.println(sql);
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                maestro.add(new Profesor(rs.getString("num_Trabajo")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);

        }
        return maestro;
    }

    List<Materias> getNombreMateria() {
        List<Materias> materias = new ArrayList<>();
        String sql;
        ResultSet rs = null;
        Statement st = null;
        sql = "SELECT * FROM materias";
        System.out.println(sql);
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                materias.add(new Materias(rs.getString("id"), rs.getString("abreviatura"), rs.getString("nombre"), rs.getString("area"), rs.getString("creditos"), rs.getString("unidades")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);

        }
        return materias;
    }

    String getMateria(String id) {
        String sql;
        ResultSet rs = null;
        Statement st = null;
        sql = "SELECT nombre FROM materias WHERE id=\"" + id + "\"";
        System.out.println(sql);
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                return rs.getString("nombre");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);

        }
        return "Error";
    }

    void registratGrupo(String materia, String profesor, String year, String epoca, JLabel error) {
        String sql;
        ResultSet rs = null;
        PreparedStatement pst = null;

        sql = "INSERT INTO grupos(materia, profesor, year, epoca)  VALUES (?,?,?,?)";
        System.out.println(sql);
        try {
            pst = cn.prepareStatement(sql);
            pst.setString(1, materia);
            pst.setString(2, profesor);
            pst.setString(3, year);
            pst.setString(4, epoca);
            pst.executeUpdate();
            error.setVisible(true);
            error.setText("Se registro el grupo correctamente");

        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            error.setVisible(true);
            error.setText("No es posible registrar el grupo");

        }

    }

    String getProfesorGrupo(String idGrupo) {

        String sql = "SELECT profesor FROM grupos WHERE id=" + idGrupo;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println("lslsm");
                return rs.getString("profesor");

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return "Error";
    }

    DefaultTableModel getGrupos() {
        String[] titulos = {"Id", "Grupo", "Materia", "Profesor"};
        DefaultTableModel modelTabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        String[] registros = new String[5];
        String sql = "SELECT grupos.id,grupos.materia,grupos.year,grupos.epoca, "
                + "concat(profesores.estudio,'.', personas.nombre,' ',personas.paterno,' ',personas.materno) AS nombre "
                + "FROM (grupos INNER JOIN personas ON grupos.profesor=personas.id "
                + "INNER JOIN profesores ON grupos.profesor=profesores.num_Trabajo) WHERE 1";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String grupo, year;
            while (rs.next()) {
                String numero = "";
                char[] zero = {'0', '0', '0', '0'};

                char[] num = rs.getString("id").toCharArray();
                int j = num.length - 1;
                for (int i = 3; i >= 0; i--) {
                    zero[i] = num[j];
                    if (j == 0) {
                        break;
                    }
                    j--;
                }
                for (int i = 0; i < zero.length; i++) {
                    numero += zero[i];
                }
                year = rs.getString("year");
                grupo = "G" + year.toCharArray()[3] + year.toCharArray()[2] + "0" + rs.getString("epoca") + "-" + numero;
                registros[0] = rs.getString("id");
                registros[1] = grupo;
                registros[2] = rs.getString("materia");
                // Profesor profe = new Profesor(rs.getString("profesor"));
                registros[3] = rs.getString("nombre");

                modelTabla.addRow(registros);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return modelTabla;
    }

    void registarAlumnoEnGrupo(String alumno, String grupo, JLabel error) {
        String sql;
        ResultSet rs = null;
        PreparedStatement pst = null;

        sql = "INSERT INTO grupos_alumnos(alumno,grupo)  VALUES (?,?)";
        System.out.println(sql);
        try {
            pst = cn.prepareStatement(sql);
            pst.setString(1, alumno);
            pst.setString(2, grupo);

            pst.executeUpdate();
            error.setVisible(true);
            error.setText("Se inscribio el alumno al grupo");

        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            error.setVisible(true);
            error.setText("No es posible  inscribir al alumno al grupo");

        }

    }

    DefaultTableModel getGruposAlumnos(String idGrupo) {
        String[] titulos = {"Folio", "Alumno"};
        DefaultTableModel modelTabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        String[] registros = new String[5];
        String sql = "SELECT * FROM grupos_alumnos where grupo= '" + idGrupo + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String grupo, year;
            while (rs.next()) {
                registros[0] = rs.getString("folio");
                registros[1] = rs.getString("alumno");
                modelTabla.addRow(registros);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return modelTabla;
    }

    void actualizarGrupo(String id, String materia, String profesor, JLabel error) {
        String sql;
        ResultSet rs = null;
        PreparedStatement pst = null;

        sql = "UPDATE grupos SET materia=?,profesor=? WHERE id =?";
        System.out.println(sql);
        try {
            pst = cn.prepareStatement(sql);
            pst.setString(1, materia);
            pst.setString(2, profesor);
            pst.setString(3, id);

            pst.executeUpdate();
            error.setVisible(true);

            error.setText("Se actualizo la informacion del grupo");

        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            error.setVisible(true);
            error.setText("No es posible actualizar la informacion del grupo. Revisa que los datos sean validos");

        }
    }
 void eliminarCalGrupo(String grupo,String alumno) {
        String sql;
        ResultSet rs = null;
        PreparedStatement pst = null;

        sql = "DELETE FROM calificaciones WHERE grupo= "+grupo+" AND alumno= \""+alumno+"\"";
        System.out.println(sql);
        try {
            pst = cn.prepareStatement(sql);

            pst.executeUpdate();
         

        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);


        }
    }
    void eliminarGrupo(String id, JLabel error) {
        String sql;
        ResultSet rs = null;
        PreparedStatement pst = null;

        sql = "DELETE FROM grupos WHERE id=" + id;
        System.out.println(sql);
        try {
            pst = cn.prepareStatement(sql);

            pst.executeUpdate();
            error.setVisible(true);
            error.setText("Se elimino el grupo exitosamente");

        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            error.setVisible(true);
            error.setText("No es posible realizar esa peticion al servidor");

        }
    }

    void eliminarAlumnoDeGrupo(String id, String grupo, JLabel error) {
        String sql;
        ResultSet rs = null;
        PreparedStatement pst = null;

        sql = "DELETE FROM grupos_alumnos WHERE folio=" + id;
        System.out.println(sql);
        try {
            pst = cn.prepareStatement(sql);

            pst.executeUpdate();
            error.setVisible(true);
            error.setText("Se elimino al alumno exitosamente");

        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            error.setVisible(true);
            error.setText("Ocurrio un error, no es posible completar la peticion. Intente nuevamente en otro momento");

        }
    }

    String[] buscarAlumno(String id) {
        String[] arr = new String[2];
        String sql = "Select  CONCAT(personas.nombre, ' ', personas.paterno,' ',personas.materno) As nombre, carreras.Nombre AS carrera "
                + "From( (alumnos INNER JOIN personas ON alumnos.matricula=personas.id)INNER JOIN carreras ON alumnos.carrera=carreras.id) "
                + "WHERE alumnos.matricula = \"" + id + "\"";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                arr[0] = rs.getString("carrera");
                arr[1] = rs.getString("nombre");
                return arr;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return arr;
    }

    boolean agruegarAlumnoGrupo(String idAlumno, String idGrupo, JLabel error) {
        String sql;
        ResultSet rs = null;
        PreparedStatement pst = null;

        sql = "INSERT INTO grupos_alumnos( alumno, grupo) VALUES (?,?)";
        System.out.println(sql);
        try {
            pst = cn.prepareStatement(sql);
            pst.setString(1, idAlumno);
            pst.setString(2, idGrupo);
            pst.executeUpdate();
            error.setVisible(true);
            error.setText("Se agruego al alumno: " + idAlumno + " al grupo " + idGrupo + " exitosamente");

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            error.setVisible(true);
            error.setText("Ocurrio un error, no es posible completar la peticion. Intente nuevamente en otro momento");

        }
        return false;
    }

    DefaultTableModel getAlumnos() {
        String[] titulos = {"Matricula", "Carrera", "Nombre", "Apellido Paterno", "Apellido Materno", "Fecha de nacimiento", "Sexo", "Estado civil", "Estado", "Municipio", "Localidad", "Calle", "No. Calle", "CP"};
        DefaultTableModel modelTabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        String[] registros = new String[15];
        String sql = "SELECT * FROM alumnos INNER JOIN personas ON personas.id=alumnos.matricula INNER JOIN direcciones ON direcciones.id=personas.direccion WHERE 1 ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String grupo, year;
            while (rs.next()) {
                registros[0] = rs.getString("matricula");
                registros[2] = rs.getString("nombre");
                registros[3] = rs.getString("paterno");
                registros[4] = rs.getString("materno");
                registros[5] = rs.getString("fecha_nac");
                registros[1] = rs.getString("carrera");
                registros[6] = rs.getString("sexo");
                registros[7] = rs.getString("estado_Civil");
                registros[8] = rs.getString("estado");
                registros[9] = rs.getString("municipio");
                registros[10] = rs.getString("localidad");
                registros[11] = rs.getString("calle");
                registros[12] = rs.getString("num");
                registros[13] = rs.getString("cp");

                modelTabla.addRow(registros);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return modelTabla;
    }

    DefaultTableModel getAlumnosBuscado(String donde) {
        String[] titulos = {"Matricula", "Carrera", "Nombre", "Apellido Paterno", "Apellido Materno", "Fecha de nacimiento", "Sexo", "Estado civil", "Estado", "Municipio", "Localidad", "Calle", "No. Calle", "CP"};
        DefaultTableModel modelTabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        String[] registros = new String[15];
        String sql = "SELECT * FROM alumnos INNER JOIN personas ON personas.id=alumnos.matricula INNER JOIN direcciones ON direcciones.id=personas.direccion WHERE" + donde;
        System.out.println(sql);
        try {
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);
            String grupo, year;
            while (rs.next()) {
                registros[0] = rs.getString("matricula");
                registros[2] = rs.getString("nombre");
                registros[3] = rs.getString("paterno");
                registros[4] = rs.getString("materno");
                registros[5] = rs.getString("fecha_nac");
                registros[1] = rs.getString("carrera");
                registros[6] = rs.getString("sexo");
                registros[7] = rs.getString("estado_Civil");
                registros[8] = rs.getString("estado");
                registros[9] = rs.getString("municipio");
                registros[10] = rs.getString("localidad");
                registros[11] = rs.getString("calle");
                registros[12] = rs.getString("num");
                registros[13] = rs.getString("cp");

                modelTabla.addRow(registros);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return modelTabla;
    }

    void actualizarAlumnos(String matricula, String nombre, String paterno, String materno, String sexo, String carrera, String civil, String nacimineto, String estado, String municipio, String localidad, String calle, String num, String cp, JLabel error) {
        String sql;
        ResultSet rs = null;
        PreparedStatement pst = null;

        sql = """
              UPDATE alumnos INNER JOIN personas ON personas.id=alumnos.matricula INNER JOIN direcciones ON personas.direccion=direcciones.id 
              SET alumnos.carrera=? ,personas.nombre=?,personas.paterno=?,personas.materno=?,personas.fecha_nac=?,personas.sexo=?,personas.estado_Civil=?
              ,direcciones.estado=?,direcciones.municipio=?,direcciones.localidad=?,direcciones.calle=?,direcciones.num=?,direcciones.cp=?
               WHERE personas.id=?;""";

        try {
            pst = cn.prepareStatement(sql);
            pst.setString(1, carrera);
            pst.setString(2, nombre);
            pst.setString(3, paterno);
            pst.setString(4, materno);
            pst.setString(5, nacimineto);
            pst.setString(6, sexo);
            pst.setString(7, civil);
            pst.setString(8, estado);
            pst.setString(9, municipio);
            pst.setString(10, localidad);
            pst.setString(11, calle);
            pst.setString(12, num);
            pst.setString(13, cp);
            pst.setString(14, matricula);
            pst.executeUpdate();
            ;
            error.setVisible(true);
            error.setText("Si el alumno existia fue actualizado");

        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            error.setVisible(true);
            error.setText("Ocurrio un error, no es posible completar la peticion.");

        }
    }

    void elimniarAlumno(String matricula, JLabel error) {
        String sql;
        ResultSet rs = null;
        PreparedStatement pst = null;

        sql = "DELETE personas,direcciones FROM personas INNER JOIN direcciones ON personas.direccion=direcciones.id WHERE personas.id=\"" + matricula + "\"";

        try {
            pst = cn.prepareStatement(sql);
            pst.executeUpdate();
            error.setVisible(true);
            error.setText("Si el alumno existia fue eliminado");
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            error.setVisible(true);
            error.setText("Ocurrio un error, no es posible completar la peticion");

        }
    }

    void actualizarProfesor(String matricula, String grado, String nombre, String paterno, String materno, String sexo, String civil, String nacimineto, String estado, String municipio, String localidad, String calle, String num, String cp, JLabel error) {
        String sql;
        ResultSet rs = null;
        PreparedStatement pst = null;

        sql = """
              UPDATE profesores INNER JOIN personas ON personas.id=profesores.num_Trabajo INNER JOIN direcciones ON personas.direccion=direcciones.id 
              SET profesores.estudio=? ,personas.nombre=?,personas.paterno=?,personas.materno=?,personas.fecha_nac=?,personas.sexo=?,personas.estado_Civil=?
              ,direcciones.estado=?,direcciones.municipio=?,direcciones.localidad=?,direcciones.calle=?,direcciones.num=?,direcciones.cp=?
               WHERE personas.id=?;""";

        try {
            pst = cn.prepareStatement(sql);
            pst.setString(1, grado);
            pst.setString(2, nombre);
            pst.setString(3, paterno);
            pst.setString(4, materno);
            pst.setString(5, nacimineto);
            pst.setString(6, sexo);
            pst.setString(7, civil);
            pst.setString(8, estado);
            pst.setString(9, municipio);
            pst.setString(10, localidad);
            pst.setString(11, calle);
            pst.setString(12, num);
            pst.setString(13, cp);
            pst.setString(14, matricula);
            pst.executeUpdate();
            ;
            error.setVisible(true);
            error.setText("Si el profesor existia fue actualizado");

        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            error.setVisible(true);
            error.setText("Ocurrio un error, no es posible completar la peticion.");

        }
    }

    DefaultTableModel getProfesores() {
        String[] titulos = {"No. Trabajo", "Grado", "Nombre", "Apellido Paterno", "Apellido Materno", "Fecha de nacimiento", "Sexo", "Estado civil", "Estado", "Municipio", "Localidad", "Calle", "No. Calle", "CP"};
        DefaultTableModel modelTabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        String[] registros = new String[15];
        String sql = "SELECT * FROM profesores INNER JOIN personas ON personas.id=profesores.num_Trabajo INNER JOIN direcciones ON direcciones.id=personas.direccion WHERE 1 ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String grupo, year;
            while (rs.next()) {
                registros[0] = rs.getString("num_Trabajo");
                registros[2] = rs.getString("nombre");
                registros[3] = rs.getString("paterno");
                registros[4] = rs.getString("materno");
                registros[5] = rs.getString("fecha_nac");
                registros[1] = rs.getString("estudio");
                registros[6] = rs.getString("sexo");
                registros[7] = rs.getString("estado_Civil");
                registros[8] = rs.getString("estado");
                registros[9] = rs.getString("municipio");
                registros[10] = rs.getString("localidad");
                registros[11] = rs.getString("calle");
                registros[12] = rs.getString("num");
                registros[13] = rs.getString("cp");

                modelTabla.addRow(registros);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return modelTabla;
    }

    void elimniarProfesor(String matricula, JLabel error) {
        String sql;
        ResultSet rs = null;
        PreparedStatement pst = null;

        sql = "DELETE personas,direcciones FROM personas INNER JOIN direcciones ON personas.direccion=direcciones.id WHERE personas.id=\"" + matricula + "\"";

        try {
            pst = cn.prepareStatement(sql);
            pst.executeUpdate();
            error.setVisible(true);
            error.setText("Si el profesor  existia fue eliminado");
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            error.setVisible(true);
            error.setText("Ocurrio un error, no es posible completar la peticion");

        }
    }

    DefaultTableModel getProfesoresBuscado(String donde) {
        String[] titulos = {"No. Trabajo", "Grado", "Nombre", "Apellido Paterno", "Apellido Materno", "Fecha de nacimiento", "Sexo", "Estado civil", "Estado", "Municipio", "Localidad", "Calle", "No. Calle", "CP"};
        DefaultTableModel modelTabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        String[] registros = new String[15];
        String sql = "SELECT * FROM profesores INNER JOIN personas ON personas.id=profesores.num_Trabajo INNER JOIN direcciones ON direcciones.id=personas.direccion WHERE" + donde;
        System.out.println(sql);
        try {
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);
            String grupo, year;
            while (rs.next()) {
                registros[0] = rs.getString("num_Trabajo");
                registros[2] = rs.getString("nombre");
                registros[3] = rs.getString("paterno");
                registros[4] = rs.getString("materno");
                registros[5] = rs.getString("fecha_nac");
                registros[1] = rs.getString("estudio");
                registros[6] = rs.getString("sexo");
                registros[7] = rs.getString("estado_Civil");
                registros[8] = rs.getString("estado");
                registros[9] = rs.getString("municipio");
                registros[10] = rs.getString("localidad");
                registros[11] = rs.getString("calle");
                registros[12] = rs.getString("num");
                registros[13] = rs.getString("cp");

                modelTabla.addRow(registros);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return modelTabla;
    }

    void crearCalificacion(String grupo, String alumno, String unidad) {
        String sql;
        ResultSet rs = null;
        PreparedStatement pst = null;

        sql = "INSERT INTO calificaciones values(?,?,?,?)";
        System.out.println(sql);
        try {
            pst = cn.prepareStatement(sql);
            pst.setString(1, grupo);
            pst.setString(2, alumno);
            pst.setString(3, unidad);
            pst.setDouble(4, 0);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    int getUnidadMateria(String idMateria) {
        String sql = "SELECT unidades FROM materias Where nombre =\"" + idMateria + "\"";
        System.out.println(sql);
        try {
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                return Integer.parseInt(rs.getString("unidades"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return 0;
    }

    List getCalificacionALumno(String grupo, String alumno) {
        String sql = "SELECT unidad,calificacion FROM calificaciones Where grupo =\"" + grupo + "\" AND alumno=\"" + alumno + "\" ORDER BY unidad ASC";
        System.out.println(sql);
        int[] arr = new int[2];
        List<Double> cal = new ArrayList<Double>();
        try {
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                cal.add(rs.getDouble("calificacion"));
            }
            return cal;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }

    DefaultTableModel getGrupos(String id) {
        String[] titulos = {"Id", "Grupo", "Materia", "Profesor"};
        DefaultTableModel modelTabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        String[] registros = new String[5];
        String sql = "SELECT grupos.id,grupos.materia,grupos.year,grupos.epoca, "
                + "concat(profesores.estudio,'.', personas.nombre,' ',personas.paterno,' ',personas.materno) AS nombre "
                + "FROM (grupos INNER JOIN personas ON grupos.profesor=personas.id "
                + "INNER JOIN profesores ON grupos.profesor=profesores.num_Trabajo) WHERE grupos.id=\"" + id + "\"";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String grupo, year;
            while (rs.next()) {
                String numero = "";
                char[] zero = {'0', '0', '0', '0'};

                char[] num = rs.getString("id").toCharArray();
                int j = num.length - 1;
                for (int i = 3; i >= 0; i--) {
                    zero[i] = num[j];
                    if (j == 0) {
                        break;
                    }
                    j--;
                }
                for (int i = 0; i < zero.length; i++) {
                    numero += zero[i];
                }
                year = rs.getString("year");
                grupo = "G" + year.toCharArray()[3] + year.toCharArray()[2] + "0" + rs.getString("epoca") + "-" + numero;
                registros[0] = rs.getString("id");
                registros[1] = grupo;
                registros[2] = rs.getString("materia");
                // Profesor profe = new Profesor(rs.getString("profesor"));
                registros[3] = rs.getString("nombre");

                modelTabla.addRow(registros);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return modelTabla;
    }

    void actualizarCalificacion(String grupo, String alumno, String unidad, double cal) {
        String sql;
        ResultSet rs = null;
        PreparedStatement pst = null;

        sql = "UPDATE calificaciones SET  calificacion=? WHERE grupo=? AND alumno=? AND unidad=?";
        System.out.println(sql);
        try {
            pst = cn.prepareStatement(sql);
            pst.setDouble(1, cal);
            pst.setString(2, grupo);
            pst.setString(3, alumno);
            pst.setString(4, unidad);

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    boolean actualizarAdmin(String matricula, String user, String nombre, String paterno, String materno, String sexo, String civil, String nacimineto, String estado, String municipio, String localidad, String calle, String num, String cp, String pass, JLabel error) {
        String sql;
        ResultSet rs = null;
        PreparedStatement pst = null;
        sql = """
              UPDATE administradores INNER JOIN personas ON personas.id=administradores.id JOIN direcciones ON personas.direccion=direcciones.id
               SET administradores.usuario=? ,personas.nombre=?,personas.paterno=?,personas.materno=?,personas.fecha_nac=?,personas.sexo=?,personas.estado_Civil=?
               ,direcciones.estado=?,direcciones.municipio=?,direcciones.localidad=?,direcciones.calle=?,direcciones.num=?,direcciones.cp=?,administradores.clave=?
                WHERE personas.id=?;""";
 
        System.out.println(sql);
        try {
            pst = cn.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, nombre);
            pst.setString(3, paterno);
            pst.setString(4, materno);
            pst.setString(5, nacimineto);
            pst.setString(6, sexo);
            pst.setString(7, civil);
            pst.setString(8, estado);
            pst.setString(9, municipio);
            pst.setString(10, localidad);
            pst.setString(11, calle);
            pst.setString(12, num);
            pst.setString(13, cp);
            pst.setString(14, pass);
            pst.setString(15, matricula);
            pst.executeUpdate();
            error.setVisible(true);
            error.setText("Actualizado");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            error.setVisible(true);
            error.setText("Ocurrio un error, no es posible completar la peticion.");

        }
        return false;
    }

    void eliminarAdmin(String matricula) {
        String sql;
        ResultSet rs = null;
        PreparedStatement pst = null;
        sql = "DELETE administradores,personas,direcciones FROM personas INNER JOIN direcciones ON personas.direccion=direcciones.id INNER JOIN administradores ON personas.id=administradores.id WHERE personas.id=\"" + matricula + "\"";;
        try {
            pst = cn.prepareStatement(sql);
            System.out.println(sql);
            pst.executeUpdate();
            ;

        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    String[]  getDataAdmin(String matricula) {
        String[] data = new String[16];

        String sql = "SELECT * FROM administradores INNER JOIN personas ON personas.id=administradores.id INNER JOIN direcciones ON direcciones.id=personas.direccion WHERE administradores.usuario=\"" + matricula + "\"";
        System.out.println(sql);
        List<Double> cal = new ArrayList<Double>();
        try {
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                
                data[0] = rs.getString("usuario");
                data[1] = rs.getString("clave");
                data[2] = rs.getString("nombre");
                data[3] = rs.getString("paterno");
                data[4] = rs.getString("materno");
                data[5] = rs.getString("fecha_nac");
                data[6] = rs.getString("sexo");
                data[7] = rs.getString("estado_Civil");
                data[8] = rs.getString("estado");
                data[9] = rs.getString("municipio");
                data[10] = rs.getString("localidad");
                data[11] = rs.getString("calle");
                data[12] = rs.getString("num");
                data[13] = rs.getString("cp");
                data[14] = rs.getString("id");
               
            }
return data;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
return null;
    }
}

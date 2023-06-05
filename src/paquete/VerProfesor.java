/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete;

import campoEntrada.CampoCalendar;
import campoEntrada.CampoComboBox;
import campoEntrada.CampoTxt;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author netom
 */
public class VerProfesor extends JFrame {

    Consultas consulta;
    CampoTxt nombre, apellidoM, apellidoP, telefono, municipio, localidad, calle, numero, cp, id, estudio;
    CampoComboBox sexo, estadoCivil, estado;
    CampoCalendar necimiento;
    String[] arrSexo = {"", "Hombre", "Mujer", "Otro"};
    String[] arrSexoInicil = {"", "H", "M", "O"};
    String[] arrestados = {"", "Aguascalientes", "Baja California", "Baja CaliforniaSur", "Campeche", "Chiapas", "Chihuahua", "CiudaddeMéxico", "Coahuila", "Colima", "Durango", "EstadodeMéxico", "Guanajuato", "Guerrero", "Hidalgo", "Jalisco", "Michoacán", "Morelos", "Nayarit", "NuevoLeón", "Oaxaca", "Puebla", "Querétaro", "QuintanaRoo", "SanLuisPotosí", "Sinaloa", "Sonora", "Tabasco", "Tamaulipas", "Tlaxcala", "Veracruz", "Yucatán", "Zacatecas"};
    String[] arrestadocivil = {"", "Solter(a/o)", "Casad(a/o)", "Divorsid(a/o)", "Union libre", "Es complicado"};
    String[] arrCarrera = {"", "Industrial", "Sistemas computacionales", "Gestion empresarial", "Innovacion Agricola Sustentable", "Quimica"};
    String[] arrCarreraId = {"", "L01", "L02", "L03", "L04", "L05"};
    JPanel contenedorBtn, contenedorEntradas;
    JPanel contenedorCampos;
    int camposFaltantes = 0;
    JLabel error;
    JScrollPane scroll, scrollTabla;
    JButton registrar, regresar, acutualizar, eliminar, buscar,limpiar;
    JTable tablaProfesores;

    public VerProfesor() {
        consulta = new Consultas();
        setBounds(0, 0, 1400, 800);
        setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Profesores", 0);
        titulo.setFont(new Font(Font.SANS_SERIF, 1, 24));
        add(titulo, BorderLayout.NORTH);
        contenedorCampos = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contenedorEntradas = new JPanel(new BorderLayout());
        scrollTabla = new JScrollPane();
        id = new CampoTxt("Matricula", 20);
        nombre = new CampoTxt("Nombre", 20);
        apellidoP = new CampoTxt("Apellido Paterno", 20);
        apellidoM = new CampoTxt("Apellido Materno", 20);
        sexo = new CampoComboBox("Sexo", arrSexo);
        necimiento = new CampoCalendar("Fecha de nacimiento");
        estadoCivil = new CampoComboBox("Estado civil", arrestadocivil);
        estado = new CampoComboBox("Estado", arrestados);
        municipio = new CampoTxt("Municipio", 20);
        localidad = new CampoTxt("Localidad", 20);
        calle = new CampoTxt("Calle", 20);
        numero = new CampoTxt("No. Calle", 5);
        cp = new CampoTxt("Codigo Postal", 5);
        estudio = new CampoTxt("Grado de estudio", 5);
        tablaProfesores = new JTable();
        error = new JLabel();
        regresar = new JButton("Ir a inicio");
        contenedorCampos.setPreferredSize(new Dimension(400, 600));
        contenedorCampos.add(id);
        contenedorCampos.add(estudio);
        contenedorCampos.add(nombre);
        contenedorCampos.add(apellidoP);
        contenedorCampos.add(apellidoM);
        contenedorCampos.add(sexo);
        contenedorCampos.add(necimiento);
        contenedorCampos.add(estadoCivil);
        contenedorCampos.add(estado);
        contenedorCampos.add(municipio);
        contenedorCampos.add(localidad);
        contenedorCampos.add(calle);
        contenedorCampos.add(numero);
        contenedorCampos.add(cp);
        contenedorCampos.add(error);
        contenedorBtn = new JPanel();
        acutualizar = new JButton("Actualizar");
        acutualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizar();
            }
        });
        eliminar = new JButton("Eliminar");
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar();
            }
        });
        buscar = new JButton("Buscar");
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscar();
            }
        });
        limpiar=new JButton("Limpiar campos");
        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           limpair();
            }
        });
        contenedorBtn.add(limpiar);
        contenedorBtn.add(buscar);
        contenedorBtn.add(acutualizar);
        contenedorBtn.add(eliminar);
        scroll = new JScrollPane(contenedorCampos);
        contenedorEntradas.add(scroll);
        add(contenedorEntradas, BorderLayout.WEST);
        scrollTabla.setViewportView(tablaProfesores);
        add(scrollTabla, BorderLayout.CENTER);
        add(contenedorBtn, BorderLayout.SOUTH);
        getTablaProfesor();
        tablaProfesores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                getTablaDatos();
            }
        });

    }

    void getTablaProfesor() {
        tablaProfesores.setModel(consulta.getProfesores());
    }

    void getTablaDatos() {
        int indiceCarrera = 0, indiceSexo = 0;
        String auxCarrera, auxSexo;
        id.setText(tablaProfesores.getValueAt(tablaProfesores.getSelectedRow(), 0).toString());
        estudio.setText(tablaProfesores.getValueAt(tablaProfesores.getSelectedRow(), 1).toString());

        //System.out.println(indiceCarrera);
        nombre.setText(tablaProfesores.getValueAt(tablaProfesores.getSelectedRow(), 2).toString());
        apellidoP.setText(tablaProfesores.getValueAt(tablaProfesores.getSelectedRow(), 3).toString());
        apellidoM.setText(tablaProfesores.getValueAt(tablaProfesores.getSelectedRow(), 4).toString());
        necimiento.setFecha(tablaProfesores.getValueAt(tablaProfesores.getSelectedRow(), 5).toString());
        auxSexo = tablaProfesores.getValueAt(tablaProfesores.getSelectedRow(), 6).toString();
        for (int i = 0; i < arrSexoInicil.length; i++) {
            if (arrSexoInicil[i].equals(auxSexo)) {
                indiceSexo = i;
            }
        }
        sexo.setIndice(arrSexo[indiceSexo]);
        estadoCivil.setIndice(tablaProfesores.getValueAt(tablaProfesores.getSelectedRow(), 7).toString());
        estado.setIndice(tablaProfesores.getValueAt(tablaProfesores.getSelectedRow(), 8).toString());
        municipio.setText(tablaProfesores.getValueAt(tablaProfesores.getSelectedRow(), 9).toString());
        localidad.setText(tablaProfesores.getValueAt(tablaProfesores.getSelectedRow(), 10).toString());
        calle.setText(tablaProfesores.getValueAt(tablaProfesores.getSelectedRow(), 11).toString());
        numero.setText(tablaProfesores.getValueAt(tablaProfesores.getSelectedRow(), 12).toString());
        cp.setText(tablaProfesores.getValueAt(tablaProfesores.getSelectedRow(), 13).toString());

    }

    void eliminar() {
        if (!id.isVacio()) {
            consulta.elimniarProfesor(id.getText(), error);
        } else {
            JOptionPane.showMessageDialog(this, "Rellene el campo ID");
        }
        getTablaProfesor();
    }

    void actualizar() {
        if (!id.isVacio()) {
            consulta.actualizarProfesor(id.getText(), estudio.getText(), nombre.getText(), apellidoP.getText(), apellidoM.getText(), arrSexoInicil[sexo.getIndice()], estadoCivil.getText(), necimiento.getFecha(), estado.getText(), municipio.getText(), localidad.getText(), calle.getText(), numero.getText(), cp.getText(), error);

        } else {
            JOptionPane.showMessageDialog(this, "Rellena el campo ID");
        }
        getTablaProfesor();
    }

    void buscar() {
        String sql = "";
        String campos[] = {" profesores.num_Trabajo=",
            " personas.nombre=",
            " personas.paterno=",
            " personas.materno=",
            " personas.fecha_nac=",
            " carrera=",
            " personas.sexo=",
            " personas.estado_Civil=",
            " direcciones.estado=",
            " direcciones.municipio=",
            " direcciones.localidad=",
            " direcciones.calle=",
            " direcciones.num=",
            " direcciones.cp=",
            "profesores.num_Trabajo"};
        camposFaltantes = 0;
        error.setVisible(false);
        error.setText("");
        if (!id.isVacio()) {
            camposFaltantes++;
            sql += campos[0] + "\"" + id.getText() + "\"" + "AND";
        }
        if (!nombre.isVacio()) {
            camposFaltantes++;
            sql += campos[1] + "\"" + nombre.getText() + "\"" + "AND";
        }
        if (!apellidoP.isVacio()) {
            camposFaltantes++;
            sql += campos[2] + "\"" + apellidoP.getText() + "\"" + "AND";
        }
        if (!apellidoM.isVacio()) {
            camposFaltantes++;
            sql += campos[3] + "\"" + apellidoM.getText() + "\"" + "AND";
        }
        if (!sexo.isVacio()) {
            camposFaltantes++;
            sql += campos[6] + "\"" + arrSexoInicil[sexo.getIndice()] + "\"" + "AND";
        }

        if (!estadoCivil.isVacio()) {
            camposFaltantes++;
            sql += campos[7] + "\"" + arrestadocivil[estadoCivil.getIndice()] + "\"" + "AND";
        }
        if (!estado.isVacio()) {
            camposFaltantes++;
            sql += campos[8] + " \"" + estado.getText() + "\"" + "AND";
        }
        if (!municipio.isVacio()) {
            camposFaltantes++;
            sql += campos[9] + "\"" + municipio.getText() + "\"" + "AND";
        }
        if (!localidad.isVacio()) {
            camposFaltantes++;
            sql += campos[10] + "\"" + localidad.getText() + "\"" + "AND";
        }
        if (!calle.isVacio()) {
            camposFaltantes++;
            sql += campos[11] + "\"" + calle.getText() + "\"" + "AND";
        }
        if (!cp.isVacio()) {
            camposFaltantes++;
            sql += campos[13] + "\"" + cp.getText() + "\"" + "AND";
        }
        if (!estudio.isVacio()) {
            camposFaltantes++;
            sql += campos[13] + "\"" + cp.getText() + "\"" + "AND";
        }
        if(!sql.equals("")){
               sql = sql.substring(0, sql.length() - 3);
        tablaProfesores.setModel(consulta.getProfesoresBuscado(sql)); 
        }
    
        if (sql.equals("")) {
            getTablaProfesor();
        }
    }

    void limpair() {
        id .setText("");
        nombre.setText("");
        apellidoP.setText("");
        apellidoM .setText("");
        sexo.setIndice("");
        necimiento.setFecha("2003-01-01");
        estadoCivil.setIndice("");
        estado.setIndice("");
        municipio .setText("");
        localidad.setText("");
        calle .setText("");
        numero.setText("");
        cp .setText("");
        estudio .setText("");
    }
}

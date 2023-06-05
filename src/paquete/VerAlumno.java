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
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class VerAlumno extends JFrame {

    Consultas consulta;
    CampoTxt nombre, apellidoM, apellidoP, telefono, municipio, localidad, calle, numero, cp, id;
    CampoComboBox sexo, estadoCivil, estado, carrera;
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
    JButton registrar, regresar, acutualizar, eliminar, buscar;
    JTable tablaAlumnos;

    public VerAlumno() {
        consulta = new Consultas();
        setBounds(0, 0, 1400, 800);
        setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Alumnos", 0);
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
        carrera = new CampoComboBox("Carrera", arrCarrera);
        necimiento = new CampoCalendar("Fecha de nacimiento");
        estadoCivil = new CampoComboBox("Estado civil", arrestadocivil);
        estado = new CampoComboBox("Estado", arrestados);
        municipio = new CampoTxt("Municipio", 20);
        localidad = new CampoTxt("Localidad", 20);
        calle = new CampoTxt("Calle", 20);
        numero = new CampoTxt("No. Calle", 5);
        cp = new CampoTxt("Codigo Postal", 5);
        tablaAlumnos = new JTable();
        error = new JLabel();
        regresar = new JButton("Ir a inicio");
        contenedorCampos.setPreferredSize(new Dimension(400, 600));
        contenedorCampos.add(id);
        contenedorCampos.add(carrera);
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
        contenedorBtn.add(buscar);
        contenedorBtn.add(acutualizar);
        contenedorBtn.add(eliminar);
        scroll = new JScrollPane(contenedorCampos);
        contenedorEntradas.add(scroll);
        add(contenedorEntradas, BorderLayout.WEST);
        scrollTabla.setViewportView(tablaAlumnos);
        add(scrollTabla, BorderLayout.CENTER);
        add(contenedorBtn, BorderLayout.SOUTH);
        getTablaAlumnos();
        tablaAlumnos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                getTablaDatos();
            }
        });

    }

    void getTablaAlumnos() {
        tablaAlumnos.setModel(consulta.getAlumnos());
    }

    void getTablaDatos() {
        int indiceCarrera = 0, indiceSexo = 0;
        String auxCarrera, auxSexo;
        id.setText(tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 0).toString());
        auxCarrera = tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 1).toString();
        for (int i = 0; i < arrCarreraId.length; i++) {
            if (arrCarreraId[i].equals(auxCarrera)) {
                indiceCarrera = i;
                System.out.println("aqui");
            }
            System.out.println(arrCarreraId[i] + " " + auxCarrera);
        }
        //System.out.println(indiceCarrera);
        carrera.setIndice(arrCarrera[indiceCarrera]);
        nombre.setText(tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 2).toString());
        apellidoP.setText(tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 3).toString());
        apellidoM.setText(tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 4).toString());
        necimiento.setFecha(tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 5).toString());
        auxSexo = tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 6).toString();
        for (int i = 0; i < arrSexoInicil.length; i++) {
            if (arrSexoInicil[i].equals(auxSexo)) {
                indiceSexo = i;
            }
        }
        sexo.setIndice(arrSexo[indiceSexo]);
        estadoCivil.setIndice(tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 7).toString());
        estado.setIndice(tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 8).toString());
        municipio.setText(tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 9).toString());
        localidad.setText(tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 10).toString());
        calle.setText(tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 11).toString());
        numero.setText(tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 12).toString());
        cp.setText(tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 13).toString());

    }

    void eliminar() {
        if (!id.isVacio()) {
            consulta.elimniarAlumno(id.getText(), error);
        } else {
            JOptionPane.showMessageDialog(this, "Rellene el campo ID");
        }
        getTablaAlumnos();
    }

    void actualizar() {
        if (!id.isVacio()) {
            consulta.actualizarAlumnos(id.getText(), nombre.getText(), apellidoP.getText(), apellidoM.getText(), arrSexoInicil[sexo.getIndice()], arrCarreraId[carrera.getIndice()], estadoCivil.getText(), necimiento.getFecha(), estado.getText(), municipio.getText(), localidad.getText(), calle.getText(), numero.getText(), cp.getText(), error);

        } else {
            JOptionPane.showMessageDialog(this, "Rellene el campo ID");
        }
        getTablaAlumnos();
    }

    void buscar() {
        String sql = "";
        String campos[] = {" alumnos.matricula=",
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
            " direcciones.cp=",};
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
        if (!carrera.isVacio()) {
            camposFaltantes++;
            sql += campos[5] + "\"" + arrCarreraId[carrera.getIndice()] + "\"" + "AND";
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
        sql = sql.substring(0, sql.length() - 3);
        tablaAlumnos.setModel(consulta.getAlumnosBuscado(sql));
        if (sql.equals("")) {
            getTablaAlumnos();
        }
    }
}

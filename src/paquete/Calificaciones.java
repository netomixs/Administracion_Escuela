package paquete;

import campoEntrada.CampoCalendar;
import campoEntrada.CampoComboBox;
import campoEntrada.CampoJLabel;
import campoEntrada.CampoTxt;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import objetosTabla.Materias;

public class Calificaciones extends JFrame {

    CampoTxt[] campoCal;
    Consultas consulta;
    JPanel cebezal, centro, panelGrupos, panelAlumnos, contenedorBoxGrupos;
    JTable tablaGrupos, tablaAlumnos;
    CampoJLabel nombre, carrera, profesor;
    DefaultTableModel modelGrupos;
    JScrollPane scrollGrupo, scrollAlumnos;
    CampoTxt alumno, grupo;

    JButton buscar, cargarGrupo, actualizarCali;
    JLabel error;
    String strGrupo = "";
    List<Double> listCal;

    public Calificaciones() {

        consulta = new Consultas();
        setBounds(100, 0, 1000, 800);
        setLayout(new BorderLayout());
        centro = new JPanel(new GridLayout(1, 2));
        profesor = new CampoJLabel("Profesor ID", "");

        error = new JLabel();
        error.setVisible(false);
        nombre = new CampoJLabel("Nombre:", "");
        carrera = new CampoJLabel("Carrera:", "");
        grupo = new CampoTxt("Grupo", 12);
        JPanel panelAlumno = new JPanel(new GridLayout(5, 1));
        //    JPanel panelBotones = new JPanel(new GridLayout(2, 1, 0, 10));
        // panelBotones.setPreferredSize(new Dimension(200, 200));
        JPanel panelBoxGrupo = new JPanel();
        panelBoxGrupo.setLayout(new GridLayout(3, 1));
        //  panelBoxGrupo.setLayout(new FlowLayout());
        JPanel panelBtnBuscar = new JPanel();
        JPanel panelBtnAgregar = new JPanel(new FlowLayout());
        JPanel panelBtnBActualizar = new JPanel(new FlowLayout());
        panelBtnBActualizar.setPreferredSize(new Dimension(120, 200));
        contenedorBoxGrupos = new JPanel(new BorderLayout());

        tablaAlumnos = new JTable();
        tablaGrupos = new JTable();
        cebezal = new JPanel(new BorderLayout());
        panelGrupos = new JPanel(new BorderLayout());
        panelAlumnos = new JPanel(new FlowLayout());
        scrollAlumnos = new JScrollPane();
        scrollGrupo = new JScrollPane();

        scrollGrupo.setViewportView(tablaGrupos);
        panelAlumnos.add(scrollAlumnos);
        panelGrupos.add(scrollGrupo, BorderLayout.CENTER);

        centro.add(panelGrupos);
        centro.add(panelAlumnos);
        alumno = new CampoTxt(" Matricula de Alumno", 12);

        buscar = new JButton("Buscar");

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarAlumno();
            }
        });
        actualizarCali = new JButton("Actualizar calificaciones");
        actualizarCali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCal();
            }
        });
        //panelBotones.add(agregarAlumno);
        //panelBotones.add(eliminarAlumnoGrupo);
        //panelBotones.add(actualizarGrupo);
        // panelBotones.add(eliminarGrupo);
        panelAlumno.add(alumno);
        panelAlumno.add(nombre);
        panelAlumno.add(carrera);
        panelBtnBuscar.add(buscar);
        panelBtnBuscar.add(actualizarCali);
        panelAlumno.add(panelBtnBuscar);
        panelBoxGrupo.add(profesor);
        panelBoxGrupo.add(grupo);
        cargarGrupo = new JButton("Buscar grupo");
        cargarGrupo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarGrupo(grupo.getText());
            }
        });

        panelBtnBActualizar.add(cargarGrupo);
        //panelBoxGrupo.add(new JPanel( new FlowLayout()).add(cargarGrupo).setPreferredSize(new Dimension(1,1)));
        //panelBoxGrupo.add(panelBtnBActualizar);
        contenedorBoxGrupos.add(panelBoxGrupo, BorderLayout.CENTER);
        contenedorBoxGrupos.add(panelBtnBActualizar, BorderLayout.EAST);
        //   cebezal.add(panelBotones, BorderLayout.EAST);
        cebezal.add(contenedorBoxGrupos, BorderLayout.WEST);
        cebezal.add(panelAlumno, BorderLayout.EAST);
        add(cebezal, BorderLayout.NORTH);

        add(centro, BorderLayout.CENTER);
        add(error, BorderLayout.SOUTH);
        actualizarDatos();
        tablaGrupos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                getDatosTablaGrupos();
            }
        });
    }

    void actualizarDatos() {
        modelGrupos = consulta.getGrupos();

        tablaGrupos.setModel(modelGrupos);
        tablaGrupos.getColumnModel().getColumn(0).setPreferredWidth(5);
        tablaGrupos.getColumnModel().getColumn(1).setPreferredWidth(40);
        tablaGrupos.getColumnModel().getColumn(2).setPreferredWidth(40);
        tablaGrupos.getColumnModel().getColumn(3).setPreferredWidth(150);
    }
    String idGrupo = "", idGrupoNombre = "";
    String idMateria;

    void getDatosTablaGrupos() {
        profesor.setEnabled(true);
        grupo.setText(tablaGrupos.getValueAt(tablaGrupos.getSelectedRow(), 1).toString());
        String idProfesor = consulta.getProfesorGrupo(tablaGrupos.getValueAt(tablaGrupos.getSelectedRow(), 0).toString());
        idMateria = consulta.getMateria(tablaGrupos.getValueAt(tablaGrupos.getSelectedRow(), 2).toString());
        profesor.setText(idProfesor);

        idGrupo = tablaGrupos.getValueAt(tablaGrupos.getSelectedRow(), 0).toString();
        idGrupoNombre = tablaGrupos.getValueAt(tablaGrupos.getSelectedRow(), 0).toString();

    }
    String folio = "";

    void buscarAlumno() {
        String[] arr = consulta.buscarAlumno(alumno.getText());
        carrera.setText(arr[0]);
        nombre.setText(arr[1]);
        actualizarDatos();
        cargarCalificaciones();
    }

    void cargarCalificaciones() {

        listCal = consulta.getCalificacionALumno(idGrupo, alumno.getText());
        panelAlumnos.removeAll();
        panelAlumnos.updateUI();
        campoCal=new CampoTxt[listCal.size()];
        if (listCal != null) {
            campoCal = new CampoTxt[listCal.size()];
            for (int i = 0; i < campoCal.length; i++) {
                campoCal[i] = new CampoTxt("Tema " + (i + 1), 5);
                campoCal[i].setText(listCal.get(i) + "");
                panelAlumnos.add(campoCal[i]);
                panelAlumnos.updateUI();

            }
        } 

    }

    void buscarGrupo(String a) {
        if (a.length() > 3) {
            a = a.substring(a.length() - 2, a.length());
            tablaGrupos.setModel(consulta.getGrupos(a));
        }
    }

    void actualizarCal() {
        String sql = "";
        for (int i = 0; i < campoCal.length; i++) {
            consulta.actualizarCalificacion(idGrupo, alumno.getText(), (i + 1) + "", Double.parseDouble(campoCal[i].getText()));
        }

    }
}

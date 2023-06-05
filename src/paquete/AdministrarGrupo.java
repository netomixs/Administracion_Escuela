package paquete;

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

public class AdministrarGrupo extends JFrame {

    Consultas consulta;
    JPanel cebezal, centro, panelGrupos, panelAlumnos, contenedorBoxGrupos;
    JTable tablaGrupos, tablaAlumnos;
    CampoJLabel nombre, carrera, grupo;
    DefaultTableModel modelGrupos, modelAlumnos;
    JScrollPane scrollGrupo, scrollAlumnos;
    CampoTxt profesor, alumno;
    CampoComboBox materia;
    String[] arrMaterias;
    List<Materias> listMaterias;
    JButton eliminarGrupo, actualizarGrupo;
    JButton eliminarAlumnoGrupo, agregarAlumno, buscar;
    JLabel error;
    String strGrupo = "";

    public AdministrarGrupo() {

        consulta = new Consultas();
        setBounds(100, 0, 1000, 800);
        setLayout(new BorderLayout());
        centro = new JPanel(new GridLayout(1, 2));
        profesor = new CampoTxt("Profesor ID", 15);
        listMaterias = consulta.getNombreMateria();
        arrMaterias = new String[listMaterias.size()];

        for (int i = 0; i < arrMaterias.length; i++) {
            arrMaterias[i] = listMaterias.get(i).getNombre();
        }
        error = new JLabel();
        error.setVisible(false);
        nombre = new CampoJLabel("Nombre:", "");
        carrera = new CampoJLabel("Carrera:", "");
        grupo = new CampoJLabel("Grupo", "");
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
        materia = new CampoComboBox("Materia", arrMaterias);
        tablaAlumnos = new JTable();
        tablaGrupos = new JTable();
        cebezal = new JPanel(new BorderLayout());
        panelGrupos = new JPanel(new BorderLayout());
        panelAlumnos = new JPanel();
        scrollAlumnos = new JScrollPane();
        scrollGrupo = new JScrollPane();
        scrollAlumnos.setViewportView(tablaAlumnos);
        scrollGrupo.setViewportView(tablaGrupos);
        panelAlumnos.add(scrollAlumnos);
        panelGrupos.add(scrollGrupo, BorderLayout.CENTER);

        centro.add(panelGrupos);
        centro.add(panelAlumnos);
        alumno = new CampoTxt(" Matricula de Alumno", 12);

        eliminarGrupo = new JButton("Eliminar Grupo");

        actualizarGrupo = new JButton("Actualizar");

        eliminarAlumnoGrupo = new JButton("Eliminar alumno");

        agregarAlumno = new JButton("Agregar alumno");
        buscar = new JButton("Buscar");
        eliminarGrupo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarGrupo();
            }
        });
        actualizarGrupo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarGrupo();
            }
        });
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarAlumno();
            }
        });
        agregarAlumno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarAlumno();
            }
        });
        eliminarAlumnoGrupo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarAlumno();
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
        panelBtnAgregar.add(agregarAlumno);
        panelBtnAgregar.add(eliminarAlumnoGrupo);
        panelAlumno.add(panelBtnBuscar);
        panelAlumno.add(panelBtnAgregar);

        panelBtnBActualizar.add(actualizarGrupo);
        panelBtnBActualizar.add(eliminarGrupo);
        panelBoxGrupo.add(profesor);
        panelBoxGrupo.add(grupo);
        panelBoxGrupo.add(materia);

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
        tablaAlumnos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                getDatosTablaAlumnos();
            }
        });
    }

    void actualizarDatos() {
        modelGrupos = consulta.getGrupos();
        modelAlumnos = consulta.getGruposAlumnos(idGrupo);
        tablaAlumnos.setModel(modelAlumnos);
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
        materia.setIndice(idMateria);
        idGrupo = tablaGrupos.getValueAt(tablaGrupos.getSelectedRow(), 0).toString();
        idGrupoNombre = tablaGrupos.getValueAt(tablaGrupos.getSelectedRow(), 0).toString();
        modelAlumnos = consulta.getGruposAlumnos(tablaGrupos.getValueAt(tablaGrupos.getSelectedRow(), 0).toString());
        tablaAlumnos.setModel(modelAlumnos);

    }
    String folio = "";

    void getDatosTablaAlumnos() {
        folio = tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 0).toString();
        String[] arr = consulta.buscarAlumno(tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 1).toString());
        carrera.setText(arr[0]);
        nombre.setText(arr[1]);
        alumno.setText(tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 1).toString());

    }

    void actualizarGrupo() {
        if (!profesor.getText().equals("")) {
            String idProfesor = profesor.getText();
            String idMateria = listMaterias.get(this.materia.getIndice()).getId();
            consulta.actualizarGrupo(tablaGrupos.getValueAt(tablaGrupos.getSelectedRow(), 0).toString(), idMateria, idProfesor, error);
        }
        actualizarDatos();
    }

    void eliminarGrupo() {
        if (!profesor.getText().equals("")) {

            consulta.eliminarGrupo(tablaGrupos.getValueAt(tablaGrupos.getSelectedRow(), 0).toString(), error);
        }
        actualizarDatos();
    }

    void eliminarAlumno() {
        String id = alumno.getText();
        if (!id.equals("") && !idGrupo.equals("")) {

            int op;
            op = JOptionPane.showConfirmDialog(this, "Se eliminara el alumno " + nombre.getText() + " \ndel grupo " + grupo.getText());
            if (op == 0) {
                consulta.eliminarAlumnoDeGrupo(folio, idGrupo, error);
               consulta.eliminarCalGrupo(idGrupo,id);
            } else {

            }
        }
        actualizarDatos();
    }

    void buscarAlumno() {
        String[] arr = consulta.buscarAlumno(alumno.getText());
        carrera.setText(arr[0]);
        nombre.setText(arr[1]);
        actualizarDatos();
    }

    void agregarAlumno() {
        int materiasInt;
        String id = null, grupo = null;
        if (!idGrupo.equals("")) {
            id = alumno.getText();
            grupo = this.grupo.getText();
        }

        if (grupo != null && id != null && !id.equals("")) {

            int op;
            op = JOptionPane.showConfirmDialog(this, "Se agregara el alumno " + nombre.getText() + " \nal grupo " + grupo);
            if (op == 0) {
                consulta.agruegarAlumnoGrupo(id, idGrupo, error);
                materiasInt = consulta.getUnidadMateria(idMateria);
                for (int i = 1; i <=materiasInt; i++) {
                    consulta.crearCalificacion(idGrupo, alumno.getText(),i+"");
                }
                

            } else {

            }
        }
        actualizarDatos();
    }
}

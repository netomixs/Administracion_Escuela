    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete;

import campoEntrada.CampoCalendar;
import campoEntrada.CampoComboBox;
import campoEntrada.CampoComboBuscador;
import campoEntrada.CampoJYearSelect;
import campoEntrada.CampoTxt;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import objetosTabla.Materias;
import objetosTabla.Profesor;

/**
 *
 * @author netom
 */
public class RegistroGrupo extends JFrame {

    Consultas consulta;
    String[] arrPeriodo = {"Enero-Junio", "Verano", "Agosto-Diciembre"};
    String[] arrMaterias;
    List<Materias> listMaterias;
    CampoComboBox periodo, profesor;
    CampoJYearSelect year;
    CampoComboBuscador materias;
    List<Profesor> listProfesores;
    String[] arrProfesor;
    JPanel contenedorBtn;
    JPanel contenedorCampos;
    int camposFaltantes = 0;
    JLabel error;
    JScrollPane scroll;
    JButton registrar, regresar;

    public RegistroGrupo(Rectangle l) {
        consulta = new Consultas();
        setBounds(l.x, l.y - 50, 420, 400);

        setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Registro de nuevo Grupo", 0);
        titulo.setFont(new Font(Font.SANS_SERIF, 1, 24));
        add(titulo, BorderLayout.NORTH);
        contenedorCampos = new JPanel(new FlowLayout(FlowLayout.CENTER));
        listMaterias = consulta.getNombreMateria();
        arrMaterias = new String[listMaterias.size()];
        for (int i = 0; i < arrMaterias.length; i++) {
            arrMaterias[i] = listMaterias.get(i).getNombre();

        }
        listProfesores = consulta.getInfoProfesor();
        arrProfesor = new String[listProfesores.size()];
        for (int i = 0; i < arrProfesor.length; i++) {
            arrProfesor[i] = listProfesores.get(i).getNombreCompleto();
        }
        year = new CampoJYearSelect("Año", 12);
        periodo = new CampoComboBox("Periodo", arrPeriodo);
        profesor = new CampoComboBox("Profesor", arrProfesor);
        materias = new CampoComboBuscador("Materia", arrMaterias);
        materias.updateUI();

        error = new JLabel();
        regresar = new JButton("Ir a inicio");
        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresar();
            }
        });
        registrar = new JButton("Registrar datos");
        registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarCampos();
            }
        });
        error.setVisible(false);
        contenedorCampos.setPreferredSize(new Dimension(350, 280));
        contenedorCampos.add(year);
        contenedorCampos.add(periodo);
        contenedorCampos.add(profesor);
        contenedorCampos.add(materias);
        contenedorCampos.add(error);
        contenedorBtn = new JPanel();
        contenedorBtn.add(registrar);
        contenedorBtn.add(regresar);
        scroll = new JScrollPane(contenedorCampos);
        add(scroll, BorderLayout.CENTER);
        add(contenedorBtn, BorderLayout.SOUTH);
        contenedorCampos.updateUI();
    }

    void regresar() {
        this.setVisible(false);
    }

    void verificarCampos() {
        camposFaltantes = 0;
        error.setVisible(false);
        error.setText("");

        if (camposFaltantes > 0) {
            error.setVisible(true);
            error.setText("Completa todos los campos");
        } else {
            registarDatos();
        }

    }

    void registarDatos() {
        consulta.registratGrupo(listMaterias.get(materias.getIndice()).getId(), listProfesores.get(profesor.getIndice()).getNumTrabajo(), year.geyYearSelect() + "", (periodo.getIndice()+1)+"", error);
    }

}

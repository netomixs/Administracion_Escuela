/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete;

import campoEntrada.CampoTxt;
import campoEntrada.CampoComboBox;
import campoEntrada.CampoCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class RegistroAlumnos extends JFrame {

    Consultas consulta;
    CampoTxt nombre, apellidoM, apellidoP, telefono, municipio, localidad, calle, numero, cp, id;
    CampoComboBox sexo, estadoCivil, estado, carrera;
    CampoCalendar necimiento;
    String[] arrSexo = {"Hombre", "Mujer", "Otro"};
    String[] arrSexoInicil = {"H", "M", "O"};
    String[] arrestados = {"Aguascalientes", "Baja California", "Baja CaliforniaSur", "Campeche", "Chiapas", "Chihuahua", "CiudaddeMéxico", "Coahuila", "Colima", "Durango", "EstadodeMéxico", "Guanajuato", "Guerrero", "Hidalgo", "Jalisco", "Michoacán", "Morelos", "Nayarit", "NuevoLeón", "Oaxaca", "Puebla", "Querétaro", "QuintanaRoo", "SanLuisPotosí", "Sinaloa", "Sonora", "Tabasco", "Tamaulipas", "Tlaxcala", "Veracruz", "Yucatán", "Zacatecas"};
    String[] arrestadocivil = {"Solter(a/o)", "Casad(a/o)", "Divorsid(a/o)", "Union libre", "Es complicado"};
    String[] arrCarrera = {"Industrial", "Sistemas computacionales", "Gestion empresarial", "Innovacion Agricola Sustentable", "Quimica"};
    String[] arrCarreraId = {"L01", "L02", "L03", "L04", "L05"};
    JPanel contenedorBtn;
    JPanel contenedorCampos;
    int camposFaltantes = 0;
    JLabel error;
    JScrollPane scroll;
    JButton registrar, regresar,verAlumnos;

    public RegistroAlumnos(Rectangle l) {
        consulta = new Consultas();
        setBounds(l.x, l.y - 50, 420, 775);
        setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Registro de nuevo Alumno", 0);
        titulo.setFont(new Font(Font.SANS_SERIF, 1, 24));
        add(titulo, BorderLayout.NORTH);
        contenedorCampos = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
        verAlumnos=new JButton("Ver registros");
        verAlumnos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               verRgistros();
            }
        });
        error.setVisible(false);
        contenedorCampos.setPreferredSize(new Dimension(350, 600));
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
        contenedorBtn.add(registrar);
        contenedorBtn.add(regresar);
        contenedorBtn.add(verAlumnos);
        scroll = new JScrollPane(contenedorCampos);
        add(scroll, BorderLayout.CENTER);
        add(contenedorBtn, BorderLayout.SOUTH);

    }

    void regresar() {
        this.setVisible(false);
    }

    void verificarCampos() {
        camposFaltantes = 0;
        error.setVisible(false);
        error.setText("");
        if (nombre.isVacio()) {
            camposFaltantes++;
        }
        if (apellidoP.isVacio()) {
            camposFaltantes++;
        }
        if (apellidoM.isVacio()) {
            camposFaltantes++;
        }
        if (sexo.isVacio()) {
            camposFaltantes++;
        }
        if (necimiento.isVacio()) {
            camposFaltantes++;
        }
        if (estadoCivil.isVacio()) {
            camposFaltantes++;
        }
        if (estado.isVacio()) {
            camposFaltantes++;
        }
        if (municipio.isVacio()) {
            camposFaltantes++;
        }
        if (localidad.isVacio()) {
            camposFaltantes++;
        }
        if (calle.isVacio()) {
            camposFaltantes++;
        }
        if (cp.isVacio()) {
            camposFaltantes++;
        }
        if (camposFaltantes > 0) {
            error.setVisible(true);
            error.setText("Completa todos los campos");
        } else {
            registarDatos();
        }

    }

    void registarDatos() {
        String direccion, persona;

        direccion = consulta.registrarDireccion(estado.getText(), municipio.getText(), localidad.getText(), calle.getText(), numero.getText(), cp.getText());
        persona = consulta.registarPersona(id.getText(), nombre.getText(), apellidoP.getText(), apellidoM.getText(), necimiento.getFecha(), arrSexoInicil[sexo.getIndice()], arrestadocivil[estadoCivil.getIndice()], direccion, error);
        if (!persona.equals("")) {
            consulta.registrarAlumno(persona, arrCarreraId[carrera.getIndice()], error);
        }

    }
    void verRgistros(){
        this.setVisible(false);
        VerAlumno ve=new VerAlumno();
        ve.setVisible(true);
    }

}

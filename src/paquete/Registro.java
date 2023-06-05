package paquete;

import campoEntrada.CampoCalendar;
import campoEntrada.CampoComboBox;
import campoEntrada.CampoPassword;
import campoEntrada.CampoTxt;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Registro extends JFrame {

    Consultas consulta;
    CampoTxt nombre, apellidoM, apellidoP, telefono, municipio, localidad, calle, numero, cp, usuario, id;
    CampoComboBox sexo, estadoCivil, estado;
    CampoCalendar necimiento;
    CampoPassword clave, clave2;
    String[] arrSexo = {"Hombre", "Mujer", "Otro"};
    String[] arrSexoInicil = {"H", "M", "O"};
    String[] arrestados = {"Aguascalientes", "BajaCalifornia", "BajaCaliforniaSur", "Campeche", "Chiapas", "Chihuahua", "CiudaddeMéxico", "Coahuila", "Colima", "Durango", "EstadodeMéxico", "Guanajuato", "Guerrero", "Hidalgo", "Jalisco", "Michoacán", "Morelos", "Nayarit", "NuevoLeón", "Oaxaca", "Puebla", "Querétaro", "QuintanaRoo", "SanLuisPotosí", "Sinaloa", "Sonora", "Tabasco", "Tamaulipas", "Tlaxcala", "Veracruz", "Yucatán", "Zacatecas"};
    String[] arrestadocivil = {"Solter(a/o)", "Casad(a/o)", "Divorsid(a/o)", "Union libre", "Es complicado"};
    JPanel contenedorBtn;
    JPanel contenedorCampos;
    int camposFaltantes = 0;
    JLabel error;
    JScrollPane scroll;
    JButton registrar, iniciar;

    public Registro(Rectangle l) {
        consulta = new Consultas();
        setBounds(l.x, l.y - 150, 420, 700);
        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(420, 700));
        setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Registro de nuevo usuario", 0);
        titulo.setFont(new Font(Font.SANS_SERIF, 1, 24));
        add(titulo, BorderLayout.NORTH);
        contenedorCampos = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
        usuario = new CampoTxt("Usuario", 20);
        clave = new CampoPassword("Contraseña", 20);
        clave2 = new CampoPassword("Repita la contraseña", 20);
        id = new CampoTxt("Clave de trabajo", 20);
        error = new JLabel("", 0);
        registrar = new JButton("Registrar datos");
        iniciar = new JButton("Iniciar sesion");
        iniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });
        registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarCampos();
            }
        });
        error.setVisible(false);
        contenedorCampos.setPreferredSize(new Dimension(350, 750));
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
        contenedorCampos.add(usuario);
        contenedorCampos.add(clave);
        contenedorCampos.add(clave2);
        contenedorCampos.add(id);
        contenedorCampos.add(error);
        contenedorBtn = new JPanel();
        contenedorBtn.add(registrar);
        contenedorBtn.add(iniciar);
        scroll = new JScrollPane(contenedorCampos);
        add(scroll, BorderLayout.CENTER);
        add(contenedorBtn, BorderLayout.SOUTH);

    }

    void iniciarSesion() {
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
        if (usuario.isVacio()) {
            camposFaltantes++;
        }
        if (clave.isVacio()) {
            camposFaltantes++;
        }
        if (clave2.isVacio()) {
            camposFaltantes++;
        }
        if (camposFaltantes > 0) {
            error.setVisible(true);
            error.setText("Completa todos los campos");
        }
        if (clave.getText().equals(clave2.getText())) {
            if (camposFaltantes == 0) {
                registarDatos();
            }

        } else {
            error.setVisible(true);
            error.setText("La contraseña no coincide");
        }
    }

    void registarDatos() {
        String direccion, persona;
        direccion = consulta.registrarDireccion(estado.getText(), municipio.getText(), localidad.getText(), calle.getText(), numero.getText(), cp.getText());
        persona = consulta.registarPersona(id.getText(), nombre.getText(), apellidoP.getText(), apellidoM.getText(), necimiento.getFecha(), arrSexoInicil[sexo.getIndice()], estadoCivil.getText(), direccion, error);
        if (consulta.registrarAdministrador(id.getText(), usuario.getText(), clave.getText(), error)) {
            //error.setText("registro exitoso");
        }

    }

}

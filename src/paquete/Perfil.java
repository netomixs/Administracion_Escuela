package paquete;

import campoEntrada.CampoCalendar;
import campoEntrada.CampoComboBox;
import campoEntrada.CampoJLabel;
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

public class Perfil extends JFrame {

    String idAdmin,idC;
    Consultas consulta;
    CampoTxt nombre, apellidoM, apellidoP, telefono, municipio, localidad, calle, numero, cp, usuario, clave, clave2;
    CampoComboBox sexo, estadoCivil, estado;
    CampoCalendar necimiento;
    String[] arrSexo = {"Hombre", "Mujer", "Otro"};
    String[] arrSexoInicil = {"H", "M", "O"};
    String[] arrestados = {"Aguascalientes", "BajaCalifornia", "BajaCaliforniaSur", "Campeche", "Chiapas", "Chihuahua", "CiudaddeMéxico", "Coahuila", "Colima", "Durango", "EstadodeMéxico", "Guanajuato", "Guerrero", "Hidalgo", "Jalisco", "Michoacán", "Morelos", "Nayarit", "NuevoLeón", "Oaxaca", "Puebla", "Querétaro", "QuintanaRoo", "SanLuisPotosí", "Sinaloa", "Sonora", "Tabasco", "Tamaulipas", "Tlaxcala", "Veracruz", "Yucatán", "Zacatecas"};
    String[] arrestadocivil = {"Solter(a/o)", "Casad(a/o)", "Divorsid(a/o)", "Union libre", "Es complicado"};
    JPanel contenedorBtn;
    JPanel contenedorCampos;
    int camposFaltantes = 0;
    JLabel error;
    JScrollPane scroll;
    JButton actualizar, eliminarPerfil;
    CampoJLabel id;
    Menu menu;
    public Perfil(Rectangle l, String idAdmin,Menu menu) {
        this.idAdmin = idAdmin;
        this.menu=menu;
        consulta = new Consultas();
        setBounds(l.x, l.y -100, 420, 700);
        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(420, 700));
        setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Datos de perfil", 0);
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
        clave = new CampoTxt("Contraseña", 20);
        clave2 = new CampoTxt("Repita la contraseña", 20);
        id = new CampoJLabel("Clave de trabajo", "");
        error = new JLabel("", 0);
        actualizar = new JButton("Actualizar");
        eliminarPerfil = new JButton("Eliminar");
        eliminarPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminer();
            }
        });
        actualizar.addActionListener(new ActionListener() {
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
        contenedorBtn.add(actualizar);
        contenedorBtn.add(eliminarPerfil);
        scroll = new JScrollPane(contenedorCampos);
        add(scroll, BorderLayout.CENTER);
        add(contenedorBtn, BorderLayout.SOUTH);
        getDatos();
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
            error.setText("No puedes dejar campos vacioas");
        }
        if (clave.getText().equals(clave2.getText())) {
            if (camposFaltantes == 0) {
                actualizar();
            }
            
        } else {
            error.setVisible(true);
            error.setText("La contraseña no coincide");
        }
    }
    
    void actualizar() {
        if(consulta.actualizarAdmin(idC, usuario.getText(), nombre.getText(), apellidoP.getText(), apellidoM.getText(), arrSexoInicil[sexo.getIndice()], estadoCivil.getText(), necimiento.getFecha(), estado.getText(), municipio.getText(), localidad.getText(), calle.getText(), numero.getText(), cp.getText(), clave.getText(), error)
        ){
            idAdmin=usuario.getText();
            menu.idAdmin=usuario.getText();
        }
        getDatos();
    }

    void eliminer() {
        consulta.eliminarAdmin(id.getText());
        System.exit(0);
    }

    void getDatos() {
        String[] arr = consulta.getDataAdmin(idAdmin);
        nombre.setText(arr[2]);
        apellidoP.setText(arr[3]);
        apellidoM.setText(arr[4]);
        necimiento.setFecha(arr[5]);
        String auxSexo =arr[6];
        for (int i = 0; i < arrSexoInicil.length; i++) {
            if (arrSexoInicil[i].equals(auxSexo)) {
                sexo.setIndice(i);
            }
        }
       
        estadoCivil.setIndice(arr[7]);
        estado.setIndice(arr[8]);
        municipio.setText(arr[9]);
        localidad.setText(arr[10]);
        calle.setText(arr[11]);
        numero.setText(arr[12]);
        cp.setText(arr[13]);
        usuario.setText(arr[0]);
        clave.setText(arr[1]);
        id.setText(arr[14]);
            idC=id.getText();
    }
}

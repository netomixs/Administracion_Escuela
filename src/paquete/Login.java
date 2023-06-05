
package paquete;

import campoEntrada.CampoPassword;
import campoEntrada.CampoTxt;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {
    
    JLabel titulo, error;
    CampoTxt campoUsuario;
    CampoPassword campoPassword;
    JButton btnInicio, btnRegistrar;
    Consultas consulta;
    JPanel contenedorBtn,contenedorCampos;
    
    public Login() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        consulta = new Consultas();
        Font fuenteTexto = new Font(Font.SANS_SERIF, 0, 12);
        Font fuenteTitulo = new Font(Font.SANS_SERIF, 1, 24);
        setLayout(new BorderLayout());
        setBounds(500, 200, 400, 275);
        setResizable(false);
        contenedorBtn = new JPanel(new GridLayout(3, 1,0,20));
        
         contenedorCampos = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //contenedorCampos.setPreferredSize(new Dimension(100,100));
        titulo = new JLabel("Acceso a usuario", 0);
        titulo.setFont(fuenteTitulo);
        campoUsuario = new CampoTxt("Ingrese Usuario", 15);
        campoUsuario.setFuente(fuenteTexto);
        campoPassword = new CampoPassword("Ingrese Contraseña", 15);
        campoPassword.setFuente(fuenteTexto);
        btnInicio = new JButton("Ingresar");
        btnInicio.setFont(fuenteTexto);
        // btnInicio.setSize(new Dimension(100,50));
        btnRegistrar = new JButton("Registar");
        btnRegistrar.setFont(fuenteTexto);
        error = new JLabel("",0);
        error.setVisible(false);
        error.setForeground(Color.red);
        contenedorCampos.add(campoUsuario);
        contenedorCampos.add(campoPassword);
        contenedorCampos.setPreferredSize(new Dimension(400,100));
        contenedorBtn.add(error);
        contenedorBtn.add(btnInicio);
        contenedorBtn.add(btnRegistrar);
        
        add(titulo, BorderLayout.NORTH);
        add(contenedorCampos, BorderLayout.CENTER);
        add(contenedorBtn, BorderLayout.SOUTH);
        contenedorBtn.setBorder(new EmptyBorder(0, 60, 0, 60));
        btnInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarUsuario();
                
            }
        });
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               abrirRegistro();
            }
        });
        
    }
    void abrirRegistro(){
         Registro r=new Registro(this.getBounds());
         r.setVisible(true);
    }
    public void verificarUsuario() {
        String usuario, clave;
        usuario = campoUsuario.getText();
        clave = campoPassword.getText();
        if (!usuario.equals("") && !clave.equals("")) {
            if (consulta.accesoAminitrador(usuario, clave, error)) {
                Menu menu=new Menu(campoUsuario.getText());
                menu.setVisible(true);
                this.setVisible(false);
                System.out.println("skmskomsk");
            } else {
                btnRegistrar.setSelected(true);
                error.setVisible(true);
               
            }
        }
        
    }
}



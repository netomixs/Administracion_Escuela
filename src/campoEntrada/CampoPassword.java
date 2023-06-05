/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campoEntrada;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

   public class CampoPassword extends JPanel {

        public JLabel titulo;
        public JPasswordField entrada;
        public JPanel panelTitulo, panelEntrada;

        public CampoPassword(String txtTitulo, int largo) {
            setPreferredSize(new Dimension(400, 40));
            setLayout(new FlowLayout(0));
            panelTitulo = new JPanel(new FlowLayout(0));
            panelEntrada = new JPanel(new FlowLayout(FlowLayout.LEFT));
            titulo = new JLabel(txtTitulo, 2);
            panelTitulo.setPreferredSize(new Dimension(130, 30));
            entrada = new JPasswordField();
            entrada.setPreferredSize(new Dimension(largo * 10, 30));

            panelTitulo.add(titulo);
            panelEntrada.add(entrada);
            add(panelTitulo);
            add(panelEntrada);

        }

       public String getText() {
           char[] arrentrada=entrada.getPassword();
            return new String(arrentrada);
        }

        public boolean isVacio() {
            return (entrada.getPassword().length==0);
        }

        public void setText(String text) {
            entrada.setText(text);
        }

        public void setFuente(Font f) {
            titulo.setFont(f);
            entrada.setFont(f);
        }
    }

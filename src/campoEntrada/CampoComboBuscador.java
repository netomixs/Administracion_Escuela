/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campoEntrada;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

    public class CampoComboBuscador extends JPanel {

        JLabel titulo;
        JComboBox<String> entrada;
        JPanel panelTitulo, panelEntrada;

        public CampoComboBuscador(String txtTitulo, String[] arr) {
    
            setPreferredSize(new Dimension(400, 100));
            setLayout(new FlowLayout(0));
            panelTitulo = new JPanel(new FlowLayout(0));
            panelEntrada = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panelEntrada.setBackground(Color.red);
            titulo = new JLabel(txtTitulo, 2);
            entrada = new JComboBox<>(arr);
    
            panelTitulo.add(titulo);
            panelEntrada.add(entrada);
            JScrollPane scroll=new JScrollPane(entrada);
            add(panelTitulo);
            add(scroll);
            panelTitulo.setPreferredSize(new Dimension(50, 30));

        }

       public String getText() {
            return entrada.getSelectedItem().toString();
        }

     public   int getIndice() {
            return entrada.getSelectedIndex();
        }

    public    boolean isVacio() {
            return (entrada.getSelectedItem() == null);
        }

      public  void setFuente(Font f) {
            titulo.setFont(f);
            entrada.setFont(f);
        }
    }
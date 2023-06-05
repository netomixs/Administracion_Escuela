/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campoEntrada;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CampoComboBox extends JPanel {

    JLabel titulo;
    JComboBox<String> entrada;
    JPanel panelTitulo, panelEntrada;

    public CampoComboBox(String txtTitulo, String[] arr) {
        setPreferredSize(new Dimension(400, 40));
        setLayout(new FlowLayout(0));
        panelTitulo = new JPanel(new FlowLayout(0));
        panelEntrada = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titulo = new JLabel(txtTitulo, 2);
        entrada = new JComboBox<>(arr);
        panelTitulo.add(titulo);
        panelEntrada.add(entrada);
        add(panelTitulo);
        add(panelEntrada);
        panelTitulo.setPreferredSize(new Dimension(130, 30));

    }

    public String getText() {
        return entrada.getSelectedItem().toString();
    }

    public int getIndice() {
        return entrada.getSelectedIndex();
    }

    public void setIndice(String a) {
        entrada.setSelectedItem(a);
    }

    public void setIndice(int a) {
        entrada.setSelectedItem(a);
    }

    public boolean isVacio() {
        return (entrada.getSelectedItem().equals(""));
    }

    public void setFuente(Font f) {
        titulo.setFont(f);
        entrada.setFont(f);
    }
}

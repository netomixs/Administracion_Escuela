/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campoEntrada;

import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CampoCalendar extends JPanel {

    JLabel titulo;
    JPanel panelTitulo, panelEntrada;
    JDateChooser entrada;
    SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");

    public CampoCalendar(String txtTitulo) {
        setPreferredSize(new Dimension(400, 40));
        setLayout(new FlowLayout(0));
        panelTitulo = new JPanel(new FlowLayout(0));
        panelEntrada = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titulo = new JLabel(txtTitulo, 2);
        entrada = new JDateChooser();
        entrada.setDate(new Date(103, 0, 1));
        entrada.setPreferredSize(new Dimension(100, 30));
        panelTitulo.add(titulo);
        panelEntrada.add(entrada);
        panelTitulo.setPreferredSize(new Dimension(130, 30));
        add(panelTitulo);
        add(panelEntrada);

    }

    public boolean isVacio() {
        return (entrada.getDate() == null);
    }

    public String getFecha() {
        if (entrada.getDate() != null) {
            return Formato.format(entrada.getDate());
        } else {
            return null;
        }
    }

    public void setFecha(String f) {
        try {
            Date fecha = Formato.parse(f);
            entrada.setDate(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(CampoCalendar.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    public void setFuente(Font f) {
        titulo.setFont(f);
        entrada.setFont(f);
    }
}

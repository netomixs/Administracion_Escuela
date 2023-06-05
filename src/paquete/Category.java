/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author netom
 */
  public class Category extends JPanel {

        JButton btn;
        JLabel text;

        public Category(String strCategory) {
            // setBorder(BorderFactory.createEmptyBorder(50, 50, 1,1));
            btn = new JButton(new ImageIcon(getClass().getResource("/img/"+strCategory+".png")));
            btn.setPreferredSize(new Dimension(200, 200));
            text = new JLabel(strCategory, SwingConstants.CENTER);
            text.setFont(new Font("Arial", Font.PLAIN, 24));
            text.setPreferredSize(new Dimension(250, 50));
            add(btn, BorderLayout.PAGE_START);
            add(text, BorderLayout.PAGE_END);
        }
    }

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author netom
 */
public class Menu extends JFrame implements ActionListener {
String idAdmin;
    private final Category[] category;
    private final String[] categories = {"Alumnos", "Profesores", "Crear Grupos", "Administrar grupos", "Calificaciones","Perfil"};

    public Menu(String idAdmin) {
         this.idAdmin=idAdmin;
        setBounds(500, 100, 700, 600);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //u=new UserPorfile(home);
        setLayout(new GridLayout(2, 3));
        category = new Category[categories.length];
        for (int i = 0; i < categories.length; i++) {
            category[i] = new Category(categories[i]);
            add(category[i]);
            category[i].btn.addActionListener(this);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int op = 0;

        for (int i = 0; i < 6; i++) {
            if (e.getSource() == category[i].btn) {
                //home.getReloj().setContinum(true);

                op = i;
                break;
            }
        }
        switch (op) {
            case 0:
                RegistroAlumnos alumnos = new RegistroAlumnos(this.getBounds());
                alumnos.setVisible(true);
                break;
            case 1:
                RegistroProfesor profesro = new RegistroProfesor(this.getBounds());
                profesro.setVisible(true);
                break;
            case 2:
                RegistroGrupo grupo = new RegistroGrupo(this.getBounds());
                grupo.setVisible(true);

                break;
            case 3:
                AdministrarGrupo adminGrupo = new AdministrarGrupo();
                adminGrupo.setVisible(true);
                break;
            case 4:
                Calificaciones cal=new Calificaciones();
                cal.setVisible(true);
                break;
            case 5:
                Perfil p=new Perfil(this.getBounds(), idAdmin,this);
              p.setVisible(true);
                break;
            default:
                break;
        }
    }

}

package newProject.playGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exit extends JFrame implements ActionListener {
    JButton yes;
    JButton no;
    JLabel exit;
    public String check;

    public Exit() {
        this.setTitle("Escape the dungeon");
        this.setSize(768, 576);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.black);
        exit = new JLabel("", JLabel.CENTER);

        add(exit);
        this.setLocationRelativeTo(null);
        no = new JButton();
        no.setBounds(384, 238, 200, 100);
        no.setText("No");
        no.setFont(new Font("Comic Sans", Font.BOLD, 25));
        no.setForeground(Color.red);
        no.setBorderPainted(false);
        no.setContentAreaFilled(false);
        no.addActionListener(this);
        exit.add(no);

        yes = new JButton();
        yes.setBounds(184, 238, 200, 100);
        yes.setText("Yes");
        yes.setFont(new Font("Comic Sans", Font.BOLD, 25));
        yes.setForeground(Color.red);
        yes.setBorderPainted(false);
        yes.setContentAreaFilled(false);
        yes.addActionListener(this);
        exit.add(yes);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == yes) {
            if (check == "interface") {
                System.exit(0);
            } else if (check == "lever") {
                new Interface();
            } else if (check == "again"){
                new Interface();
            }else {
                new Interface();
            }
            this.setVisible(false);
        }
        if (e.getSource() == no) {
            if (check == "interface") {
                new Interface();
            } else if (check == "lever") {
                new LeverGame();
            } else if (check == "again"){
                new Again();
            }else {
                new Description();
            }
            this.setVisible(false);
        }
    }
}

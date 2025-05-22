package newProject.playGame;

import newProject.test2.Balanced;
import newProject.test2.GamePanel;
import newProject.test2.Hard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeverGame extends JFrame implements ActionListener {
    JButton hard;
    JButton medium;
    JButton exit;
    JLabel bk;
    GamePanel gamePanel;
    public LeverGame() {
        this.setTitle("Escape the dungeon");
        this.setSize(768, 576);
        this.setResizable(false);
        ImageIcon logo = new ImageIcon("src\\project\\imag");
        this.getContentPane().setBackground(Color.black);
        this.setIconImage(logo.getImage());
        ImageIcon iconBk = new ImageIcon("src\\project\\imag\\");
        bk = new JLabel("", iconBk, JLabel.CENTER);
        add(bk);
        this.setLocationRelativeTo(null);

        hard = new JButton();
        hard.setBounds(284, 100, 200, 100);
        hard.setText("HARD");
        hard.setFont(new Font("Comic Sans", Font.BOLD, 20));
        hard.setForeground(Color.red);
        hard.setBorderPainted(false);
        hard.setContentAreaFilled(false);
        hard.addActionListener(this);
        bk.add(hard);

        medium = new JButton();
        medium.setBounds(284, 200, 200, 100);
        medium.setText("MEDIUM");
        medium.setFont(new Font("Comic Sans", Font.BOLD, 20));
        medium.setForeground(Color.red);
        medium.setBorderPainted(false);
        medium.setContentAreaFilled(false);
        medium.addActionListener(this);
        bk.add(medium);


        exit = new JButton();
        exit.setBounds(284, 300, 200, 100);
        exit.setText("EXIT");
        exit.setFont(new Font("Comic Sans", Font.BOLD, 20));
        exit.setForeground(Color.red);
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        exit.addActionListener(this);
        bk.add(exit);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == hard) {
            new Hard();
            this.setVisible(false);
        }
        if (e.getSource() == medium) {
            new Balanced();
            this.setVisible(false);
        }
        if (e.getSource() == exit) {
            Exit exit1 = new Exit();
            exit1.check = "lever";
            this.setVisible(false);
        }
    }
}

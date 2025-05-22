package newProject.playGame;

import newProject.test2.Balanced;
import newProject.test2.Hard;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Again extends JFrame implements ActionListener {
    JButton exit;
    JButton again;
    JLabel gameOver;
    JLabel over;

    public int check;

    public Again() {
        this.setTitle("Escape the dungeon");
        this.setSize(768, 576);
        this.setResizable(false);
        ImageIcon end = new ImageIcon("src/res/lmao/over.png");
        this.getContentPane().setBackground(Color.black);
        over = new JLabel("", end,JLabel.CENTER);
        over.setSize(768,576);
        this.setLocationRelativeTo(null);
        gameOver = new JLabel();
        gameOver.setBounds(270, 30, 300, 200);
        gameOver.setText("GAME OVER!");
        gameOver.setFont(new Font("Comic Sans", Font.BOLD, 40));
        gameOver.setForeground(Color.red);
        over.add(gameOver);

        again = new JButton();
        again.setBounds(384, 238, 200, 100);
        again.setText("Restart");
        again.setFont(new Font("Comic Sans", Font.BOLD, 25));
        again.setForeground(Color.red);
        again.setBorderPainted(false);
        again.setContentAreaFilled(false);
        again.addActionListener(this);
        over.add(again);

        exit = new JButton();
        exit.setBounds(184, 238, 200, 100);
        exit.setText("Exit the title");
        exit.setFont(new Font("Comic Sans", Font.BOLD, 25));
        exit.setForeground(Color.red);
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        exit.addActionListener(this);
        over.add(exit);
        this.add(over);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            Exit exit1 = new Exit();
            exit1.check = "again";
            this.setVisible(false);
        }
        if (e.getSource() == again) {
            if (check == 0) {
                new Hard();
                this.setVisible(false);
            }else {
                new Balanced();
                this.setVisible(false);
            }
        }
    }
}

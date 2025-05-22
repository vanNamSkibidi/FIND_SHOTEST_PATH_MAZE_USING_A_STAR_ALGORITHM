package newProject.playGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JFrame implements ActionListener {
    JButton start;
    JButton huong;
    JButton exit;
    JLabel bk;


    public Interface() {
        this.setTitle("Escape the dungeon");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(768, 576);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.black);
        ImageIcon iconImag = new ImageIcon("src/res/lmao/startGame.png");
        bk = new JLabel("",iconImag, JLabel.CENTER);
        bk.setSize(768,576);
        add(bk);
        this.setLocationRelativeTo(null);


        start = new JButton();
        start.setBounds(512, 176, 200, 100);
        start.setText("START");
        start.setFont(new Font("Comic Sans", Font.BOLD, 20));
        start.setForeground(Color.red);
        start.setBorderPainted(false);
        start.setContentAreaFilled(false);
        start.addActionListener(this);
        bk.add(start);

        huong = new JButton();
        huong.setBounds(512, 276, 200, 100);
        huong.setText("DESCRIPTION");
        huong.setFont(new Font("Comic Sans", Font.BOLD, 20));
        huong.setForeground(Color.red);
        huong.setBorderPainted(false);
        huong.setContentAreaFilled(false);
        huong.addActionListener(this);
        bk.add(huong);

        exit = new JButton();
        exit.setBounds(512, 376, 200, 100);
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
        if (e.getSource() == start) {
            new LeverGame();
            this.setVisible(false);
        }
        if (e.getSource() == huong) {
            new Description();
            this.setVisible(false);
        }
        if (e.getSource() == exit) {
            Exit exit1 = new Exit();
            exit1.check = "interface";
        }
    }
}

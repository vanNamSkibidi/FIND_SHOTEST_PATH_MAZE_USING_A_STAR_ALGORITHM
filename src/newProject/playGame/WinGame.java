package newProject.playGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinGame extends JFrame implements ActionListener {
    JButton title1;
    JButton title2;
    JButton title3;
    JLabel win;
    JLabel meme;
    public WinGame() {
        this.setTitle("Escape the dungeon");
        this.setSize(768, 576);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.black);
        ImageIcon iconBk = new ImageIcon("src/res/lmao/happyBirthdayEndGame.png");
        meme = new JLabel(iconBk);
        win = new JLabel("", JLabel.CENTER);
        this.setLocationRelativeTo(null);
        meme.setBounds(101,170,iconBk.getIconWidth(),iconBk.getIconHeight());
        title1 = new JButton();
        title1.setBounds(284, 10, 200, 100);
        title1.setText("Congratulations!");
        title1.setFont(new Font("Comic Sans", Font.BOLD, 20));
        title1.setForeground(Color.red);
        title1.setBorderPainted(false);
        title1.setContentAreaFilled(false);
        win.add(title1);

        title3 = new JButton();
        title3.setBounds(134, 110, 500, 100);
        title3.setText("Thank you for playing our game:>");
        title3.setFont(new Font("Comic Sans", Font.BOLD, 20));
        title3.setForeground(Color.red);
        title3.setBorderPainted(false);
        title3.setContentAreaFilled(false);
        win.add(title3);

        title2 = new JButton();
        title2.setBounds(284, 210, 200, 100);
        title2.setText("exit the title");
        title2.setFont(new Font("Comic Sans", Font.BOLD, 20));
        title2.setForeground(Color.red);
        title2.setBorderPainted(false);
        title2.setContentAreaFilled(false);
        title2.addActionListener(this);
        win.add(title2);
        this.add(meme);
        this.add(win);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == title2) {
            new Interface();
            this.setVisible(false);
        }
    }
}

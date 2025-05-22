package newProject.test2;

import javax.swing.JFrame;
import java.awt.*;

public class Hard {




    public Hard() {

        JFrame window = new JFrame("Escape the dungeon");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setLayout(new BorderLayout());
        GamePanel gamePanel = new GamePanel(window,"hard");
        window.add(gamePanel, BorderLayout.CENTER);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setUpGame();
        gamePanel.startGameThread();

    }
}

package newProject.test2;

import newProject.entity.Entity;
import newProject.entity.Player;
import newProject.findPath.PathFinderUsingBfs;
import newProject.findPath.PathFinderUsingAStar ;
import newProject.playGame.Again;
import newProject.playGame.WinGame;
import newProject.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;
    int FPS = 60;
    Thread gameThread;
    public KeyHandler keyH = new KeyHandler();
    public Player player = new Player(this, keyH);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public Entity[] monsters = new Entity[3];
    public int idxMonster = 0;
    public TileManager tileManager = new TileManager(this);
    public boolean gameOver;
    public boolean winGame;
    public String DifficultLevel;
    public PathFinderUsingAStar  pathFinderUsingAStar  = new PathFinderUsingAStar (this);
    public PathFinderUsingBfs pathFinderUsingBfs = new PathFinderUsingBfs(this);
    public JFrame window;
    public int count = 0;
    public int totalSharp = 20;
    JLabel gemCountLabel = new JLabel("Remaining", SwingConstants.LEFT);
    public GamePanel(JFrame window,String lever) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.window = window;
        this.DifficultLevel = lever;
        window.add(gemCountLabel, BorderLayout.BEFORE_FIRST_LINE);
        startGameThread();
    }
    public void updateGemCount(int gemCount) {
        if (count < totalSharp) {
            gemCountLabel.setText("Remaining : " + (totalSharp - gemCount) + " sharps");
        } else gemCountLabel.setText("Run as fast as\n you can towards the gate\n");
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }


    public void setUpGame() {
        assetSetter.setMonster();
    }

    boolean check = true;

    public void update() {
        if (gameOver) {
            if (check) {
                check = false;
                window.dispose();
                Again again =new Again();
                if(DifficultLevel =="hard"){
                    again.check = 0;
                }else {
                    again.check = 1;
                }
                return;
            }
        }
        if (winGame) {
            if (check) {
                check = false;
                window.dispose();
                new WinGame();
                return;
            }
        }
        player.update();
        for (Entity monster : monsters) {
            if (monster != null) {
                monster.update();
                idxMonster++;
            }
        }
        updateGemCount(count);
        idxMonster = 0;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        player.draw(g2);

        //monster
        for (Entity monster : monsters) {
            if (monster != null) {
                monster.draw(g2, this);
            }
        }
        g2.dispose();
    }
}
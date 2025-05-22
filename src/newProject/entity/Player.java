package newProject.entity;

import newProject.test2.GamePanel;
import newProject.test2.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth / 2;
        screenY = gp.screenHeight / 2;

        solidArea = new Rectangle(12, 24, 24, 24);
        setDefaultValues();
        getPlayerImage();
        direction = "down";
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 2;
        worldY = gp.tileSize * 10;
        speed = 3;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(new File("src/res/player/up1.png"));
            up2 = ImageIO.read(new File("src/res/player/up2.png"));
            down1 = ImageIO.read(new File("src/res/player/right2.png"));
            down2 = ImageIO.read(new File("src/res/player/down2.png"));
            left1 = ImageIO.read(new File("src/res/player/left1.png"));
            left2 = ImageIO.read(new File("src/res/player/left2.png"));
            right1 = ImageIO.read(new File("src/res/player/right1.png"));
            right2 = ImageIO.read(new File("src/res/player/right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else {
                direction = "right";
            }

            collisionOn = false;
            gp.collisionChecker.checkTile(this);
            if (!collisionOn) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }

            }
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) spriteNum = 2;
                else spriteNum = 1;
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2d) {
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                } else image = up2;
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                } else image = down2;
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                } else image = left2;
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                } else image = right2;
            }
        }
        g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
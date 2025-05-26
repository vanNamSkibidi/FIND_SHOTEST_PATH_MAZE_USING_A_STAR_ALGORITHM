package newProject.entity;

import newProject.test2.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    GamePanel gp;
    public int worldX;
    public int worldY;
    public int speed;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public Rectangle solidArea = new Rectangle(12, 24, 24, 24);
    public boolean collisionOn;
    public int actionLockCounter = 0;

    public Entity(GamePanel gp) {
        this.gp = gp;
        this.direction = "down";

    }

    public int screenX, screenY;
    BufferedImage image;

    public void draw(Graphics2D g2, GamePanel gp) {
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                && worldX - gp.tileSize < gp.player.screenX + gp.player.worldX
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            screenX = worldX - gp.player.worldX + gp.player.screenX;
            screenY = worldY - gp.player.worldY + gp.player.screenY;

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
            g2.drawImage(image, screenX, screenY, 48, 48, null);
        }
    }

    public void setAction() {

    }

    public void checkCollision() {
        collisionOn = false;
        gp.collisionChecker.checkTile(this);
       gp.collisionChecker.checkObjectCollisionCheckerOpenTheGate(this);
    }

    public void update() {
        // check collision
        setAction();
        checkCollision();

        // update position
        if (!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }

        // update sprite
        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) spriteNum = 2;
            else spriteNum = 1;
            spriteCounter = 0;
        }

    }
}
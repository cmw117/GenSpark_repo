package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity{

    public int maxLife;
    public int life;
    public String hasKey;
    GamePanel gp;
    KeyHandler keyH;
    String direction;



    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
        direction = "down";
    }
    public void getPlayerImage() {
        try {

            up1 =  ImageIO.read(new File("C:\\GenSpark_repo\\PotatoThief\\images\\artwork\\boy_up_1.png"));
            up2 = ImageIO.read(new File("C:\\GenSpark_repo\\PotatoThief\\images\\artwork\\boy_up_2.png"));
            down1 = ImageIO.read(new File("C:\\GenSpark_repo\\PotatoThief\\images\\artwork\\boy_down_1.png"));
            down2 = ImageIO.read(new File("C:\\GenSpark_repo\\PotatoThief\\images\\artwork\\boy_down_2.png"));
            left1 = ImageIO.read(new File("C:\\GenSpark_repo\\PotatoThief\\images\\artwork\\boy_left_1.png"));
            left2 = ImageIO.read(new File("C:\\GenSpark_repo\\PotatoThief\\images\\artwork\\boy_left_2.png"));
            right1 = ImageIO.read(new File("C:\\GenSpark_repo\\PotatoThief\\images\\artwork\\boy_right_1.png"));
            right2 = ImageIO.read(new File("C:\\GenSpark_repo\\PotatoThief\\images\\artwork\\boy_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Image not found");
        }
    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;
    }

    public void update() {

        if ( keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed == true) {
                direction = "up";
                y -= speed;
            } else if (keyH.downPressed == true) {
                direction = "down";
                y += speed;
            } else if (keyH.leftPressed == true) {
                x -= speed;
                direction = "left";
            } else if (keyH.rightPressed == true) {
                x += speed;
                direction = "right";
            }
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {
            case "up":
                if (spriteNum == 1) { image = up1; }
                if (spriteNum == 2) { image = up2; }
                break;
            case "down":
                if (spriteNum == 1) { image = down1; }
                if (spriteNum == 2) { image = down2; }
                break;
            case "left":
                if (spriteNum == 1) { image = left1; }
                if (spriteNum == 2) { image = left2; }
                break;
            case "right":
                if (spriteNum == 1) { image = right1; }
                if (spriteNum == 2) { image = right2; }
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }


}
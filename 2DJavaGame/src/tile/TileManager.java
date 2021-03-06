package tile;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.awt.Graphics2D;
import java.util.Scanner;
import javax.imageio.ImageIO;

import static java.lang.Integer.parseInt;
//import static jdk.xml.internal.getResourceAsStream;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;


    public TileManager(GamePanel gp) throws IOException {
        this.gp = gp;

        tile = new Tile[6];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldCol];
        try {
        getTileImage(); } catch (IOException e) { System.out.println("error");}
        loadMap("C:\\GenSpark_repo\\2DJavaGame\\res\\maps\\world01.txt");

    }

    public void getTileImage() throws IOException {
            //DEBUG :System.out.println("loading images started");
            setup(0, "res/tiles/grass00.png", false);
            setup(1, "res/tiles//thewall.png", true);
            setup(2, "res/tiles/water01.png", true);
            setup(3, "res/tiles/sand.png", false);
            setup(4, "res/tiles/tree.png", false);
            setup(5, "res/tiles/sand.png", false);

    }
    public void setup(int index, String imagePath, boolean collision) {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(new File(imagePath));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) { e.printStackTrace(); }

    }

    public void loadMap(String filePath) throws IOException {
//    	try {
//
            //List<String> is = Files.readAllLines(new File(filePath).toPath(), Charset.defaultCharset());


    		//InputStream is = getClass().getResourceAsStream(filePath);
            InputStream is = new FileInputStream(filePath);
    		BufferedReader br = new BufferedReader(new InputStreamReader(is));
    		int worldCol = 0;
    		int worldRow = 0;
    		
    		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
    			
    			String line = br.readLine();
    			while (worldCol < gp.maxWorldCol) {
    				String[] numbers = line.split(" ");
    				int num = Integer.parseInt(numbers[worldCol]);

    				mapTileNum[worldCol][worldRow] = num;
                    System.out.println(num);
    				worldCol++;
    			}
    			if (worldCol == gp.maxWorldCol) {
    				worldCol = 0;
    				worldRow++;

    			}

    		}
        br.close();
    	}

        public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
        	
        	int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize,null);
                worldCol++;
                //x += gp.tileSize;
            }
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}

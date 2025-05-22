package newProject.entity;
import newProject.findPath.PathFinderUsingBfs;
import newProject.findPath.PathFinderUsingAStar;
import newProject.test2.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MonkeyDemon extends Entity {
    public MonkeyDemon(GamePanel gp) {
        super(gp);
        //direction = "down";
        speed = 3;
        getImage();
    }

    public void getImage() {
        try {
            up1 = ImageIO.read(new File("src/res/monster/left1.png"));
            up2 = ImageIO.read(new File("src/res/monster/left2.png"));
            down1 = ImageIO.read(new File("src/res/monster/down1.png"));
            down2 = ImageIO.read(new File("src/res/monster/down2.png"));
            left1 = ImageIO.read(new File("src/res/monster/left1.png"));
            left2 = ImageIO.read(new File("src/res/monster/left2.png"));
            right1 = ImageIO.read(new File("src/res/monster/right1.png"));
            right2 = ImageIO.read(new File("src/res/monster/right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> dir;
    int arr = 0;
    boolean onPath = false;


    @Override
    public void setAction() {
        // Nếu chưa có đường đi hoặc đã đi hết đường thì cập nhật lại đường đi
        if (!onPath || arr >= dir.size()) {
            updatePath();
        }

        // Nếu nhân vật di chuyển thì cập nhật lại đường đi mới
        if (gp.keyH.moved) {
            updatePath();
            gp.keyH.moved = false;
        }

        // Di chuyển theo đường đi nếu có đường đi hợp lệ
        if (dir != null && !dir.isEmpty() && !dir.get(0).equals("No Path Found")) {
            if (actionLockCounter == 0) {
                direction = dir.get(arr++);
            }
            if (++actionLockCounter >= 48 / speed) {
                actionLockCounter = 0;
            }
        }
    }

    private void updatePath() {
        int startRow = (worldY + solidArea.y) / 48;  // row = worldY / tileSize
        int startCol = (worldX + solidArea.x) / 48;  // col = worldX / tileSize
        int targetRow = (gp.player.worldY + gp.player.solidArea.y) / 48;
        int targetCol = (gp.player.worldX + gp.player.solidArea.x) / 48;

        dir = new ArrayList<>();

        if (gp.DifficultLevel.equals("balanced")) {
            gp.pathFinderUsingAStar.findPathUsingAStar(startRow, startCol, targetRow, targetCol);
            if (gp.pathFinderUsingAStar.path != null && !gp.pathFinderUsingAStar.path.isEmpty()) {
                dir.addAll(gp.pathFinderUsingAStar.path);
            }
        } else {
            PathFinderUsingBfs pathFinderBfs = new PathFinderUsingBfs(gp);
            pathFinderBfs.findPathUsingBFS(startRow, startCol, targetRow, targetCol);
            if (pathFinderBfs.path != null && !pathFinderBfs.path.isEmpty()) {
                dir.addAll(pathFinderBfs.path);
            }
        }


        arr = 0;
        onPath = true;
    }


}
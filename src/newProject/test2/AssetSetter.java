package newProject.test2;

import newProject.entity.MonkeyDemon;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setMonster() {
            gp.monsters[0] = new MonkeyDemon(gp);
            gp.monsters[0].worldX = gp.tileSize * 2;
            gp.monsters[0].worldY = gp.tileSize * 2;

    }
}

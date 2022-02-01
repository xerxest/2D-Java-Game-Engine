package xerxes.game.engine.renderer;

import xerxes.game.engine.*;
import xerxes.game.engine.test.GameObjTestGen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Render {

    private Sprite[] spriteList;

    public Render() throws Exception {

        ArrayList<GameObject> objList;

        GameObjTestGen gameObjs = new GameObjTestGen();

        objList = gameObjs.genObjs();


        spriteList = new Sprite[objList.size()];

        for(int i = 0; i < objList.size(); i++){
            spriteList[i] = objList.get(i).render();
        }

        Arrays.sort(spriteList, Comparator.comparingInt(Sprite::getTexID));

        Arrays.sort(spriteList, Comparator.comparingInt(o -> o.layerID));

        for(Sprite sp :spriteList){
            System.out.println("TeX ID "+sp.getTexID());
            System.out.println("Layer ID "+sp.layerID+"\n");

        }

        System.out.println("Done");

    }

}

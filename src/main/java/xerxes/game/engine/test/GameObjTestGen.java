package xerxes.game.engine.test;

import org.joml.Vector2f;
import xerxes.game.engine.Entity;

import java.util.ArrayList;
import java.util.Random;

public class GameObjTestGen {

    public ArrayList<Entity> genObjs(){

        ArrayList<Entity> objList = new ArrayList<>();

        Random rand = new Random();

        for(int i =0; i < 50; i++){

            Entity newObj = new Entity();

            newObj.position = new Vector2f(rand.nextInt(300),rand.nextInt(300));

            newObj.scale = new Vector2f(1.0f,1.0f);

            int pic = rand.nextInt(4);

            String fileName  = "";

            if(pic == 0){
                fileName = "apple.jpg";
            }
            if(pic == 1){
                fileName = "bird.jpg";
            }
            if(pic == 2){
                fileName = "test.png";
            }

            if(pic == 3){
                fileName = "bird.jpg";
            }

            newObj.setTexture(fileName);

            newObj.layerID = rand.nextInt(5);

            objList.add(newObj);

        }

        return objList;

    }
}

package xerxes.game.engine;
import java.util.ArrayList;

// TODO GameObject Searching
public class Scene {
	
	private ArrayList<Entity> ObjList;
	
	private ArrayList<Update> nonStaticObj;
	
	public Scene() {
		ObjList = new ArrayList<Entity>();
		nonStaticObj = new ArrayList<Update>();
	}
	
	void addGameObject(Entity newGameObject) {
		
		newGameObject.CurrScene = this;
		
		ObjList.add(newGameObject);
		nonStaticObj.add((Update) newGameObject);
	}

	void addNonStaticObj(Update newGameObject) {

		nonStaticObj.add( newGameObject);
	}

	void update() {
		
		for(Update u : nonStaticObj) {
			
			u.update();
			
		}
	}

}

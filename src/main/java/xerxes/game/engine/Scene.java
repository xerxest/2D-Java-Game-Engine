package xerxes.game.engine;

import java.util.ArrayList;

public class Scene {
	
	private ArrayList<GameObject> ObjList;
	
	private ArrayList<Update> nonStaticObj;
	
	public Scene() {
		ObjList = new ArrayList<GameObject>();
		nonStaticObj = new ArrayList<Update>();
	}
	
	void addGameObject(GameObject newGameObject) {
		
		// do other stuff to filter game object into correct list 
		
		newGameObject.CurrScene = this;
		
		ObjList.add(newGameObject);
		nonStaticObj.add((Update) newGameObject);
	}

	void addNonStaticObj(Update newGameObject) {

		// do other stuff to filter game object into correct list

		//newGameObject.CurrScene = this;

		//ObjList.add(newGameObject);
		nonStaticObj.add( newGameObject);
	}
	
	
	void update() {
		
		for(Update u : nonStaticObj) {
			
			u.update();
			
		}
	}


}

package xerxes.game.engine;

import org.joml.Vector2f;
import xerxes.game.engine.renderer.Texture;

public class GameObject {
	
	public Vector2f position;

	public Vector2f scale;

	public int layerID = -1;
	
	public Scene CurrScene;

	private int textureID;

	private String fileName;

	public void setTexture(String fileName){

		if(fileName == ""){
			System.out.println("FILENAME IS EMPTY");
		}else{

			this.fileName = fileName;

			textureID =  Texture.setID(fileName);
			return;
		}

		textureID = -1;
	}

	public int getTextureID(){
		return textureID;
	}

	public String getFileName(){
		return fileName;
	}

	// TODO default behavior
	public GameObject(){

	}

}



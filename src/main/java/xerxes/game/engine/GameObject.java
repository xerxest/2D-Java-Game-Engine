package xerxes.game.engine;

import org.joml.Vector2f;

public class GameObject {
	
	public Vector2f position;

	public Vector2f scale;

	public int layerID = -1;
	
	public Scene CurrScene; 
	
	private Sprite sprite;

	public String fileName;

	public Sprite render() throws Exception {

		boolean errorFlag = false;

		if(fileName == ""){
			System.out.println("Tex file is null");
			errorFlag = true;
		}

		if(position == null){
			System.out.println("Position is null");
			errorFlag = true;

		}

		if(scale == null){
			System.out.println("Scale is null");
			errorFlag = true;
		}

		if(layerID == -1){
			System.out.println("LayerID is null");
			errorFlag = true;
		}

		if(errorFlag){
			throw new Exception();
		}

		return new Sprite(fileName,position,scale,layerID);
		
	}

}

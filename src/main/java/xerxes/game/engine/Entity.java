package xerxes.game.engine;

import org.joml.Vector2f;
import org.joml.Vector3f;
import xerxes.game.engine.renderer.Texture;

import java.util.ArrayList;

public class Entity {
	
	private Vector3f position;

	private Vector3f eulerRotation;

	private Vector3f scale;

	private Model model;

	private ArrayList<Entity> children;

}



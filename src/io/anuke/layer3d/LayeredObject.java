package io.anuke.layer3d;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool.Poolable;

public class LayeredObject implements Poolable{
	/**The textureregion layers for this object.
	 * Modifying these after the object is added will have no effect.*/
	public TextureRegion[] regions;
	/**The position and rotation of the object.*/
	public float x, y, z, rotation;
	
	/**Creatures a layered object from all the regions.*/
	public LayeredObject(TextureRegion... regions){
		this.regions = regions;
	}
	
	/**Creatures a layered object from all the textures*/
	public LayeredObject(Texture... textures){
		regions = new TextureRegion[textures.length];
		for(int i = 0; i < textures.length; i ++) regions[i] = new TextureRegion(textures[i]);
	}
	
	/**Sets the position for the object. 
	 * @return the object, for chaining.*/
	public LayeredObject setPosition(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}
	
	/**Sets the position for the object. 
	 * @return this object, for chaining.*/
	public LayeredObject setPosition(float x, float y){
		return setPosition(x,y,z);
	}
	
	/**Adds this object to the list of global layers. 
	 * @return the object, for chaining.*/
	public LayeredObject add(){
		if(regions == null) throw new RuntimeException("The regions are empty, initialize them first!");
		LayeredRenderer.instance().addObject(this);
		return this;
	}
	
	/**Removes this object from the list of global layers.*/
	public void remove(){
		LayeredRenderer.instance().removeObject(this);
	}

	@Override
	public void reset(){
		remove();
		x = y = z = rotation = 0;
		regions = null;
	}
}

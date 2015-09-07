package net.deepwater.corgi;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CorgiGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	GameStateManager gsm;
	
	@Override
	public void create () {
		gsm = new GameStateManager();
		gsm.pushState(new GameStatePlay());
	}

	@Override
	public void render () {
		gsm.update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.render();
	}
}

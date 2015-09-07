package net.deepwater.corgi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by Kyle on 9/6/2015.
 */
public class GameStatePlay extends GameState {
    List<Shape> shapes;
    double speed = 50;
    OrthographicCamera cam;
    ShapeRenderer shapeRenderer;
    Random rand;
    private float randQual = 1;
    Shape lastShape;
    int lastLine;

    Color colors[] = {
            new Color(1, 0, 0, 1), new Color(0, 1, 0, 1), new Color(0, 0, 1, 1),
            new Color(1, 1, 0, 1), new Color(0, 1, 1, 1), new Color(1, 0, 1, 1)
    };

    public GameStatePlay() {
        shapeRenderer = new ShapeRenderer();
        shapes = new ArrayList<Shape>();
        cam = new OrthographicCamera(Gdx.graphics.getHeight(), Gdx.graphics.getWidth());
        cam.setToOrtho(false);
        rand = new Random();
    }

    private void addNewRandom() {
        int line = rand.nextInt();
        if (lastShape != null && lastShape.position.y < 100) {
            while (lastLine == line) {
                line = rand.nextInt();
            }
        }
        lastShape = new Shape(rand.nextInt(2) * 3, colors[rand.nextInt(colors.length)], Gdx.graphics.getWidth() * .15F + rand.nextInt(4) * .25F * Gdx.graphics.getWidth());
        shapes.add(lastShape);
        lastLine = line;
    }

    @Override
    public void update(float dt) {
        speed += 5 * dt;
        for (Iterator<Shape> it = shapes.iterator(); it.hasNext(); ) {
            Shape s = it.next();
            s.position.y += speed * dt;
            if (s.position.y - 50 > cam.viewportHeight) {
                it.remove();
            }
        }

        randQual -= .05 * dt * speed / 50;

        if (rand.nextFloat() > randQual && (lastShape == null || lastShape.position.y > 50)) {
            addNewRandom();
            randQual = 1;
        }
    }

    @Override
    public void render() {
        cam.update();
        shapeRenderer.setProjectionMatrix(cam.combined);
        for (Shape s : shapes) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(s.color);
            switch (s.sides) {
                case 0:
                    shapeRenderer.circle(s.position.x, s.position.y, 50);
                    break;
                case 3:
                    shapeRenderer.triangle(s.position.x - 43.3f, s.position.y - 30, s.position.x, s.position.y + 45, s.position.x + 43.3f, s.position.y - 30);
                    break;
                default:
                    break;
            }
            shapeRenderer.end();
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void stop() {

    }
}

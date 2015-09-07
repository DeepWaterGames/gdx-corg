package net.deepwater.corgi;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Kyle on 9/6/2015.
 */
public class Shape {
    public Shape(int sides, Color color, float x) {
        this.sides = sides;
        this.color = color;
        this.position = new Vector2(x, -50);
    }
    public int sides;
    public Vector2 position;
    public Color color;
}

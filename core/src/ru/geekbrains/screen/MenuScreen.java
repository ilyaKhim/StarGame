package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;

public class MenuScreen extends BaseScreen {


    private Texture img;
    private Vector2 touch;
    private Texture newImg;
    private Vector2 dest;
    private Vector2 pos;
    private Vector2 inTouch;

    @Override
    public void show() {
        super.show();
        img = new Texture("space.jpg");
        touch= new Vector2();
        newImg = new Texture("badlogic.jpg");
        pos = new Vector2(0,0); // начальное положение картинки
        dest = new Vector2(pos.x,pos.y); // точка назначения (берется из getTouch)
        inTouch = new Vector2(); // отслеживает нажатие и записывает его в методе touchDown()


    }

    @Override
    public void render(float delta) {
        super.render(delta);
        float v = 0.1f; // коэфф скорости перемещения иконки (по сути разбивает наш вектор на 10 маленьких)
        dest = new Vector2((getTouch().x- pos.x)*v,(getTouch().y - pos.y)*v);
        pos.add(dest);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(newImg, pos.x , pos.y );
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight()-screenY);
        System.out.println(touch.x + " "+touch.y);
        setTouch(touch.x, touch.y);
        return false;

    }

    public void setTouch(float x, float y){
        inTouch.set(x,y);
    }

    public Vector2 getTouch(){
        return inTouch;
    }

}

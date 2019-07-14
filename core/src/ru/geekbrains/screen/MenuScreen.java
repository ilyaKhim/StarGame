package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import ru.geekbrains.base.BaseScreen;

public class MenuScreen extends BaseScreen {


    private Texture img;
    private Vector2 touch;
    private Texture newImg;
    private Vector2 dest;
    private Vector2 pos;
    private Vector2 inTouch;
    private Vector2 inDrag;
    private boolean isDragging;
    private boolean isUp, isDown, isLeft, isRight;

    @Override
    public void show() {
        isUp = isDown = isLeft = isRight = false;
        super.show();
        isDragging = false;
        img = new Texture("space.jpg");
        newImg = new Texture("badlogic.jpg");
        pos = new Vector2(0,0); // положение картинки
        dest = new Vector2(pos.x,pos.y); // точка назначения (берется из getTouch)
        inTouch = new Vector2(); // отслеживает нажатие и записывает его в методе touchDown()
        inDrag = new Vector2(); // отслеживание перетаскивания

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        float v = 0.1f;// коэфф скорости перемещения иконки (по сути разбивает наш вектор на 10 маленьких)
//        if(isDragging){
//            dest.set(getDrag().x - pos.x, getDrag().y - pos.y);
//
//        }
//        else
        dest.set(((getTouch().x- pos.x)*v),(getTouch().y - pos.y)*v);
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
        System.out.println("TouchDown screenX = " + screenX + " screenY = " + screenY + " pointer "+pointer+" button "+button);
        setTouch(screenX-(newImg.getHeight()/2), Gdx.graphics.getHeight()-screenY-(newImg.getHeight()/2));
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        return super.touchUp(screenX, screenY, pointer, button);
    }

    public void setTouch(float x, float y){
        inTouch.set(x,y);
    }

    public Vector2 getTouch(){
        return inTouch;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        isDragging = true;
        setDrag(screenX, Gdx.graphics.getHeight()-screenY);
        return false;
    }



    private void setDrag(float x, float y) {
        inDrag.set(x,y);
    }

    private Vector2 getDrag() {return inDrag;}



    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.UP:
                setTouch(this.pos.x, this.pos.y + 10);
                break;
            case Input.Keys.DOWN:
                setTouch(this.pos.x, this.pos.y - 10);
                break;
            case Input.Keys.LEFT:
                setTouch(this.pos.x - 10, this.pos.y);
                break;
            case Input.Keys.RIGHT:
                setTouch(this.pos.x + 10, this.pos.y);
                break;
        }
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case 19: isUp = false;
        }
        super.keyUp(keycode);
        return true;
    }
}

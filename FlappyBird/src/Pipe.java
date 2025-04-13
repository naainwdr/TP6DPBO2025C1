import java.awt.*;

public class Pipe {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private Image image;
    private int velocityX;
    private boolean passed = false;
    private boolean scored = false;

    public Pipe(int posX, int posY, int width, int height, Image image){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;

        this.velocityX = -1;
        this.passed = false;
    }

    public void setPosX(int posX){
        this.posX = posX;
    }

    public void setPosY(int posY){
        this.posY = posY;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setImage(Image image){
        this.image = image;
    }

    public void setVelocityX(int velocityX){
        this.velocityX = velocityX;
    }

    public void setPassed(boolean passed){
        this.passed = passed;
    }

    public void setScored(boolean scored){
        this.scored = scored;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public int getPosX(){
        return this.posX;
    }

    public int getPosY(){
        return this.posY;
    }

    public Image getImage(){
        return this.image;
    }

    public int getVelocityX(){
        return this.velocityX;
    }

    public boolean getPassed(){
        return this.passed;
    }

    public boolean getScored(){
        return this.scored;
    }


}

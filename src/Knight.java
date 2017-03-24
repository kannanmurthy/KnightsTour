import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by kannanmurthy on 3/24/17.
 */
public class Knight {
    private BufferedImage knight;
    private int col;
    private int row;

    public Knight(int row, int col) {
        setUpImg();
        this.col = col;
        this.row = row;
    }

    private void setUpImg() {
        try{
            knight = ImageIO.read(new File("knight.png"));
        } catch (IOException e) {
            System.out.println("Couldn't open image!");
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(knight,col,row,null);
    }





}

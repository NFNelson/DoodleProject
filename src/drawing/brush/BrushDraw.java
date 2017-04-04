package drawing.brush;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BrushDraw extends DrawBehavior {
    
    private int brushSize = 0;
    
    public BrushDraw(int minBrushSize, int maxBrushSize) {
        super(minBrushSize, maxBrushSize);
    }
    
    private BufferedImage brush;
    
    @Override
    public void performDraw(Point point, Graphics2D g2d, double pressure) {
        setBrush(pressure);
        int x = point.x - (brushSize / 2) + 1;
        int y = point.y - (brushSize / 2) + 1;
        
        g2d.drawImage(brush, x, y, null);
        
        setBrushClip(new Rectangle(x, y, brushSize, brushSize));
    }
    
    public void setBrush(double pressure) {
        brushSize = (int) ((maxBrushSize - minBrushSize) * pressure + minBrushSize);
        brush = new BufferedImage(brushSize, brushSize,
                                  BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = brush.createGraphics();
        ///randomly generates the brush color
        //needs to be fixed
        Random rand = new Random();
        int red, green, blue;
        red = rand.nextInt(256);
        green = rand.nextInt(256);
        blue = rand.nextInt(256);
        g2d.setColor(new Color(red, green, blue, (int) 255 / brushSize));
        for (int i = 0; i < brushSize * 0.75; i++) {
            g2d.fillOval(i, i, brushSize - i * 2, brushSize - i * 2);
        }
        g2d.fillOval(0, 0, brushSize, brushSize);
    }
    
}

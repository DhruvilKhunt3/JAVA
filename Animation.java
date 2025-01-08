import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class EarthMoonAnimation extends JPanel {
    private int moonAngle = 0; 
    private final int[][] stars = new int[100][3]; 

    public EarthMoonAnimation() {
       
        Random rand = new Random();
        for (int i = 0; i < stars.length; i++) {
            stars[i][0] = rand.nextInt(800); 
            stars[i][1] = rand.nextInt(800); 
            stars[i][2] = rand.nextInt(3) + 1; 
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Earth and Moon Animation");
        EarthMoonAnimation animation = new EarthMoonAnimation();

        frame.add(animation);
       
        //my name is dhruvil
        frame.setSize(800, 800);
       
        //this code for animathion
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        //this code writen by chatgpt
        frame.setVisible(true);

        Timer timer = new Timer(50, e -> {
            animation.updateAnimation();
            animation.repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        
        for (int[] star : stars) {
            g2d.setColor(new Color(255, 255, 153, 150)); 
            g2d.fillOval(star[0], star[1], star[2], star[2]);
        }

        
        int earthCenterX = getWidth() / 2;
        int earthCenterY = getHeight() / 2;
      
        for (int radius = 120; radius <= 200; radius += 10) {
            float alpha = (200f - radius) / 100f; 
            g2d.setColor(new Color(0, 0, 255, Math.max(0, (int) (alpha * 255))));
            g2d.fillOval(earthCenterX - radius, earthCenterY - radius, radius * 2, radius * 2);
        }

        
        int earthX = earthCenterX - 50;
        int earthY = earthCenterY - 50;
       
        g2d.setColor(Color.BLUE);
        
        g2d.fillOval(earthX, earthY, 100, 100);

       
        int moonOrbitRadius = 150;
        
        int moonX = (int) (earthCenterX + moonOrbitRadius * Math.cos(Math.toRadians(moonAngle)) - 20);
        
        int moonY = (int) (earthCenterY + moonOrbitRadius * Math.sin(Math.toRadians(moonAngle)) - 20);

        
        for (int i = 1; i <= 10; i++) {
            int tailX = (int) (earthCenterX + moonOrbitRadius * Math.cos(Math.toRadians(moonAngle - i * 2)) - 20);
            //angle
            int tailY = (int) (earthCenterY + moonOrbitRadius * Math.sin(Math.toRadians(moonAngle - i * 2)) - 20);
            //set angle
            float alpha = 1.0f - i * 0.1f; 
            //aura light
            g2d.setColor(new Color(255, 255, 0, (int) (alpha * 255))); 
            //tail of moonpath
            g2d.fillOval(tailX, tailY, 10, 10);
        }

       
        g2d.setColor(Color.GRAY);
        g2d.fillOval(moonX, moonY, 40, 40);
    }
    public void updateAnimation() {
        moonAngle += 5; 
        if (moonAngle >= 360) {
            moonAngle = 0;
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BouncingBall extends JPanel implements Runnable {

    private int x = 50, y = 50;
    private int diameter = 30; 
    private int xSpeed = 2, ySpeed = 2;
    private Color backgroundColor = Color.BLACK;
    private final Random random = new Random();

    public BouncingBall() {
        // Start  animation thread
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //  background color
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw  ball
        g.setColor(Color.RED);
        g.fillOval(x, y, diameter, diameter);
    }

    @Override
    public void run() {
        while (true) {
            // Update position
            x += xSpeed;
            y += ySpeed;

            // Check  collision 
            if (x <= 0 || x + diameter >= getWidth()) {
                xSpeed = -xSpeed; 
                changeBackgroundColor(); 
            }
            if (y <= 0 || y + diameter >= getHeight()) {
                ySpeed = -ySpeed; 
                changeBackgroundColor();  
            }

            
            repaint();

            
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void changeBackgroundColor() {
        
        backgroundColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bouncing Ball");
        BouncingBall panel = new BouncingBall();

        frame.add(panel);
        frame.setSize(800, 600); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

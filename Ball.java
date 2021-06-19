package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Ball extends JPanel {

	private static final int DIAMETER = 30;
	int x = 0;
	int y = 0;
	int xa = 1;
	int ya = 1;
	private Principal joc;

	public Ball(Principal joc) {
		this.joc = joc;
	}

	public void moveBall() {
		boolean changeDirection = true;
		if (x + xa < 0) {
			xa = joc.speed;
		}
		if (x + xa > joc.getWidth() - DIAMETER) {
			xa = -joc.speed;
		}
		if (y + ya < 0) {
			ya = joc.speed;
		}
		if (y + ya > joc.getHeight() - DIAMETER) {
			joc.gameOver();
		} else if (collision()) {
			ya = -joc.speed;
			y = joc.racquet.getTopY() - DIAMETER;
			joc.speed++;
		} else
			changeDirection = false;

		if (changeDirection = false) {
			Sound.BALL.play();
		}
		x = x + xa;
		y = y + ya;
	}

	private boolean collision() {
		return joc.racquet.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillOval(x, y, DIAMETER, DIAMETER);

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}

}

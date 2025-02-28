package acsse.csc2a.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Vector;

public class CatchEngine {
    public static final int WIDTH = 250;
    public static final int HEIGHT = 250;

    public static final int MIN_BALL_DELAY = 30;
    public static final int MAX_BALL_DELAY = 100;
    public static final int SPEED_UP_DELAY = 20;

    private Vector<Ball> balls;
    private Bucket bucket;
    private int points = 0;
    private int lives = 3;
    private int speedDelay = 0;
    private int addBallDelay = MAX_BALL_DELAY;
    private int ballDelay = 0;

    public CatchEngine() {
        balls = new Vector<>();
        bucket = new Bucket(WIDTH / 2, HEIGHT - Bucket.HEIGHT / 2);
    }

    public void moveLeft() {
        if (lives > 0) {
            bucket = bucket.moveLeft();
        }
    }

    public void moveRight() {
        if (lives > 0) {
            bucket = bucket.moveRight();
        }
    }

    public void addBall() {
        balls.add(new Ball((int) (Math.random() * WIDTH), 0, Math.random() > .5));
    }

    public void moveBalls() {
        for (int i = 0; i < balls.size(); i++) {
            balls.set(i, balls.get(i).moveDown());
        }
    }

    public void testBallCatch() {
        for (int i = 0; i < balls.size(); i++) {
            if (bucket.contains(balls.get(i))) {
                if (balls.get(i).isGood()) points++;
                else lives--;
                balls.remove(i);
                i--;
            } else if (balls.get(i).getLocation().getY() >= HEIGHT + Ball.RADIUS) {
                if (balls.get(i).isGood()) lives--;
                balls.remove(i);
                i--;
            }
        }
    }

    public void update() {
        if (lives > 0) {
            ballDelay++;
            speedDelay++;
            if (ballDelay >= addBallDelay) {
                ballDelay = 0;
                addBall();
            }
            if (speedDelay >= SPEED_UP_DELAY) {
                speedDelay = 0;
                addBallDelay--;
                if (addBallDelay < MIN_BALL_DELAY) addBallDelay = MIN_BALL_DELAY;
            }
            moveBalls();
            testBallCatch();
        }
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillText("Points: " + points, 10, 20);
        gc.fillText("Lives: " + lives, 10, 30);

        if (lives <= 0) {
            gc.setFill(Color.RED);
            gc.fillText("You Lose", WIDTH / 2 - 20, HEIGHT / 2);
        }

        gameElementVisitor visitor = new DrawVisitor(gc);
        for (Ball ball : balls) {
            ball.accept(visitor);
        }
        bucket.accept(visitor);
    }
}

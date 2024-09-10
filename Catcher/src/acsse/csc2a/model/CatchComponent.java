package acsse.csc2a.model;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.animation.AnimationTimer;

public class CatchComponent extends Canvas {
    private CatchEngine engine;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public CatchComponent() {
        super(250, 250);
        engine = new CatchEngine();
        GraphicsContext gc = getGraphicsContext2D();

        setFocusTraversable(true);
        setOnKeyPressed(this::keyPressed);
        setOnKeyReleased(this::keyReleased);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateState();
                paint(gc);
            }
        };
        timer.start();
    }

    public void paint(GraphicsContext gc) {
        engine.draw(gc);
    }

    public void updateState() {
        if (leftPressed) engine.moveLeft();
        if (rightPressed) engine.moveRight();
        engine.update();
    }

    public void keyPressed(KeyEvent ke) {
        if (ke.getCode() == KeyCode.LEFT) leftPressed = true;
        else if (ke.getCode() == KeyCode.RIGHT) rightPressed = true;
    }

    public void keyReleased(KeyEvent ke) {
        if (ke.getCode() == KeyCode.LEFT) leftPressed = false;
        else if (ke.getCode() == KeyCode.RIGHT) rightPressed = false;
    }
}


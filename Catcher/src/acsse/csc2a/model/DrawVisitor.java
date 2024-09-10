package acsse.csc2a.model;

import javafx.scene.canvas.GraphicsContext;

public class DrawVisitor implements gameElementVisitor {
    private GraphicsContext gc;

    public DrawVisitor(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void visitBall(Ball ball) {
        ball.draw(gc);
    }

    @Override
    public void visitBucket(Bucket bucket) {
        bucket.draw(gc);
    }
}

package acsse.csc2a.model;

public interface gameElementVisitor {
    void visitBall(Ball ball);
    void visitBucket(Bucket bucket);
}

package chapter1.dxball;

import chapter1.gameengine.Game;
import chapter1.gameengine.GameController;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class DXBallGame implements Game, GameController<DXBallGame> {

    int ballCount;
    DXBallArea area;

    DXBallRacket racket;
    DXBall ball;

    DXTileGenerator tileGenerator;
    private boolean ballAttached;

    public DXBallArea getArea() {
        return area;
    }

    public DXBallRacket getRacket() {
        return racket;
    }

    public DXTileGenerator getTileGenerator() {
        return tileGenerator;
    }

    @Override
    public boolean isGameOver() {
        return (ballCount==0) || area.isEmpty();
    }

    @Override
    public void init() {
        ballCount=DXConstants.INITIAL_BALL;

        area = new DXBallArea(new Point2D.Double(0,0), DXConstants.GameWidth, DXConstants.GameHeight);
        area.init(this);

        double rX=  area.topLeft().getX() +  DXConstants.GameWidth/2 - DXConstants.RacketWidth/2;
        double rY= area.topLeft().getY() + DXConstants.GameHeight - DXConstants.RacketHeight;
        racket = new DXBallRacket(rX,rY,DXConstants.RacketWidth,DXConstants.RacketHeight);

        racket.init(this);


        ball = new DXBall(rX,rY,2*DXConstants.BallRadius,2*DXConstants.BallRadius);
        ball.init(this);
        ballAttached= true;

    }

    @Override
    public void update(long time) {
        area.update(this,time);
        racket.update(this,time);
        ball.update(this,time);

        handleBoundaryCollisions();
        handleRacketCollision();

        handleTileCollisions();

    }

    private void handleTileCollisions() {

    }

    private void handleRacketCollision() {

        if (ballAttached || ball.velocity.getY()<=0)
            return;

        double cX = ball.topLeft().getX() + DXConstants.BallRadius;
        double cY = ball.topLeft().getY() + DXConstants.BallRadius;

        if ( ( cX >=racket.topLeft().getX() && cX< racket.topLeft().getX()+DXConstants.RacketWidth) &&(cY + DXConstants.BallRadius >= racket.topLeft().getY())  )
        {
            double vY= ball.velocity.getY()*-1;
            double ratio = (cX-(racket.topLeft().getX()+DXConstants.RacketWidth/2 ))/ DXConstants.RacketWidth/2;
            double vX = -1*ratio*DXConstants.INITIAL_BALL_SPEED_Y;

            ball.setVelocity(new Point2D.Double(vX,vY));
        }
    }

    private void handleBoundaryCollisions() {
        double cX = ball.topLeft().getX() + DXConstants.BallRadius;
        double cY = ball.topLeft().getY() + DXConstants.BallRadius;

        if ( cY -DXConstants.BallRadius  <= area.topLeft().getY() )
        {
            ball.setVelocity(new Point2D.Double(ball.getVelocity().getX(), ball.getVelocity().getY()*-1));
        }
        if ( cY + DXConstants.BallRadius >= area.topLeft().getY()+area.getHeight())
        {
            ballCount--;
            ballAttached = true;
            ball.init(this);
        }
        if ( ( cX -DXConstants.BallRadius  <= area.topLeft().getX() ) || ( cX +DXConstants.BallRadius  >= area.topLeft().getX() + area.getWidth() ) )
        {
            ball.setVelocity(new Point2D.Double(ball.getVelocity().getX()*-1, ball.getVelocity().getY()));
        }


    }

    @Override
    public String getTitle() {
        return "DXBall 0.1";
    }

    public Point2D initialBallPos() {
        double bX = racket.topLeft().getX()+racket.getWidth()/2- DXConstants.BallRadius;
        double bY = racket.topLeft().getY()-2*DXConstants.BallRadius;
        return new Point2D.Double(bX,bY);
    }

    public void render(Graphics g) {
        area.render(this,g);
        racket.render(this,g);
        ball.render(this,g);
    }

    public void setTileGenerator(DXTileGenerator tg) {
        tileGenerator = tg;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton()== MouseEvent.BUTTON1)
        {
            throwBall();
        }
    }

    private void throwBall() {
        ballAttached= false;
        ball.setVelocity(new Point2D.Double(0, DXConstants.INITIAL_BALL_SPEED_Y));
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();

        moveRacket(x);
    }

    private void moveRacket(int x) {
        double rX = x-DXConstants.RacketWidth/2;
        double rY = racket.topLeft().getY();

        racket.topLeft().setLocation(rX,rY);

        if (ballAttached)
        {
            ball.topLeft().setLocation(initialBallPos());
        }
    }
}

package mx.unison.pong;

public class GameModel {
    public static final int MAX_X = 640;
    public static final int MAX_Y = 480;

    // Propiedades de la pelota
    private double ballX = 320;
    private double ballY = 240;
    private double ballDX = 5; // Velocidades más pequeñas para mayor fluidez
    private double ballDY = 5;
    private final int ballSize = 20;

    // Propiedades de la raqueta
    private double paddleX = 280;
    private final double paddleY = 450;
    private final int paddleWidth = 80;
    private final int paddleHeight = 20;

    private GameState state = GameState.RUNNING;

    // Getters y Setters encapsulados
    public double getBallX() { return ballX; }
    public void setBallX(double ballX) { this.ballX = ballX; }

    public double getBallY() { return ballY; }
    public void setBallY(double ballY) { this.ballY = ballY; }

    public double getBallDX() { return ballDX; }
    public void setBallDX(double ballDX) { this.ballDX = ballDX; }

    public double getBallDY() { return ballDY; }
    public void setBallDY(double ballDY) { this.ballDY = ballDY; }

    public int getBallSize() { return ballSize; }

    public double getPaddleX() { return paddleX; }
    public void setPaddleX(double paddleX) { this.paddleX = paddleX; }

    public double getPaddleY() { return paddleY; }
    public int getPaddleWidth() { return paddleWidth; }
    public int getPaddleHeight() { return paddleHeight; }

    public GameState getState() { return state; }
    public void setState(GameState state) { this.state = state; }
}
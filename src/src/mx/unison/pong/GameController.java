package mx.unison.pong;

import javax.swing.Timer;

public class GameController {
    private final GameModel model;
    private final Board board;
    private final Timer timer;

    public GameController(GameModel model, Board board) {
        this.model = model;
        this.board = board;

        // Ejecutar a ~60 FPS (1000ms / 60 = ~16ms)
        this.timer = new Timer(16, e -> gameLoop());
    }

    public void start() {
        model.setState(GameState.RUNNING);
        timer.start();
    }

    public void pause() {
        if (model.getState() == GameState.RUNNING) {
            model.setState(GameState.PAUSED);
        }
    }

    public void resume() {
        if (model.getState() == GameState.PAUSED) {
            model.setState(GameState.RUNNING);
        }
    }

    public void stop() {
        model.setState(GameState.STOPPED);
        timer.stop();
    }

    private void gameLoop() {
        if (model.getState() == GameState.RUNNING) {
            updatePhysics();
        }
        // El repaint se llama siempre, permitiendo dibujar los mensajes de pausa/fin
        board.repaint();
    }

    private void updatePhysics() {
        double nextX = model.getBallX() + model.getBallDX();
        double nextY = model.getBallY() + model.getBallDY();

        // --- Actividad 4: Corrección de Colisiones Básicas ---
        // Colisión pared izquierda y derecha
        if (nextX <= 0) {
            nextX = 0; // Prevenir tunneling
            model.setBallDX(-model.getBallDX());
        } else if (nextX + model.getBallSize() >= GameModel.MAX_X) {
            nextX = GameModel.MAX_X - model.getBallSize();
            model.setBallDX(-model.getBallDX());
        }

        // Colisión techo
        if (nextY <= 0) {
            nextY = 0;
            model.setBallDY(-model.getBallDY());
        }

        // Colisión Raqueta
        if (model.getBallDY() > 0 &&
                nextY + model.getBallSize() >= model.getPaddleY() &&
                nextY <= model.getPaddleY() + model.getPaddleHeight() &&
                nextX + model.getBallSize() >= model.getPaddleX() &&
                nextX <= model.getPaddleX() + model.getPaddleWidth()) {

            // Rebotar fijando la posición justo encima de la raqueta
            nextY = model.getPaddleY() - model.getBallSize();
            model.setBallDY(-model.getBallDY());
        }

        // Pelota perdida (supera el límite inferior)
        if (nextY >= GameModel.MAX_Y) {
            model.setState(GameState.LOST);
        }

        // Aplicar nuevas coordenadas
        model.setBallX(nextX);
        model.setBallY(nextY);
    }
}
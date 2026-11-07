package mx.unison.pong;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Board extends JPanel {
    private final GameModel model;
    private final Font theFont = new Font("SansSerif", Font.BOLD, 24);

    public Board(GameModel model) {
        this.model = model;
        setPreferredSize(new Dimension(GameModel.MAX_X, GameModel.MAX_Y));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Suavizado de bordes para mejor renderizado
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Renderizado basado en el estado
        if (model.getState() == GameState.PAUSED) {
            g2d.setFont(theFont);
            g2d.setColor(Color.BLUE);
            g2d.drawString("Paused", 20, 30);
        } else if (model.getState() == GameState.LOST) {
            g2d.setFont(theFont);
            g2d.setColor(Color.RED);
            g2d.drawString("Game Over", GameModel.MAX_X / 2 - 60, GameModel.MAX_Y / 2);
        }

        // Dibujar pelota
        g2d.setColor(Color.RED);
        g2d.fill(new Ellipse2D.Double(model.getBallX(), model.getBallY(), model.getBallSize(), model.getBallSize()));

        // Dibujar raqueta
        g2d.setColor(Color.BLACK);
        g2d.fill(new Rectangle2D.Double(model.getPaddleX(), model.getPaddleY(), model.getPaddleWidth(), model.getPaddleHeight()));
    }
}
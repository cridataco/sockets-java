package co.edu.uptc.view;

import co.edu.uptc.presenter.Contract;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MainPanel extends JPanel implements MouseListener, MouseMotionListener {
    private Contract.Presenter presenter;
    private Rectangle rectangle;
    private Graphics2D g2d;
    private int widthDifference;
    private int heightDifference;
    private int colorRectangle;
    private int colorPanel;

    public MainPanel(Contract.Presenter presenter) {
        this.presenter = presenter;
        rectangle = new Rectangle();
        colorPanel = 0;
        colorRectangle = 0;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        g2d.setColor(Color.cyan);
        g2d.setColor(new Color(colorRectangle));
        g2d.draw(rectangle);
        this.setBackground(new Color(colorPanel));
    }

    private void drawBackground(Color color) {
        g2d.setColor(color);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    private void setSizeDifferences(MouseEvent e) {
        widthDifference = e.getX() - rectangle.x;
        heightDifference = e.getY() - rectangle.y;
    }

    private boolean verifyRectangle(MouseEvent e) {
        return rectangle.contains(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point point = new Point(e.getX(), e.getY());
        if (verifyRectangle(e)) {
            setSizeDifferences(e);
            point = new Point(e.getX() - widthDifference, e.getY() - heightDifference);
        }
        presenter.updateCoordinates(point);
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
        presenter.updateCoordinates(new Point(e.getX() - widthDifference, e.getY() - heightDifference));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public void setPresenter(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void setColorRectangle(int colorRectangle) {
        this.colorRectangle = colorRectangle;
    }

    public void setColorPanel(int colorPanel) {
        this.colorPanel = colorPanel;
    }
}

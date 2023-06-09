package co.edu.uptc.view;

import co.edu.uptc.presenter.Contract;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame implements Contract.View {
    private Contract.Presenter presenter;
    private PrincipalPanel mainPanel;

    public MyFrame() {
        setTitle("Rectangle");
        setSize(700, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponent();
    }

    private void initComponent() {
        mainPanel = new PrincipalPanel(presenter);
        add(mainPanel);
    }

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        this.presenter = presenter;
        mainPanel.setPresenter(presenter);
    }

    @Override
    public void start() {
        setVisible(true);
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void updateView(Rectangle rectangle, int colorRectangle, int colorPanel) {
        mainPanel.setRectangle(rectangle);
        mainPanel.setColorRectangle(colorRectangle);
        mainPanel.setColorPanel(colorPanel);
        repaint();
    }
}

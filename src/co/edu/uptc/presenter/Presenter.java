package co.edu.uptc.presenter;

import java.awt.*;

public class Presenter implements Contract.Presenter {
    private Contract.View view;
    private Contract.Model model;

    @Override
    public void setView(Contract.View view) {
        this.view = view;
    }

    @Override
    public void setModel(Contract.Model model) {
        this.model = model;
    }

    @Override
    public void loadData() {
        model.loadData();
    }

    @Override
    public void updateView() {
        view.updateView(model.getRectangle(),model.getColorRectangle(),model.getColorPanel());
    }

    @Override
    public void updateCoordinates(Point point) {
        model.updateRectanglePosition(point);
    }
}
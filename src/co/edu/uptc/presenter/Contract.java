package co.edu.uptc.presenter;

import java.awt.*;

public interface Contract {
    interface View {
        void setPresenter(Presenter presenter);

        void start();

        void updateView(Rectangle rectangle, int colorRectangle, int colorPanel);
    }

    interface Presenter {
        void setView(View view);

        void setModel(Model model);

        void loadData();

        void updateView();

        void updateCoordinates(Point point);
    }

    interface Model {
        void setPresenter(Presenter presenter);

        void loadData();

        Rectangle getRectangle();

        void updateRectanglePosition(Point point);

        int getColorRectangle();

        int getColorPanel();
    }
}

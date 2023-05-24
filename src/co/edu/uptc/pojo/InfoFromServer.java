package co.edu.uptc.pojo;

import java.awt.*;

public class InfoFromServer {
    private FigureInformation figureInformation;
    private PanelInformation panelInformation;

    public InfoFromServer() {
        figureInformation = new FigureInformation();
        panelInformation = new PanelInformation();
    }

    public InfoFromServer(FigureInformation figureInformation, PanelInformation panelInformation) {
        this.figureInformation = figureInformation;
        this.panelInformation = panelInformation;
    }

    public void setFigureInformation(FigureInformation figureInformation) {
        this.figureInformation = figureInformation;
    }

    public void setPanelInformation(PanelInformation panelInformation) {
        this.panelInformation = panelInformation;
    }

    public FigureInformation getFigureInformation() {
        return figureInformation;
    }

    public PanelInformation getPanelInformation() {
        return panelInformation;
    }
}

package de.dfki.gui.renderers.statusbar;

import de.dfki.server.serverstatus.StatusObserver;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 * Created by alvaro on 5/18/17.
 */
public class StatusBarRenderer implements StatusObserver {
    public static final String STATUS_INFO = "Status: ";
    private final Label statusLabel;

    public StatusBarRenderer(Label statusLabel){
        this.statusLabel = statusLabel;
    }

    @Override
    public void update(String status) {
        Platform.runLater(()-> statusLabel.setText(STATUS_INFO + status));
    }
}

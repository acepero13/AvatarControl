package de.dfki.gui.renderers.moodbar;

import de.dfki.gui.Main;
import de.dfki.gui.renderers.Renderable;
import de.dfki.server.notifications.DataNotification;
import de.dfki.server.notifications.MoodNotification;
import de.dfki.server.notifications.NotificationType;
import de.dfki.server.receiver.ReceiverObserver;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * Created by alvaro on 4/30/17.
 */
public class MoodBarRender implements Renderable, ReceiverObserver {


    public static final double MAX_WIDTH = 64.0;
    public static final String IMGS_MARKER_PNG = "imgs/marker.png";
    private final Pane moodBar;
    private int value;
    private MarkPositionCalculator imageLocator;

    public MoodBarRender(Pane moodBar, ImageView moodImage){
        this.moodBar = moodBar;
        imageLocator = new MarkPositionCalculator(moodImage.getFitWidth());
    }

    @Override
    public void render() {
        clearMoodBar();
        createImage();

    }

    private void createImage() {
        try {
            showImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showImage() {

        ImageView imageView = buildImageControl();
        Platform.runLater(()-> moodBar.getChildren().add(imageView));
    }

    protected ImageView buildImageControl() {

        ImageView imageView = new ImageView();
        imageView.setFitHeight(24);
        imageView.setFitWidth(24);
        double x = imageLocator.calculatePosition(value);
        imageView.setLayoutX(x);
        imageView.setLayoutY(2);
        Image image = new Image(String.valueOf(Main.class.getClassLoader().getResource(IMGS_MARKER_PNG)));
        imageView.setImage(image);
        return imageView;
    }

    protected void clearMoodBar() {
        if(moodBar.getChildren().size() >0)
            Platform.runLater(()-> moodBar.getChildren().clear());;
    }

    @Override
    public void update(DataNotification notification) {
        if(notification.getType() == NotificationType.MOOD){
            value = ((MoodNotification)notification).getMoodValue();
            render();
        }
    }


}

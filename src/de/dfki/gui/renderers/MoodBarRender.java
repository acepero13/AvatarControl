package de.dfki.gui.renderers;

import de.dfki.gui.Main;
import de.dfki.server.notifications.DataNotification;
import de.dfki.server.notifications.MoodNotification;
import de.dfki.server.notifications.NotificationType;
import de.dfki.server.receiver.ReceiverObserver;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Created by alvaro on 4/30/17.
 */
public class MoodBarRender implements Renderable, ReceiverObserver {


    public static final double MAX_WIDTH = 64.0;
    private final HBox moodBar;
    private int value;
    private ImageLocator imageLocator;

    public MoodBarRender(HBox moodBar){
        this.moodBar = moodBar;
        imageLocator = new ImageLocator();
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

    private void showImage() throws Exception {
        String imagePath = imageLocator.getImage(value);
        ImageView imageView = buildImageControl(imagePath);
        Platform.runLater(()-> moodBar.getChildren().add(imageView));
    }

    protected ImageView buildImageControl(String imagePath) {
        ImageView imageView = new ImageView();
        imageView.setFitHeight(moodBar.getMaxHeight());
        imageView.setFitWidth(MAX_WIDTH);
        Image image = new Image(String.valueOf(Main.class.getClassLoader().getResource(imagePath)));
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

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

import javax.naming.ldap.Control;

/**
 * Created by alvaro on 4/30/17.
 */
public class MoodBarRender implements Renderable, ReceiverObserver {


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

    protected void createImage() {
        try {
            showImage();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showImage() throws Exception {
        String imagePath = imageLocator.getImage(value);
        ImageView imageView = buildImage(imagePath);
        Platform.runLater(()-> moodBar.getChildren().add(imageView));
    }

    protected ImageView buildImage(String imagePath) {
        ImageView imageView = new ImageView();
        imageView.setFitHeight(moodBar.getMaxHeight());
        imageView.setFitWidth(64.0);
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

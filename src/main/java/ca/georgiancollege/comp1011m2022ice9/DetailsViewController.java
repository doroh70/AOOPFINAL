package ca.georgiancollege.comp1011m2022ice9;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

public class DetailsViewController{

    @FXML
    private Label labelTitle;

    @FXML
    private ImageView imageView;

    @FXML
    private TextArea taPlot;

    @FXML
    private TextArea taActors;

    @FXML
    private TextArea taDetails;

    @FXML
    void LoadDetails(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Movie m = (Movie) stage.getUserData();
        System.out.println(m);
        imageView.setImage(new Image(m.getPoster()));
        taPlot.setText("Plot: "+"\n"+m.getPlot());
        labelTitle.setText(m.getTitle());
        taActors.setText("Actors: "+"\n"+m.getActors());
        taDetails.setText("Movie Details: "+"\n"+"Writers: " +m.getWriters()+"\nDirectors: "+m.getDirector()+"\nGenre: "+m.getGenre()+"\nRuntime: "+m.getRuntime()+"\nYear released: "+m.getYear());

        //SceneManager.Instance().changeScene(event, "search-view.fxml");
    }






}

package ca.georgiancollege.comp1011m2022ice9;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchViewController implements Initializable
{
    @FXML
    private BorderPane borderPane;

    @FXML
    private ImageView imageView;

    @FXML
    private ListView<Movie> resultsListView;

    @FXML
    private TextField searchTextField;

    @FXML
    void searchButtonClicked(ActionEvent event)
    {
        var searchResults = APIManager.Instance().getMovieFromOMDBBySearchTerm(searchTextField.getText());
        resultsListView.getItems().clear();
        if(searchResults.getMovies() != null)
        {
            resultsListView.getItems().addAll(searchResults.getMovies());

            resultsListView.getSelectionModel().select(0);
        }
        else
        {
            // movie was not found - maybe output to a message label of some kind
        }
    }

    @FXML
    void searchTextFieldSubmitted(ActionEvent event)
    {
        searchButtonClicked(event);
        resultsListView.requestFocus();
    }

    @FXML
    void LoadDetailsView(ActionEvent event) throws IOException {
        Movie m = resultsListView.getSelectionModel().getSelectedItem();
        var x = APIManager.Instance().getMovieFromOMDBByTitleAndYear(m.getTitle(), m.getYear());
        SceneManager.Instance().changeScene(event, "details-view.fxml", x);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        var posterNotFoundImage = new Image(getClass().getResourceAsStream("poster-not-found.png"));

        resultsListView.getSelectionModel().selectedItemProperty().addListener((obs, oldMovieSelected, newMovieSelected) ->{
            System.out.println(newMovieSelected);
            try
            {
                imageView.setImage(new Image(newMovieSelected.getPoster()));
            }catch(Exception e)
            {
                imageView.setImage(posterNotFoundImage);
            }
        });
    }
}
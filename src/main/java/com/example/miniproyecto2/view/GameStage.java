package com.example.miniproyecto2.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GameStage extends Stage {
    public GameStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto2/game-view.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        setScene(scene);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/miniproyecto2/StyleCss/game.css")).toExternalForm());

        // Agregar imagen
        getIcons().add(new Image(String.valueOf(getClass().getResource("/com/example/miniproyecto2/images/favicon.png"))));

        setTitle("SUDOKU");
        // Disables window resizing.
        setResizable(false);

        show();
    }

    private static class GameStageHolder{
        private static GameStage INSTANCE;
    }
    /**
     * Static method to get the singleton instance of GameStage.
     * If the instance is not yet created, it will instantiate a new GameStage.
     *
     * @return the singleton instance of GameStage.
     * @throws IOException if there is an issue loading resources.
     */
    public static GameStage getInstance() throws IOException{
        GameStageHolder.INSTANCE = GameStageHolder.INSTANCE != null?
                GameStageHolder.INSTANCE : new GameStage();
        return GameStageHolder.INSTANCE;
    }
    /**
     * Static method to delete the singleton instance of GameStage by closing its resources.
     */
    public static void deletedInstance(){
        GameStageHolder.INSTANCE.close();
        GameStageHolder.INSTANCE = null;
    }
}

package com.example.miniproyecto2.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStage extends Stage {
    public GameStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto2/game-view.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        setScene(scene);

        setTitle("SUDOKU");
        setResizable(false);

        show();
    }

    private static class GameStageHolder{
        private static GameStage INSTANCE;
    }

    public static GameStage getInstance() throws IOException{
        GameStageHolder.INSTANCE = GameStageHolder.INSTANCE != null?
                GameStageHolder.INSTANCE : new GameStage();
        return GameStageHolder.INSTANCE;
    }

    public static void deletedInstance(){
        GameStageHolder.INSTANCE.close();
        GameStageHolder.INSTANCE = null;
    }
}

package com.example.miniproyecto2.model;

import com.example.miniproyecto2.controller.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class Chronometer implements IChronometer{
    private Timeline timeline;
    private int minutes;
    private int seconds;

    private GameController controller;

    // El objeto que recibe este constructor ya esta asociado al archivo Fxml, por lo que si se crea una
    // nueva instancia esta no estaría asociada a los metodos y atributos del FXML. * Esto solo sucede con
    // clases de tipo controllador*
    public Chronometer(GameController controller) {

        this.controller = controller;

        // Inicializa el Timeline con intervalos de 1 segundo (1000 ms)
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            updateTime();
            updateLabel();
        }));

        // Configura el Timeline para que se repita indefinidamente
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    @Override
    public void updateTime(){
        seconds ++;
        if(seconds == 60){
            seconds = 0;
            minutes ++;
        }
    }

    @Override
    public void updateLabel() {
        // Aseguramos que los minutos y segundos tengan dos dígitos
        String timeString = String.format("%02d:%02d", minutes, seconds);

        controller.getLabelTime().setText(timeString); // Actualiza el Label en la vista
    }

    // Metodo para comenzar el cronometro
    public void start() {
        timeline.play();
    }

    // Detiene el cronometro
    public void stop() {
        timeline.stop();
    }

    public void restart(){
        minutes = 0;
        seconds = 0;
    }

}

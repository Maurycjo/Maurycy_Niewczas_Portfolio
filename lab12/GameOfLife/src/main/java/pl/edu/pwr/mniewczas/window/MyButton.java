package pl.edu.pwr.mniewczas.window;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class MyButton extends Button {


    private State currentState = State.WHITE;
    private State nextState = currentState;
    private double size = 30;
    public MyButton(){

        super();
        setPrefSize(size, size);
        setStyle("-fx-background-color: #ffffff; -fx-border-color: gray");


        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeState();
            }
        });
    }

    public void changeState(){

        if(currentState ==State.WHITE){
            currentState =State.BLACK;
            setStyle("-fx-background-color: #000000; -fx-border-color: gray");
        } else{
            currentState =State.WHITE;
            setStyle("-fx-background-color: #ffffff; -fx-border-color: gray");
        }
    }

    public State getCurrentState() {
        return currentState;
    }

    public State getNextState() {
        return nextState;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }

    public void updateState(){

        if(currentState!=nextState){
            changeState();
        }
    }

    public void changeStateIfDifferent(State state){

        if(state == currentState) return;
        changeState();

    }

    @Override
    public String toString() {
        return "MyButton{" +
                "state=" + currentState +
                '}';
    }
}

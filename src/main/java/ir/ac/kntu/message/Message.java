package ir.ac.kntu.message;

import java.io.Serializable;

public class Message implements Serializable {

    private MessageOption messageOption;
    private String message;
    private String messageAnswer;
    private String phoneNumber;
    private State state;

    public Message(String phoneNumber, String message, MessageOption messageOption) {
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.messageOption = messageOption;
        this.messageAnswer = "";
        state = State.SUBMIT;
    }

    public MessageOption getMessageOption() {
        return messageOption;
    }

    public void setMessageOption(MessageOption messageOption) {
        this.messageOption = messageOption;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageAnswer() {
        return messageAnswer;
    }

    public void setMessageAnswer(String messageAnswer) {
        this.messageAnswer = messageAnswer;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        if (messageAnswer.isEmpty()) {
            return "Message{" +
                    "messageOption=" + messageOption +
                    ", message='" + message + '\'' +
                    ", state=" + state +
                    '}';
        }
        return "Message{" +
                "messageOption=" + messageOption +
                ", message='" + message + '\'' +
                ", messageAnswer='" + messageAnswer + '\'' +
                ", state=" + state +
                '}';
    }
}

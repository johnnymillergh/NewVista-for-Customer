package com.jm.newvista.bean;

public class MessageEntity {
    private String sourceIp;
    private String sourcePort;
    private String destinationIp;
    private String destinationPort;
    private String message;

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(String sourcePort) {
        this.sourcePort = sourcePort;
    }

    public String getDestinationIp() {
        return destinationIp;
    }

    public void setDestinationIp(String destinationIp) {
        this.destinationIp = destinationIp;
    }

    public String getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(String destinationPort) {
        this.destinationPort = destinationPort;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return getClass() + " source: [" + sourceIp + ":" + sourcePort
                + "], destination: [" + destinationIp + ":" + destinationPort
                + "], message: " + message;
    }
}

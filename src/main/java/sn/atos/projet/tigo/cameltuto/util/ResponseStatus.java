/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.atos.projet.tigo.cameltuto.util;

/**
 *
 * @author A746054
 */
public class ResponseStatus {
    private Boolean  hasError=false ;
    private Boolean  messageProcesseced =false;
    private String  errorMessage =""  ;
    private Boolean  camelProcessiingError = false ;
    private String  camelProcessiingErrorMessage="";

    public Boolean getHasError() {
        return hasError;
    }

    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }

    public Boolean getMessageProcesseced() {
        return messageProcesseced;
    }

    public void setMessageProcesseced(Boolean messageProcesseced) {
        this.messageProcesseced = messageProcesseced;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Boolean getCamelProcessiingError() {
        return camelProcessiingError;
    }

    public void setCamelProcessiingError(Boolean camelProcessiingError) {
        this.camelProcessiingError = camelProcessiingError;
    }

    public String getCamelProcessiingErrorMessage() {
        return camelProcessiingErrorMessage;
    }

    public void setCamelProcessiingErrorMessage(String camelProcessiingErrorMessage) {
        this.camelProcessiingErrorMessage = camelProcessiingErrorMessage;
    }
    
}

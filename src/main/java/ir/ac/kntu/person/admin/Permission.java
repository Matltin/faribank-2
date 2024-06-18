package ir.ac.kntu.person.admin;

import java.io.Serializable;

public class Permission implements Serializable {
    private boolean authentication;
    private boolean request;
    private boolean userAccess;
    private boolean contact;
    private boolean setting;
    private boolean transfer;
    private boolean report;
    private boolean state;
    private boolean user;

    public Permission() {
        this.authentication = true;
        this.request = true;
        this.userAccess = true;
        this.contact = true;
        this.setting = true;
        this.transfer = true;
        this.report = true;
        this.state = true;
        this.user = true;
    }

    public boolean isContact() {
        return contact;
    }

    public void setContact(boolean contact) {
        this.contact = contact;
    }

    public boolean isSetting() {
        return setting;
    }

    public void setSetting(boolean setting) {
        this.setting = setting;
    }

    public boolean isTransfer() {
        return transfer;
    }

    public void setTransfer(boolean transfer) {
        this.transfer = transfer;
    }

    public boolean isReport() {
        return report;
    }

    public void setReport(boolean report) {
        this.report = report;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }

    public boolean isAuthentication() {
        return authentication;
    }

    public void setAuthentication(boolean authentication) {
        this.authentication = authentication;
    }

    public boolean isRequest() {
        return request;
    }

    public void setRequest(boolean request) {
        this.request = request;
    }

    public boolean isUserAccess() {
        return userAccess;
    }

    public void setUserAccess(boolean userAccess) {
        this.userAccess = userAccess;
    }
}

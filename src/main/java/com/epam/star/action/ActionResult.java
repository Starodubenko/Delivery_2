package com.epam.star.action;

public class ActionResult {
    private String view;
    private boolean redirection;

    public ActionResult() {

    }

    public ActionResult(String view) {
        this(view, false);
    }

    public ActionResult(String view, boolean redirection) {
        this.view = view;
        this.redirection = redirection;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public boolean isRedirect() {
        return redirection;
    }

    public void setRedirect(boolean redirection) {
        this.redirection = redirection;
    }
}

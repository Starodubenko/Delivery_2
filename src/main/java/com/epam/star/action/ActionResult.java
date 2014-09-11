package com.epam.star.action;

import org.json.JSONObject;

public class ActionResult {
    private String view;
    private boolean redirection;

    public JSONObject getJson() {
        return json;
    }

    public ActionResult(JSONObject json) {

        this.json = json;
    }

    private JSONObject json;

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

    public void setJson(JSONObject json) {
        this.json = json;
    }

    public boolean isJson() {
        if(json == null) return false;
        else return true;

    }

    public void setRedirect(boolean redirection) {
        this.redirection = redirection;
    }
}

package model;

import org.json.JSONObject;
import ui.Two048;



public class Num {
    // I'm using SuppressWarnings because I need the value of Num be used outside the Num class
    @SuppressWarnings({"checkstyle:VisibilityModifier", "checkstyle:SuppressWarnings"})
    public Integer value;


    public Num(int n) {
        this.value = n;

    }



}


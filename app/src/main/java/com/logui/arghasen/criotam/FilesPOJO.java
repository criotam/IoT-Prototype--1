package com.logui.arghasen.criotam;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilesPOJO {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("file_url")
    @Expose
    private ArrayList<String> fileUrl = null;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(ArrayList<String> fileUrl) {
        this.fileUrl = fileUrl;
    }

}
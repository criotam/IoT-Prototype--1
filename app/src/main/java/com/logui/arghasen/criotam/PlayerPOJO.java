package com.logui.arghasen.criotam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerPOJO {

    @SerializedName("player_age")
    @Expose
    private String playerAge;
    @SerializedName("player_height")
    @Expose
    private String playerHeight;
    @SerializedName("player_id")
    @Expose
    private String playerId;
    @SerializedName("player_name")
    @Expose
    private String playerName;
    @SerializedName("player_sex")
    @Expose
    private String playerSex;
    @SerializedName("player_weight")
    @Expose
    private String playerWeight;
    @SerializedName("key")
    @Expose
    private String key;

    public String getPlayerAge() {
        return playerAge;
    }

    public void setPlayerAge(String playerAge) {
        this.playerAge = playerAge;
    }

    public String getPlayerHeight() {
        return playerHeight;
    }

    public void setPlayerHeight(String playerHeight) {
        this.playerHeight = playerHeight;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerSex() {
        return playerSex;
    }

    public void setPlayerSex(String playerSex) {
        this.playerSex = playerSex;
    }

    public String getPlayerWeight() {
        return playerWeight;
    }

    public void setPlayerWeight(String playerWeight) {
        this.playerWeight = playerWeight;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}

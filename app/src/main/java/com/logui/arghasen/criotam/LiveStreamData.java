package com.logui.arghasen.criotam;

import java.util.ArrayList;
import java.util.List;

public class LiveStreamData {

    List<String> exp1_lc_streaming;

    List<String> exp2_emg_streaming;

    List<String> exp2_lc_streaming;

    List<String> exp3_emg_streaming;

    List<String> exp3_fp_streaming;

    public LiveStreamData(){}

    public LiveStreamData(ArrayList<String> exp1_lc_streaming, ArrayList<String> exp2_emg_streaming,
                          ArrayList<String> exp2_lc_streaming, ArrayList<String> exp3_emg_streamingm,
                          ArrayList<String> exp3_fp_streaming){

        this.exp1_lc_streaming = exp1_lc_streaming;
        this.exp2_emg_streaming = exp2_emg_streaming;
        this.exp2_lc_streaming = exp2_lc_streaming;
        this.exp3_emg_streaming = exp3_emg_streamingm;
        this.exp3_fp_streaming = exp3_fp_streaming;

    }

    public List<String> getExp1_lc_streaming() {
        return exp1_lc_streaming;
    }

    public List<String> getExp2_emg_streaming() {
        return exp2_emg_streaming;
    }

    public List<String> getExp2_lc_streaming() {
        return exp2_lc_streaming;
    }

    public List<String> getExp3_emg_streaming() {
        return exp3_emg_streaming;
    }

    public List<String> getExp3_fp_streaming() {
        return exp3_fp_streaming;
    }

    public void setExp1_lc_streaming(List<String> exp1_lc_streaming) {
        this.exp1_lc_streaming = exp1_lc_streaming;
    }

    public void setExp2_emg_streaming(List<String> exp2_emg_streaming) {
        this.exp2_emg_streaming = exp2_emg_streaming;
    }

    public void setExp2_lc_streaming(List<String> exp2_lc_streaming) {
        this.exp2_lc_streaming = exp2_lc_streaming;
    }

    public void setExp3_emg_streaming(List<String> exp3_emg_streaming) {
        this.exp3_emg_streaming = exp3_emg_streaming;
    }

    public void setExp3_fp_streaming(List<String> exp3_fp_streaming) {
        this.exp3_fp_streaming = exp3_fp_streaming;
    }

}

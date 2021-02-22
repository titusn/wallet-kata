package com.titusnachbauer.wallet.service;

import com.google.gson.annotations.SerializedName;

public class StatusDto implements Dto {
    @SerializedName("status")
    private final String status;

    public StatusDto(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

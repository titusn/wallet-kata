package com.titusnachbauer.client;

import com.google.gson.annotations.SerializedName;

public class StatusDto {
    @SerializedName("status")
    private final String status;

    public StatusDto(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

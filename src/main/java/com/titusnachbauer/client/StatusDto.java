package com.titusnachbauer.client;

import com.google.gson.annotations.SerializedName;
import com.titusnachbauer.service.Dto;

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

package com.titusnachbauer.wallet.rateprovider.iexservice;

import com.google.gson.annotations.SerializedName;

class StatusDto implements Dto {
    @SerializedName("status")
    private final String status;

    public StatusDto(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

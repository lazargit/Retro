package com.shamildev.retro.domain.models;


/**
 * Created by Shamil Lazar.
 */

public class GuestSession{

    private Boolean success;
    private String guestSessionId;
    private String expiresAt;

    public GuestSession(Boolean success, String guestSessionId, String expiresAt) {
        this.success = success;
        this.guestSessionId = guestSessionId;
        this.expiresAt = expiresAt;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getGuestSessionId() {
        return guestSessionId;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

}

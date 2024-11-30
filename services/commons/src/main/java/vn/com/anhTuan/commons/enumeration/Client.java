package vn.com.anhTuan.commons.enumeration;

import vn.com.anhTuan.commons.exception.UnknownValueException;

import java.util.Arrays;

public enum Client {
    PORTAL("portal"),
    APP("app");

    private final String code;

    Client(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public static Client of(String code) {
        return Arrays.stream(Client.values())
                .filter(client -> client.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new UnknownValueException("Unknown code: " + code));
    }

}

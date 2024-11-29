package vn.com.anhTuan.commons.enumeration;

import lombok.Getter;
import vn.com.anhTuan.commons.exception.UnknownValueException;

import java.util.Arrays;

public class StaticEnum {
    public enum EnableAcess {
        YES(1, "Có thể truy cập"),
        NO(0, "Không thể truy cập");

        @Getter
        private int code;
        private String description;

        EnableAcess(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public static EnableAcess safeValueOf(int code) {
            return Arrays.stream(StaticEnum.EnableAcess.values())
                    .filter(item -> item.getCode() == code)
                    .findFirst()
                    .orElseThrow(() -> new UnknownValueException("Unknown enum value: " + code));
        }
    }


    public enum IsDeletedEnum {
        YES(1, "yes"),
        NO(0, "no"),
        ;
        @Getter
        private Integer code;
        private String description;

        IsDeletedEnum(Integer code, String description) {
            this.code = code;
            this.description = description;
        }

        public static IsDeletedEnum safeValueOf(Integer code) {
            return Arrays.stream(StaticEnum.IsDeletedEnum.values())
                    .filter(item -> item.getCode().equals(code))
                    .findFirst()
                    .orElseThrow(() -> new UnknownValueException("Unknown enum value: " + code));
        }
    }

}

package vn.com.anhTuan.commons.utils;

import jakarta.annotation.Nullable;

public interface MimeType {
    static String fromExtension(@Nullable String extension) {
        if(extension == null) {
            return "application/octet-stream";
        }

        return switch (extension) {
            // Images
            case "avif" -> "image/avif";
            case "bmp" -> "image/bmp";
            case "gif" -> "image/gif";
            case "ico" -> "image/x-icon";
            case "jpeg", "jpe", "jpg" -> "image/jpeg";
            case "png" -> "image/png";
            case "tiff", "tif" -> "image/tiff";
            case "svg" -> "image/svg+xml";
            case "wav" -> "audio/wav";
            case "wma" -> "audio/x-ms-wma";
            case "wmv" -> "audio/x-ms-wmv";
            case "webp" -> "image/webp";

            // Document
            case "csv" -> "text/csv";
            case "doc" -> "application/msword";
            case "docx" -> "application/msword";
            case "pdf" -> "application/pdf";
            case "ppt" -> "application/vnd.ms-powerpoint";
            case "xls" -> "application/vnd.ms-excel";
            case "xlsx" -> "application/vnd.ms-excel";
            case "rtf" -> "application/rtf";
            case "txt" -> "text/plain";

            // Archives
            case "rar" -> "application/vnd.rar";
            case "zip" -> "application/zip";
            case "7z" -> "application/x-7z-compressed";

            default -> "application/octet-stream";
        };

    }
}

package com.hinamlist.hinam_list.service.json_scraper.exception;

public class APINotFoundException extends Exception {
    private String missingId;
    public APINotFoundException(String missingId) {
        super(String.format("Unexpected API Response - Missing item when searching for ID %s", missingId));
        this.missingId = missingId;
    }

    public String getMissingId() {
        return missingId;
    }
}

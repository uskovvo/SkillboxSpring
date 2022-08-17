package org.example.web.dto;

import org.springframework.lang.NonNull;

public class BookIdToRemove {

    @NonNull
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

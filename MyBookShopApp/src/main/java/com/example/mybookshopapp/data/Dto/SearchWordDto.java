package com.example.mybookshopapp.data.Dto;

public class SearchWordDto {

    private String example;

    public SearchWordDto(){}

    public SearchWordDto(String example) {
        this.example = example;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}

package com.example.mybookshopapp.data;

public class SearchWordDto {

    private String example;
    private int count;

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

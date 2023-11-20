package com.example.demo.DTO;

public class updateDTO {
    private Integer price;

    public Integer getPrice(){
        return price;
    }
    public void setPrice(Integer price){
        this.price = price;
    }

    public updateDTO() {
    }

    public updateDTO(Integer price) {
        this.price = price;
    }
}

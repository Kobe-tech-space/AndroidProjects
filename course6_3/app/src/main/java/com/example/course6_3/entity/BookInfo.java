package com.example.course6_3.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "BookInfo")
public class BookInfo {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String name;
    private String author;
    private String press;
    private double price;
    public int getId() {return id;}
    public String getName() {return name;}
    public String getAuthor() {return author;}
    public String getPress() {return press;}
    public double getPrice() {return price;}
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setAuthor(String author) {this.author = author;}
    public void setPress(String press) {this.press = press;}
    public void setPrice(double price) {this.price = price;}

    @Override
    public String toString() {
        return "BookInfo{" +
                "id=" + id +
                ",name='" + name + '\'' +
                ",author='" + author + '\'' +
                ",press='" + press + '\'' +
                ",price=" + price +
                '}';
        }
}

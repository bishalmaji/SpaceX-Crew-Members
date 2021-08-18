package com.bishal.datafrominternet.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "crewtable")
public class CrewModel {

    @PrimaryKey (autoGenerate = true)
    public int i;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name ="image")
    public String image;
    @ColumnInfo(name ="agency")
    public String agency;
    @ColumnInfo(name ="wikipedia")
    public String wikipedia;
    @ColumnInfo(name ="status")
    public String status;


    public CrewModel( String name, String image, String agency, String wikipedia, String status) {
        this.name = name;
        this.image = image;
        this.agency = agency;
        this.wikipedia = wikipedia;
        this.status = status;
    }

    public CrewModel(List<CrewModel> crewModelList) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

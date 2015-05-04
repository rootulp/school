package com.rootulp.rootulpjsona;

/**
 * Created by rootulp on 5/3/15.
 */

import java.io.Serializable;

public class artist implements Serializable {
    String imageURL;
    String title;
    String name;
    String nationality;
    String paintingdate;
    String paintingtype;
    String medium;
    String dimensions;
    String classification;
    String provenance;
    String description;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPaintingdate() {
        return paintingdate;
    }

    public void setPaintingdate(String paintingdate) { this.paintingdate = paintingdate; }

    public String getPaintingtype() {return paintingtype;}

    public void setPaintingtype(String paintingtype) {this.paintingtype = paintingtype; }

    public String getMedium() { return medium;    }

    public void setMedium(String medium) {        this.medium = medium;    }

    public String getDimensions() {        return dimensions;    }

    public void setDimensions(String dimensions) {    this.dimensions = dimensions;    }

    public String getClassification() { return classification;    }

    public void setClassification(String classification) { this.classification = classification;    }

    public String getProvenance() { return provenance;    }

    public void setProvenance(String provenance) {        this.provenance = provenance;    }

    public String getDescription() {        return description;    }

    public void setDescription(String description) {        this.description = description;    }
}


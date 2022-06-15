package com.diplom.e_commerce.model;

public class Component {
    public String id, name, type, properties, href, img;

    public Component() {
    }

    public Component(String id, String name, String type, String properties, String href, String img) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.properties = properties;
        this.href = href;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

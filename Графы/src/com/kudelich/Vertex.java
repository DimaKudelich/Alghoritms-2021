package com.kudelich;

public class Vertex {
    private int name;

    public Vertex(int name){
        this.setName(name);
    }

    public void setName(int name){
        this.name = name;
    }

    public int getName(){
        return name;
    }
}

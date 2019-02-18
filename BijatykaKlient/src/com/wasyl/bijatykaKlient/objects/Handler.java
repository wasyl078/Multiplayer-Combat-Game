package com.wasyl.bijatykaKlient.objects;

import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> object;

    public Handler(){
        object = new LinkedList<GameObject>();
    }

    public void draw(GraphicsContext gc){
        for(int i = 0 ; i < object.size();i++){
            object.get(i).draw(gc);
        }
    }

    public void update(){
        for(int i = 0 ; i < object.size();i++){
            object.get(i).update(object);
        }
    }

    public void addObject(GameObject gameObject){
        object.add(gameObject);
    }
}

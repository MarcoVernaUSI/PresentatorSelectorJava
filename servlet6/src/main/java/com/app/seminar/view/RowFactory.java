package com.app.seminar.view;

import com.app.seminar.model.Entity;
import com.github.manliogit.javatags.element.Element;

public interface RowFactory {
    Element row(Entity e); 
}
package com.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import com.github.manliogit.javatags.element.Element;

public class NoPageFound implements View{

    
    @Override
    public Element[] getBody() {
        return new Element[]{
            div(attr("class -> row"),
                div(attr("class -> col-12 text-center"),
                    h2(text("Oops!")),
                    h1(text("404 Not Found")),
                    p(text("Sorry, an error has occured, Requested page not found!"))))
    };}
    
}
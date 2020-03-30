package com.app.seminar.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

public class NoPageFound {
    public String renderHtmlLayout() {
        return html5(
            head(
                meta(attr("charset -> utf-8")),
                meta(attr("http-equiv -> X-UA-Compatible", "content -> IE=edge")),
                meta(attr("name -> viewport", "content -> width=device-width, initial-scale=1")),
    
                link(attr("href -> /css/bootstrap.min.css", "rel -> stylesheet"))),
            body(
                div(attr("class -> row"),
                    div(attr("class -> col-12 text-center"),
                        h1(text("Oops!")),
                        h1(text("404 Not Found")))
          ))).render();
    }
}

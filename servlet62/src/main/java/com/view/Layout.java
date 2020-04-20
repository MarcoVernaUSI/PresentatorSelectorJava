package com.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import com.github.manliogit.javatags.element.Element;

public class Layout{
    
    private final String _title;
    private final View _bodyContent;
    
    public Layout(String title, View bodyContent) {
        _title = title;
        _bodyContent = bodyContent;
    }

	public Element build() {
		return 
			html5(
		        head(
		          meta(attr("charset -> utf-8")),
		          meta(attr("http-equiv -> X-UA-Compatible","content -> IE=edge")),
		          meta(attr("name -> viewport","content -> width=device-width, initial-scale=1")),
		          title(_title),
		          text("<!-- Bootstrap core CSS -->"),
		          link(attr("href -> /css/bootstrap.min.css","rel -> stylesheet"))
		         
		        ),
		        body(
		            div(attr("class -> container"),
		                  div(attr("class -> page-header","id -> banner"),     
		                    _bodyContent.getBody()
		                  )
		                ),
		            script(attr("src -> /js/jquery.min.js")),
		            script(attr("src -> /js/bootstrap.min.js")),
		            script(attr("src -> //netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js")),
		            script(attr("src -> //code.jquery.com/jquery-1.11.1.min.js"))

		            )
	            );
	  }

}

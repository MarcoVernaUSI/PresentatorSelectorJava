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
		          link(attr("href -> /css/bootstrap.min.css","rel -> stylesheet", "media -> screen")),
		          link(attr("href -> /css/custom.css","rel -> stylesheet"))
		        ),
		        body(
		            div(attr("class -> navbar navbar-default navbar-fixed-top"),
	                      div(attr("class -> container"),
	                          div(attr("class -> navbar-header"),
	                              a(attr("href -> /", "class -> navbar-brand"), "Seminar"),
	                              button(
	                                  attr("class -> navbar-toggle", 
	                                       "type -> button", 
	                                       "data-toggle -> collapse", 
	                                       "data-target -> #navbar-main"
	                                   ),
	                                  span(attr("class -> icon-bar")),
	                                  span(attr("class -> icon-bar")),
	                                  span(attr("class -> icon-bar"))
	                                )
	                          ),
	                          div(attr("class-> navbar-collapse collapse", "id -> navbar-main"),
	                              ul(attr("class -> nav navbar-nav navbar-right"),
	                                  li(attr("class -> dropdown"),
	                                      a(attr(
	                                          "class -> dropdown-toggle", 
	                                          "data-toggle -> dropdown",
	                                          "href -> #",
	                                          "id -> download",
	                                          "aria-expanded -> false"
	                                         ),
	                                         "Account"
	                                        
	                                      ),
	                                      ul(attr(
	                                          "class -> dropdown-menu",
	                                          "aria-labelledby -> download"
	                                          ),
	                                          li(
	                                             a(attr("href -> /"), "settings")
	                                          ),
	                                          li(
	                                              attr("class -> divider")
	                                          ),
	                                          li(a(attr("href -> /"), "logout"))
	                                       )
	                                  )
	                              )
	                          )
	                      )
	              ),
		                    div(attr("class -> container"),
		                        div(attr("class -> page-header","id -> banner"),
		                            div(attr("class -> row"),
		                                div(attr("class -> col-lg-8 col-md-7 col-sm-6"),
		                                  h1("Seminar" ),
		                                  p(attr("class -> lead"),
		                                    text("Manage your courses!")
		                                  )
		                                )
		                              ),
		                              div(attr("class -> row"),
		                                div(attr("class -> col-lg-2 col-md-2 col-sm-3"),
		                                  div(attr("class -> list-group table-of-contents"),
		                                    a(attr("class -> list-group-item","href -> /"),
		                                      text("Course List")
		                                    ),
		                                    a(attr("class -> list-group-item","href -> /course/create"),
		                                      text("Create Course")
		                                    ),
		                                    a(attr("class -> list-group-item","href -> /student"),
		                                       text("Student List")
		                                    ),
		                                    a(attr("class -> list-group-item","href -> /student/create"),
		                                      text("Create Student")
		                                    )
		                                  )
		                                ),
		                                div(attr("class -> col-lg-8 col-md-8 col-sm-9"),
		                            _bodyContent.getBody()
		                                    )
		                                  )
		                                ),
		                        footer(
		                            div(attr("class -> row"),
		                              div(attr("class -> col-lg-12"),
		                                p(
		                                  a(attr("href -> http://bootswatch.com/cerulean","rel -> nofollow"),
		                                    text("Cerulean theme")
		                                  )
		                                ),
		                                p(
		                                  text("Code released under the "),
		                                  a(attr("href -> https://github.com/thomaspark/bootswatch/blob/gh-pages/LICENSE"),
		                                    text("MIT License")
		                                  ),
		                                  text(".")
		                                ),
		                                p(
		                                  text("GmTechnologies")
		                                )
		                              )
		                            )
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

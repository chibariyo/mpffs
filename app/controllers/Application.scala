package controllers

import play.api.mvc._
import play.twirl.api.Html

object Application extends Controller {

  def index = Action {
    val content = Html("<div>This is the content for the sample app<div>")
    Ok(views.html.main("Home")(content))
  }
}

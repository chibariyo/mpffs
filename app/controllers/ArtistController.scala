package controllers

import play.api.mvc._
import models.Artist

/**
  * Created by david on 16/12/21.
  */
object ArtistController extends Controller {

  def listArtist = Action {
    Ok(views.html.home(Artist.fetch))
  }

  def fetchArtistByName(name:String) = Action {
    Ok(views.html.home(Artist.fetchByName(name)))
  }

  def search(name:String, country:String) = Action {
    val result = Artist.fetchByNameOrCountry(name, country)
    if (result.isEmpty) {
      NoContent
    }
    else {
      Ok(views.html.home(result))
    }
  }

  def search2(name:Option[String], country:String) = Action {
    val result = name match {
      case Some(n) => Artist.fetchByNameOrCountry(n, country)
      case None => Artist.fetchByCountry(country)
    }
    if (result.isEmpty) {
      NoContent
    }
    else {
      Ok(views.html.home(result))
    }
  }
}

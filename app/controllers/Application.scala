package controllers

import play.api.libs.json.JsValue
import play.api.mvc._
import play.twirl.api.Html
import java.io.File
import models.User

object Application extends Controller {

  def index = Action {
    val content = Html("<div>This is the content for the sample app<div>")
    Ok(views.html.main("Home")(content))
  }

  def subscribeOld = Action {
    request =>
      val reqBody: AnyContent = request.body
      val textContent: Option[String] = reqBody.asText
      textContent.map {
        emailId =>
          Ok("added " + emailId + " to subscriber's list")
      }.getOrElse {
        BadRequest("improper request body")
      }
  }

  def subscribeText = Action(parse.text) {
    request =>
      Ok("added " + request.body + " to subscriber's list")
  }

  def subscribe = Action(parse.json) {
    request =>
      val reqData: JsValue = request.body
      val emailId = (reqData \ "emailId").as[String]
      val interval = (reqData \ "interval").as[String]
      Ok(s"added $emailId to subscriber's list and will send updates every $interval")

  }

  def createProfile = Action(parse.multipartFormData) {
    request =>
      val formData = request.body.asFormUrlEncoded
      val email: String = formData.get("email").get(0)
      val name: String = formData.get("name").get(0)
      val userId: Long = User(email, name).save
      request.body.file("displayPic").map {
        picture =>
          val path = "/socialize/user/"
          if (!picture.filename.isEmpty) {
            picture.ref.moveTo(new File(path + userId + ".jpeg"))
          }
          Ok("successfully added user")
      }.getOrElse {
        BadRequest("failed to add user")
      }
  }
}

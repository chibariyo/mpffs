package controllers

import play.api.mvc._
import models.Task

/**
  * Created by david on 16/12/21.
  */
object TaskController extends Controller {
  def index = Action {
    Redirect(routes.TaskController.tasks)
  }

  def tasks = Action {
    Ok(views.html.index(Task.all))
  }

  def newTask = Action(parse.urlFormEncoded) {
    implicit request =>
      Task.add(request.body.get("taskName").get.head)
      Redirect(routes.TaskController.index)
  }

  def deleteTask(id: Int) = Action {
    Task.delete(id)
    Ok
  }
}

package models

case class Task(id: Int, name: String)

/**
  * Created by david on 16/12/21.
  */
object Task {
  private var taskList: List[Task] = List()

  def all: List[Task] = {
    taskList
  }

  def add(taskName: String) = {
    var lastId: Int = 0
    if (!taskList.isEmpty) {
      lastId = taskList.last.id
    }
    val newId: Int = lastId + 1
    taskList = taskList ++ List(Task(newId, taskName))
  }

  def delete(taskId: Int) = {
    taskList = taskList.filterNot(task => task.id == taskId)
  }
}

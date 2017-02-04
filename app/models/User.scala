package models

case class User(email: String, name: String) {
  def save : Long = {
    print("save")
    1L
  }
}

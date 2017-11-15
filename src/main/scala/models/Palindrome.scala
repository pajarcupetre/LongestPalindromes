package models

case class Palindrome(value: String, startIndex: Int) {

  override def toString: String = {
    s"Text: $value, Index: $startIndex, Length: ${value.size}"
  }
}
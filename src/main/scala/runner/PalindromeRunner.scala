package runner

import utils.PalindromesUtils

object PalindromeRunner extends App {

  val input = if (args.size > 0 ) {
    args.mkString(" ")
  } else {
    "sqrrqabccbatudefggfedvwhijkllkjihxymnnmzpop"
  }
  val palindromes =  PalindromesUtils.getLongestPalindromes(input)
  palindromes.map(println(_))
}

package runner

import utils.PalindromesUtils

object PalindromeRunner extends App {

  val palindromes =  PalindromesUtils.getLongestPalindromes("sqrrqabccbatudefggfedvwhijkllkjihxymnnmzpop")
  palindromes.map(println(_))
}

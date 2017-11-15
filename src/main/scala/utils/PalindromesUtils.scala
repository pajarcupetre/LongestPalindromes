package utils

import models.Palindrome

import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

trait PalindromesUtils {

  def getPalindromes(stringInput: String): mutable.HashMap[String, Palindrome] = {
    val palindromes = mutable.HashMap[String, Palindrome]()

    for (index <- 0 to stringInput.size-1) {
      val maxSizeEven = if (index > 0 && stringInput.charAt(index) ==  stringInput.charAt(index-1)) {
        findSizePalindromeEven(stringInput, index, index-2)
      } else {
        0
      }
      val maxSizeOdd =  findSizePalindromeOdd(stringInput, index, index-1)
      val size = Math.max(maxSizeEven, maxSizeOdd)
      val startIndex = index - size/2
      val palindrome = stringInput.substring(startIndex, startIndex + size)
      palindromes.put(palindrome, Palindrome(palindrome, startIndex))

    }

    palindromes
  }

  @tailrec
  final def findSizePalindromeOdd(stringInput: String, initialIndex: Int, startIndex: Int): Int = {
    val endIndex = initialIndex + (initialIndex - startIndex)
    if (validPositionsForPalindrome(stringInput, startIndex, endIndex)) {
      findSizePalindromeOdd(stringInput, initialIndex, startIndex-1)
    } else {
      (initialIndex - startIndex) * 2 - 1
    }
  }

  @tailrec
  final def findSizePalindromeEven(stringInput: String, initialIndex: Int, startIndex: Int): Int = {
    val endIndex = initialIndex + (initialIndex - startIndex - 1)
    if (validPositionsForPalindrome(stringInput, startIndex, endIndex)) {
      findSizePalindromeEven(stringInput, initialIndex, startIndex-1)
    } else {
      (initialIndex - startIndex - 1) * 2
    }
  }

  private def validPositionsForPalindrome(stringInput: String, startIndex: Int, endIndex: Int) = {
    startIndex >= 0 && endIndex < stringInput.size && stringInput.charAt(startIndex) == stringInput.charAt(endIndex)
  }

  def getLongestPalindromes(stringInput: String): Seq[Palindrome] = {
    val palindromes = getPalindromes(stringInput)
    val palindromesSortedBySize = palindromes.toSeq.sortBy(_._1.size).reverse.map(_._2)
    val longestPalindrome = palindromesSortedBySize.head
    var longestUniquePalindromes = new ListBuffer[Option[Palindrome]]()
    longestUniquePalindromes += Some(longestPalindrome)
    var lastIndex = 1
    for (index <- 2 to Constants.NumberOfPalindromes) {
      val (palindrome, index) = getLongestDifferentPalindrome(palindromesSortedBySize.drop(lastIndex), longestUniquePalindromes)
      longestUniquePalindromes += palindrome
      lastIndex = index
    }
    longestUniquePalindromes.flatten
  }

  def getLongestDifferentPalindrome(palindromesSortedBySize: Seq[Palindrome],
                                    selectedPalindromes: Seq[Option[Palindrome]]): (Option[Palindrome], Int) = {
    for (index <- 0 to palindromesSortedBySize.size - 1) {
      val palindrome = palindromesSortedBySize(index)
      val palindromeUnique = selectedPalindromes.flatten.filter(
        p => p.value.contains(palindrome.value) && p.startIndex <= palindrome.startIndex
          && palindrome.startIndex + palindrome.value.size <= p.startIndex + p.value.size
      ).size == 0
      if (palindromeUnique) {
        return (Some(palindrome), index)
      }
    }
    return (None, palindromesSortedBySize.size)
  }

}

object PalindromesUtils extends PalindromesUtils

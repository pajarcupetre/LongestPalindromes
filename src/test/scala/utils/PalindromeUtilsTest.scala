package utils

import org.scalatest.FunSuite


class PalindromeUtilsTest extends FunSuite with PalindromesUtils {

  test("test max size of palindrome with middle at given position") {
    val expectedSizeOdd = 3
    val actualSize = findSizePalindromeOdd("mimdle", 1, 0)

    assert(expectedSizeOdd == actualSize)

    val expectedSizeEven = 6
    val actualSizeEven = findSizePalindromeEven("aaacannacdsada", 6, 4)

    assert(expectedSizeEven == actualSizeEven)
  }

  test("test palindromes from input") {
    val input = "sqrrqabccbatudefggfedvwhijkllkjihxymnnmzpop"

    val palindromes = getPalindromes(input)

    assert(palindromes.keys.toSeq.contains("hijkllkjih"))
    assert(palindromes.keys.toSeq.contains("defggfed"))
    assert(palindromes.keys.toSeq.contains("abccba"))

    // Check from small parts from palindrome(we should got only greatest one"
    assert(palindromes.keys.toSeq.contains("ijkllkji").equals(false))

  }

  test("test palindromes from input for consecutive equal character") {
    val input = "aaaaaaaabbbccc"
    val palindromes = getPalindromes(input)

    assert(palindromes.keys.toSeq.contains("aaaaaaaa"))
    // the intermediate solution should find the following string with middle on position 2
    assert(palindromes.keys.toSeq.contains("aaaa"))

    val longestPalindromes = getLongestPalindromes(input)

    assert(longestPalindromes.filter(_.value.equals("aaaaaaaa")).size > 0)
    assert(longestPalindromes.filter(_.value.equals("bbb")).size > 0)
    assert(longestPalindromes.filter(_.value.equals("ccc")).size > 0)
    assert(longestPalindromes.filter(_.value.equals("aaaa")).size == 0)

  }


}

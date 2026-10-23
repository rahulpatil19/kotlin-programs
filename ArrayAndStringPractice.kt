/**
 * Array and string practice problems (two-pointer techniques).
 * Runs as-is in the Kotlin Playground.
 *
 * Functions that change data work on a copy, so the input is never modified.
 */

fun main() {
    val text = "MAdam"
    val arrayWithZeroes = intArrayOf(0, 2, 0, 4, 5)
    val sortedForTargetSum = intArrayOf(2, 2, 5, 8, 9)
    val targetSum = 7
    val sortedWithDuplicates = intArrayOf(1, 1, 2, 2, 3)
    val firstSortedArray = intArrayOf(1, 2, 3)
    val secondSortedArray = intArrayOf(3, 5, 6)

    println("Merged sorted array: ${mergeSortedArrays(firstSortedArray, secondSortedArray).contentToString()}")
    println("Duplicates removed: ${removeDuplicates(sortedWithDuplicates).contentToString()}")
    println("Pair that adds up to $targetSum: ${findPairWithTargetSum(sortedForTargetSum, targetSum)}")
    println("Is \"$text\" a palindrome: ${isPalindromeString(text)}")
    println("Reverse of \"$text\": ${reverseString(text)}")
    println("Zeroes moved to end: ${moveZeroesToEnd(arrayWithZeroes).contentToString()}")
    println("Reversed array: ${reverseArray(arrayWithZeroes).contentToString()}")
    println("Is array a palindrome: ${isPalindromeArray(intArrayOf(1, 2, 3, 2, 1))}")
}

/** Merges two sorted arrays into one sorted array. */
fun mergeSortedArrays(first: IntArray, second: IntArray): IntArray {
    val merged = IntArray(first.size + second.size)

    var firstIndex = 0
    var secondIndex = 0
    var mergedIndex = 0

    while (firstIndex < first.size && secondIndex < second.size) {
        if (first[firstIndex] <= second[secondIndex]) {
            merged[mergedIndex++] = first[firstIndex++]
        } else {
            merged[mergedIndex++] = second[secondIndex++]
        }
    }

    while (firstIndex < first.size) merged[mergedIndex++] = first[firstIndex++]
    while (secondIndex < second.size) merged[mergedIndex++] = second[secondIndex++]

    return merged
}

/** Removes duplicates from a sorted array. */
fun removeDuplicates(sortedArray: IntArray): IntArray {
    if (sortedArray.isEmpty()) return sortedArray

    val result = sortedArray.copyOf()
    var lastUniqueIndex = 0

    for (i in 1 until result.size) {
        if (result[i] != result[lastUniqueIndex]) {
            lastUniqueIndex++
            result[lastUniqueIndex] = result[i]
        }
    }

    return result.copyOfRange(0, lastUniqueIndex + 1)
}

/**
 * Finds two numbers in a sorted array that add up to [target].
 * Returns an empty list if no such pair exists.
 */
fun findPairWithTargetSum(sortedArray: IntArray, target: Int): List<Int> {
    var left = 0
    var right = sortedArray.size - 1

    while (left < right) {
        val sum = sortedArray[left] + sortedArray[right]
        when {
            sum == target -> return listOf(sortedArray[left], sortedArray[right])
            sum > target -> right--
            else -> left++
        }
    }

    return emptyList()
}

/** Moves all zeroes to the end while keeping the order of the other numbers. */
fun moveZeroesToEnd(array: IntArray): IntArray {
    val result = array.copyOf()
    var nextNonZeroIndex = 0

    for (i in result.indices) {
        if (result[i] != 0) {
            if (i != nextNonZeroIndex) {
                val temp = result[i]
                result[i] = result[nextNonZeroIndex]
                result[nextNonZeroIndex] = temp
            }
            nextNonZeroIndex++
        }
    }

    return result
}

/** Reverses a string using two pointers. */
fun reverseString(text: String): String {
    val chars = text.toCharArray()
    var left = 0
    var right = chars.size - 1

    while (left < right) {
        val temp = chars[left]
        chars[left] = chars[right]
        chars[right] = temp
        left++
        right--
    }

    return String(chars)
}

/** Reverses an array using two pointers. */
fun reverseArray(array: IntArray): IntArray {
    val result = array.copyOf()
    var left = 0
    var right = result.size - 1

    while (left < right) {
        val temp = result[left]
        result[left] = result[right]
        result[right] = temp
        left++
        right--
    }

    return result
}

/** Checks whether an array reads the same forwards and backwards. */
fun isPalindromeArray(array: IntArray): Boolean {
    var left = 0
    var right = array.size - 1

    while (left < right) {
        if (array[left] != array[right]) return false
        left++
        right--
    }

    return true
}

/** Checks whether a string is a palindrome, ignoring case, spaces and punctuation. */
fun isPalindromeString(text: String): Boolean {
    val cleaned = text.lowercase().filter { it.isLetterOrDigit() }

    var left = 0
    var right = cleaned.length - 1

    while (left < right) {
        if (cleaned[left] != cleaned[right]) return false
        left++
        right--
    }

    return true
}

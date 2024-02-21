class Sort() {

    private fun Int.toDigits(): List<Int> = toString().map { it.toString().toInt() } // from stack overflow
    fun radixSort(numList: MutableList<Int>):MutableList<MutableList<Int>> {
        var mainList: MutableList<MutableList<Int>> = mutableListOf()
        var baseTenList: MutableList<MutableList<MutableList<Int>>> = mutableListOf(mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf())
        for (number in numList) {
            val digitList = number.toDigits().toMutableList()
            mainList.add(digitList)
        }

        //First sort
        for (digit in mainList[0].size-1 downTo 0) {
            println("doing index $digit")
            //println(digit)
            println("main list: $mainList")
            for (index in 0..<mainList.size) {
                    val valAtPlace = mainList[index][digit]
                    baseTenList[valAtPlace].add(mainList[index])
            }
            mainList = baseTenList.flatten().toMutableList()
            baseTenList = mutableListOf(mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf())
            println("main list: $mainList")
        }
        return mainList
    }

    fun selectionSort(numList: MutableList<Int>) {
        var unsortedLowerBound = 0
        while (unsortedLowerBound < numList.size - 1) {
            val slice = numList.slice(unsortedLowerBound..<numList.size)
            println("lower bound: $unsortedLowerBound, slice: $slice")
            println("current list is $numList")
            val minimum = slice.indexOf(slice.min()) + unsortedLowerBound
            println("list min index: $minimum")
            val temp = numList[unsortedLowerBound]
            numList[unsortedLowerBound] = numList[minimum]
            numList[minimum] = temp
            println("current list is $numList")
            unsortedLowerBound+=1
        }
    }

    fun quickSort(numList: MutableList<Int>):MutableList<Int> {
        if (numList.size <= 1) {
            return numList
        }
        val pivot = numList[numList.size - 1]
        var rightArray = mutableListOf<Int>()
        var leftArray = mutableListOf<Int>()
        println(numList.size)
        for (index in numList.size-2 downTo 0) {
            println(index)
            if (pivot < numList[index]) {
                rightArray.add(numList[index])
            } else if (pivot >= numList[index]) {
                leftArray.add(numList[index])
            }
        }
        println("left array: $leftArray")
        println("right array: $rightArray")
        if (leftArray.isNotEmpty()) {
            leftArray = quickSort(leftArray)
        }
        if (rightArray.isNotEmpty()) {
            rightArray = quickSort(rightArray)
        }
        println("left array: $leftArray")
        println("right array: $rightArray")
        var finalList = leftArray.plus(mutableListOf(pivot)).toMutableList()
        finalList = finalList.plus(rightArray).toMutableList()
        return finalList
        }

    fun insertionSort (numList: MutableList<Int>):MutableList<Int> {

        for (index in 0..numList.size - 1) {
            val key = numList[index]
            var left = index - 1
            while (left >= 0 && numList[left] > key) {
                numList[left + 1] = numList[left]
                left = left - 1
            }
            numList[left + 1] = key
        }
        return numList
    }

}
//what if partition is not at the end
//recursive version, how to do not recursive version / how to swap, how to implement min heap and then I'll do heap sort after that

fun main() {
    val listOfNumbers = mutableListOf<Int>()
    listOfNumbers.add(409)
    listOfNumbers.add(829)
    listOfNumbers.add(371)
    listOfNumbers.add(501)
    listOfNumbers.add(700)
    listOfNumbers.add(740)
    listOfNumbers.add(741)
    val sort = Sort()
    val sortedNumbersRadix = sort.radixSort(listOfNumbers)
    println(sortedNumbersRadix)
    //val nums = sort.insertionSort(listOfNumbers)
    //println(nums)
}
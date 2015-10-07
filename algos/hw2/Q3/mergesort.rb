class MergeSort

  # self. before the class just makes this a class method
  def self.merge_sort(arr)
    # base case: return array if size is less than or equal to 1
    return arr if arr.size <= 1

    # split array into two halves
    left = merge_sort(arr[0, arr.size / 2])
    right = merge_sort(arr[arr.size / 2, arr.size])

    # merge two sub arrays
    merge(left, right)
  end


  def self.merge(left, right)
    sorted = []

    # while there are any elements in the left or right sub arrays
    while left.any? && right.any?
      # this is a ternary operation (condensed if/else block)
      left[0] <= right[0] ? sorted << left.shift : sorted << right.shift
    end

    sorted.concat(left).concat(right)
  end

end

# test cases
p MergeSort.merge_sort([4, 3, 1, 2])
p MergeSort.merge_sort([4, 3, 1, 2, 6, 5, 9, 8, 7])

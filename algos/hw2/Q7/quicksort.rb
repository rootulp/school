require "benchmark"

class QuickSort

  attr_accessor :arr
  def initialize(arr)
    @arr = arr
  end

  def sort(first_i = 0, last_i = arr.size - 2)
    # Stop if already sorted
    return if first_i > last_i

    # Declare index var's for easier ref
    insert_i = first_i
    pivot_i = last_i + 1

    # Partition
    (first_i..last_i).each do |i|
      if arr[i] < arr[pivot_i]
        # Move smaller elements to left
        swap(i, insert_i)
        insert_i += 1
      end
    end

    # Move pivot inbetween left(smaller elements) & right(larger elements)
    swap(pivot_i, insert_i)

    # Recursively sort left and ride sides
    sort(first_i, insert_i - 2)
    sort(insert_i + 1, last_i)
  end

  def print_arr
    puts arr.join(" ")
  end

  def swap(a, b)
    arr[a], arr[b] = arr[b], arr[a]
  end

end

# t1 = QuickSort.new([4, 3, 1, 2])
# t1.sort
# t1.print_arr
#
# t2 = QuickSort.new([4, 3, 1, 2, 6, 5, 9, 8, 7])
# t2.sort
# t2.print_arr

# Time results - Uncomment following lines to run benchmarks

Benchmark.bm do |x|
  x.report("Random: 1") { QuickSort.new(Array.new(1).map! { rand() }).sort }
  x.report("Random: 2") { QuickSort.new(Array.new(2).map! { rand() }).sort }
  x.report("Random: 10") { QuickSort.new(Array.new(10).map! { rand() }).sort }
  x.report("Random: 10^2") { QuickSort.new(Array.new(10**2).map! { rand() }).sort }
  x.report("Random: 10^3") { QuickSort.new(Array.new(10**3).map! { rand() }).sort }
end

Benchmark.bm do |x|
  x.report("Reverse: 2..1") { QuickSort.new((1..2).to_a.reverse).sort }
  x.report("Reverse: 10..1") { QuickSort.new((1..10).to_a.reverse).sort }
  x.report("Reverse: 10^2..1") { QuickSort.new((1..10**2).to_a.reverse).sort }
  x.report("Reverse: 10^3..1") { QuickSort.new((1..10**3).to_a.reverse).sort }
end

Benchmark.bm do |x|
  x.report("All 1's: 1") { QuickSort.new(Array.new(1, 1)).sort }
  x.report("All 1's: 2") { QuickSort.new(Array.new(2, 1)).sort }
  x.report("All 1's: 10") { QuickSort.new(Array.new(10, 1)).sort }
  x.report("All 1's: 10^2") { QuickSort.new(Array.new(10**2, 1)).sort }
  x.report("All 1's: 10^3") { QuickSort.new(Array.new(10**3, 1)).sort }
end


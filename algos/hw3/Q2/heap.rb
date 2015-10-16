# Max Heap Implementation
require "benchmark"

class Heap
  attr_accessor :arr
  def initialize
    @arr = []
  end

  def add(val)
    arr << val
    fix_up(arr.size - 1)
  end

  def pop_min
    swap(0, arr.size - 1)
    max = arr.pop
    fix_down(0)
    max
  end

  def fix_up(i)
    if arr[i] < arr[parent(i)]
      swap(i, parent(i))
      fix_up(parent(i))
    end
  end

  def fix_down(i)
    if valid(left(i))
      child = left(i)
      if valid(right(i)) && arr[left(i)] > arr[right(i)]
        child = right(i)
      end

      if arr[i] > arr[child]
        swap(child, i)
        fix_down(child)
      end
    end
  end

  def heapify(input)
    while input.any?
      add(input.pop)
    end
  end

  private

  def valid(i)
    i < arr.size - 1
  end

  def left(i)
    2 * i + 1
  end

  def right(i)
    2 * i + 2
  end

  def parent(i)
    i / 2
  end

  def swap(a, b)
    arr[a], arr[b] = arr[b], arr[a]
  end

end

# Time results - Uncomment following lines to run benchmarks
puts "i"
Benchmark.bm do |x|
  x.report("Random: 1") { Heap.new.heapify(Array.new(1).map! { rand() }) }
  x.report("Random: 2") { Heap.new.heapify(Array.new(2).map! { rand() }) }
  x.report("Random: 10") { Heap.new.heapify(Array.new(10).map! { rand() }) }
  x.report("Random: 10^2") { Heap.new.heapify(Array.new(10**2).map! { rand() }) }
  x.report("Random: 10^3") { Heap.new.heapify(Array.new(10**3).map! { rand() }) }
end

puts "ii"
Benchmark.bm do |x|
  x.report("Reverse: 2..1") { Heap.new.heapify(Array.new((1..2).to_a.reverse)) }
  x.report("Reverse: 10..1") { Heap.new.heapify(Array.new((1..10).to_a.reverse)) }
  x.report("Reverse: 10^2..1") { Heap.new.heapify(Array.new((1..10**2).to_a.reverse)) }
  x.report("Reverse: 10^3..1") { Heap.new.heapify(Array.new((1..10**3).to_a.reverse)) }
end

puts "iii"
Benchmark.bm do |x|
  x.report("Random Add: 2") do
    heap = Heap.new
    2.times { heap.add(rand()) }
  end
  x.report("Random Add: 10") do
    heap = Heap.new
    10.times { heap.add(rand()) }
  end
  x.report("Random Add: 100") do
    heap = Heap.new
    10**2.times { heap.add(rand()) }
  end
  x.report("Random Add: 1000") do
    heap = Heap.new
    10**3.times { heap.add(rand()) }
  end
end

puts "iv"
Benchmark.bm do |x|
  x.report("Reverse Add: 2..1") do
    heap = Heap.new
    2.downto(1) { |i| heap.add(i) }
  end
  x.report("Reverse Add: 10..1") do
    heap = Heap.new
    10.downto(1) { |i| heap.add(i) }
  end
  x.report("Reverse Add: 10^2..1") do
    heap = Heap.new
    10**2.downto(1) { |i| heap.add(i) }
  end
  x.report("Reverse Add: 10^3..1") do
    heap = Heap.new
    10**3.downto(1) { |i| heap.add(i) }
  end
end

heap1 = Heap.new
2.times { heap1.add(rand()) }
heap2 = Heap.new
10.times { heap2.add(rand()) }
heap3 = Heap.new
(10**2).times { heap3.add(rand()) }
heap4 = Heap.new
(10**3).times { heap4.add(rand()) }

puts "v"
Benchmark.bm do |x|
  x.report("Remove 2") { 2.times { heap1.pop_min } }
  x.report("Remove 10") { 10.times { heap2.pop_min } }
  x.report("Remove 10^2") { 10**2.times { heap3.pop_min } }
  x.report("Remove 10^3") { 10**3.times { heap4.pop_min } }
end

heap1 = Heap.new
heap1.heapify(Array.new((1..2).to_a.reverse))
heap2 = Heap.new
heap2.heapify(Array.new((1..10).to_a.reverse))
heap3 = Heap.new
heap3.heapify(Array.new((1..10**2).to_a.reverse))
heap4 = Heap.new
heap4.heapify(Array.new((1..10**3).to_a.reverse))

puts "vi"
Benchmark.bm do |x|
  x.report("Remove 2") { 2.times { heap1.pop_min } }
  x.report("Remove 10") { 10.times { heap2.pop_min } }
  x.report("Remove 10^2") { 10**2.times { heap3.pop_min } }
  x.report("Remove 10^3") { 10**3.times { heap4.pop_min } }
end

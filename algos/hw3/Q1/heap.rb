# Max Heap Implementation
# TO-DO: Clean up fix_down function
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

test1 = Heap.new
8.downto(1) do |i|
  test1.add(i)
end
p test1.arr

test2 = Heap.new
test2.heapify([8,6,4,2,1,3,5,7])
p test2.arr

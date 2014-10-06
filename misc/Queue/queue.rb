class Queue
  attr_reader :max_size

  def initialize(size)
    @max_size = size
    @array_queue = []
  end
  
  def enqueue(num)
    if self.size >= @max_size
      raise "Queue overflow"
    else
      @array_queue.unshift(num)
    end
  end

  def dequeue
    if self.size <= 0
      raise "Queue underflow"
    else
      @array_queue.pop
    end
  end

  def size
    @array_queue.size
  end

  def to_s
    @array_queue.inspect
  end
end

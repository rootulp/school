# Rootul Patel
# Algos HW1 Q1

# require benchmark to time results
require "benchmark"

# wrapped CountDown algo in class
class CountDown

  # attr_reader just defines a getter for variables (n and l)
  # attr_accessor defines a getter and setter for variables (clock)
  attr_reader :n, :l
  attr_accessor :clock

  # initialize our CountDown instance with n and l
  # also create an array of length l and pre-populate it with the val n
  def initialize(n, l)
    @n = n
    @l = l
    @clock = Array.new(l, n)
  end

  # start begins the countdown
  # it runs a while loop until all values in our clock array are zero
  # during each iteration it prints the clock and then decrements the clock
  def start
    until clock.all? { |val| val == 0 }
      print_clock
      decrement_clock
    end
  end

  # decrements the last value in the array
  # then calls carry over
  def decrement_clock
    clock[-1] -= 1
    carry_over
  end

  # carry over finds the index of the right-most -1 and resets that value to n
  # also decrements the value to the left of that index
  def carry_over
    while clock.any? { |val| val < 0 }
      indx = clock.rindex(-1)
      clock[indx - 1] -= 1
      clock[indx] = n
    end
  end

  # print clock converts our clock array to a string and prints it
  def print_clock
    puts @clock.join(', ')
  end

end

CountDown.new(13, 3).start

# Time results - Uncomment following lines to run benchmarks
# Benchmark.bm do |x|
#   x.report("n = 27, l = 2:") { CountDown.new(27, 2).start }
#   x.report("n = 27, l = 3:") { CountDown.new(27, 3).start }
#   x.report("n = 27, l = 4:") { CountDown.new(27, 4).start }
#   x.report("n = 27, l = 5:") { CountDown.new(27, 5).start }
# end

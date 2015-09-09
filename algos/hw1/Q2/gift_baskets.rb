# Rootul Patel
# Algos HW1 Q2

# wrapped GiftBaskets algo in class
class GiftBaskets

  # attr_reader defines getters for k and set
  attr_reader :k, :set

  # initialize our GiftBaskets instance with k and set
  def initialize(k, set)
    @k = k
    @set = set
  end

  # create a temp array of size k and pass it to find_combinations
  def combinations
    combination = Array.new(k)
    find_combinations(combination, 0, 0, set.size)
  end

  # find_combinations recursively adds an element to combination and
  # calls find_combinations on the remaining set
  def find_combinations(combination, index, left, right)
    if index == k
      print_combination(combination)
    else
      (left..right).each do |i|
        next if right - i < k - index
        combination[index] = set[i]
        find_combinations(combination, index + 1, i + 1, right)
      end
    end
  end

  # print_combination prints our combination array
  def print_combination(combination)
    puts combination.join(', ')
  end

end

GiftBaskets.new(2, ['car', 'ipod', 'orange', 'hotel']).combinations

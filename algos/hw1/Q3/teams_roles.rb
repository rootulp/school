# Rootul Patel
# Algos HW1 Q3

# wrapped TeamsRoles algo in class
class TeamsRoles

  # attr_reader defines getters for k and set
  attr_reader :k, :set

  # initialize our TeamsRoles instance with k and set
  def initialize(k, set)
    @k = k
    @set = set
  end

  # call find_permutations with an empty permutation array, set, and 0
  def permutations
    find_permutations([], set, 0)
  end

  # recursively calls find_permutations after appending all possible
  # vals to current permutation and passing subset with elem removed
  def find_permutations(permutation, subset, index)
    if index == k
      print_permutation(permutation)
    else
      subset.each do |elem|
        permutation[index] = elem
        new_subset = subset.clone
        new_subset.delete(elem)
        find_permutations(permutation, new_subset, index + 1)
      end
    end
  end

  # prints our permutation
  def print_permutation(permutation)
    puts permutation.join(', ')
  end

end

TeamsRoles.new(2, ['John', 'Mary', 'Luke', 'Ann', 'Joe']).permutations

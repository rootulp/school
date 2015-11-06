class LineBreaking

  attr_accessor :words, :length, :line_costs, :memo
  def initialize(words, length)
    # Take words and split on spaces
    # Then add a space before each word
    @words = words.split(' ').map { |x| ' ' + x }
    @length = length
    @line_costs = {}
    @memo = {}
    calculate_costs
  end

  def solve
    minimize_rest(0)
  end

  def minimize_rest(next_word_index)
    return 0 if next_word_index >= words.size - 1
    # return memo[next_word_index] if memo.include?(next_word_index)

    line_costs.select do |k, v|
      k.first == next_word_index
    end.min_by do |k, v|
      p words.size
      p v
      p k.last
      p k
      v + minimize_rest(k.last + 1)
    end

    #   .min_by do |x|
    #   x.last + minimize_rest(x[1] + 1)
    # end
    # min_rest = min.last + minimize_rest(min[1] + 1)
    # memo[next_word_index] = min_rest
    # p min_rest
    # p memo
    # min_rest
  end

  def calculate_costs
    words.each_with_index do |first_word, first_i|
      last_i = first_i
      while last_i < words.size && linear_cost(words[first_i..last_i]) >= 0
        line_costs[[first_i, last_i]] = linear_cost(words[first_i..last_i])
        # puts "#{first_i},#{last_i},#{linear_cost(words[first_i..last_i])}]"
        last_i += 1
      end
    end
  end

  def linear_cost(subwords)
    cost(subwords)
  end

  def square_cost(subwords)
    cost(subwords) ** 2
  end

  def square_root_cost(subwords)
    cost(subwords) ** 0.5
  end

  def cost(subwords)
    length - subwords.join.size
  end

  # def test_case
  #   linear_cost(words[1..15])
  # end

end


WORDS =
'''
Some kinds of writing work best in long paragraphs, and
others move through many short paragraphs. Newspaper
reporters usually write in very short paragraphs. In
addition, their examples and explanations are not always
tightly tied in related clusters. This style of writing is
addressed to readers who are skimming and looking for the
main points in the first few inches of print, so reporters
dont develop each idea fully in clear sequence. Information
in newspaper articles sometimes has to be reorganized to
make a standard essay.
'''

LENGTH = 70

test_case = LineBreaking.new(WORDS, LENGTH)
# p test_case.memo
p test_case.solve

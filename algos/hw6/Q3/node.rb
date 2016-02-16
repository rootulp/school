require 'set'

class Node

  attr_accessor :name, :neighbors
  def initialize(name)
    @name = name
    @neighbors = Set.new
  end

  def to_s
    @name
  end

end

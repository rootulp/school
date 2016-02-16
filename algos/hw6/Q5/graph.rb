require 'set'

require_relative 'node'

class Graph

  attr_accessor :nodes, :edges
  def initialize
    @nodes = Set.new
    @edges = Set.new
  end

  def add_node(node)
    @nodes << node
  end

  def add_edge(node1, node2, weight)
    node1.neighbors << node2
    node2.neighbors << node1
    @edges << [weight, Set.new(node1, node2)]
  end

end

require 'rubygems'
require 'algorithms'

require_relative 'graph'
require_relative 'node'

class Prim

  attr_accessor :graph, :heap
  def initialize(graph)
    @graph = graph
    @heap = Containers::MinHeap.new(graph.edges)
  end

end

# g = Graph.new
# p = Prim.new(

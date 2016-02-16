require 'set'

require_relative 'node'

class Graph

  attr_accessor :nodes
  def initialize
    @nodes = Set.new
  end

  def add_node(node)
    @nodes << node
  end

  def add_edge(node1, node2)
    node1.neighbors << node2
    node2.neighbors << node1
  end

  def self.generate_graph(n)
    g = Graph.new

    # Add Nodes
    (1..n).each do |i|
      g.add_node(Node.new(i))
    end

    # Add Edges
    g.nodes.to_a.combination(2).each do |pair|
      g.add_edge(*pair) if rand() < 5.0 / n
    end

    return g
  end

  def self.generate_particular_graph
    g = Graph.new

    # Add Nodes
    nodes = []

    (0..16).each do |i|
      nodes << Node.new(i)
    end

    nodes.each do |node|
      next if node.name == 0
      g.add_node(node)
    end

    # Add Edges
    g.add_edge(nodes[1], nodes[2])
    g.add_edge(nodes[1], nodes[3])
    g.add_edge(nodes[1], nodes[4])
    g.add_edge(nodes[2], nodes[11])
    g.add_edge(nodes[2], nodes[5])
    g.add_edge(nodes[3], nodes[5])
    g.add_edge(nodes[3], nodes[7])
    g.add_edge(nodes[3], nodes[12])
    g.add_edge(nodes[4], nodes[7])
    g.add_edge(nodes[4], nodes[11])
    g.add_edge(nodes[4], nodes[13])
    g.add_edge(nodes[5], nodes[7])
    g.add_edge(nodes[5], nodes[8])
    g.add_edge(nodes[5], nodes[6])
    g.add_edge(nodes[7], nodes[10])
    g.add_edge(nodes[7], nodes[8])
    g.add_edge(nodes[8], nodes[10])
    g.add_edge(nodes[8], nodes[9])
    g.add_edge(nodes[10], nodes[11])
    g.add_edge(nodes[12], nodes[15])
    g.add_edge(nodes[15], nodes[16])

    return g
  end

end

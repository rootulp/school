require_relative 'graph'
require_relative 'node'

class DFS

  attr_accessor :graph, :start, :visited, :edges
  def initialize(graph, start)
    @graph = graph
    @start = start
    @visited = []
    @edges = []
    dfs(start)
  end

  def dfs(node)
    @visited << node

    node.neighbors.each do |neighbor|
      next if @visited.include?(neighbor)
      dfs(neighbor)
      @edges << [node, neighbor]
    end

  end

  def print_visited
    puts 'Visited order:'
    puts @visited.map { |node| node.to_s }.join(' -> ')
  end

  def print_edges
    puts 'Edges found:'
    @edges.each { |node1, node2| puts "#{node1.to_s} : #{node2.to_s}" }
  end

end

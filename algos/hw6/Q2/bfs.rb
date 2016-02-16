require_relative 'graph'
require_relative 'node'

class BFS

  attr_accessor :graph, :start, :visited, :edges
  def initialize(graph, start)
    @graph = graph
    @start = start
    @visited = []
    @edges = []
    bfs(start)
  end

  def bfs(node)
    queue = [node]
    @visited << node

    while queue.any?
      curr = queue.shift
      curr.neighbors.each do |neighbor|
        next if @visited.include?(neighbor)
        queue << neighbor
        @visited << neighbor
        @edges << [curr, neighbor]
      end
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

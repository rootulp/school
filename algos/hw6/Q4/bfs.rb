require 'benchmark'

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

# Part I
Benchmark.bm do |x|

  total = []
  (0..10).each do |i|
    g = Graph.generate_graph(10)
    total << x.report("N: 10") { BFS.new(g, g.nodes.first) }
  end
  puts "Average (N: 10) #{((total.reduce(:+) / 10.0)).real}"

  total = []
  (0..10).each do |i|
    g = Graph.generate_graph(100)
    total << x.report("N: 100") { BFS.new(g, g.nodes.first) }
  end
  puts "Average (N: 100) #{((total.reduce(:+) / 10.0)).real}"

  total = []
  (0..10).each do |i|
    g = Graph.generate_graph(1000)
    total << x.report("N: 1000") { BFS.new(g, g.nodes.first) }
  end
  puts "Average (N: 1000) #{((total.reduce(:+) / 10.0)).real}"

end

# Part II
g = Graph.generate_particular_graph
bfs = BFS.new(g, g.nodes.first)
bfs.print_visited
bfs.print_edges

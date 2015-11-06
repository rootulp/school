require "benchmark"

class Vertex

  attr_accessor :num, :neighbors, :distance, :prev
  def initialize(num, neighbors = [] , distance = Float::INFINITY, prev = nil)
    @num = num
    @neighbors = neighbors
    @distance = distance
    @prev = prev
  end

end

class Graph

  attr_accessor :vertices, :edges, :start, :stop
  def initialize(start, stop)
    @start = start
    @stop = stop
    @edges = {}
    @vertices = Hash.new { |k, v| k[v] = Vertex.new(v) }
    vertices[start].distance = 0
  end

  def add_edge(v1, v2, distance)
    vertices[v1].neighbors << v2
    vertices[v2].neighbors << v1
    edges[[v1, v2]] = distance
    edges[[v2, v1]] = distance
  end

  def delete_edge(v1, v2)
    vertices[v1].neighbors.delete(v2)
    vertices[v2].neighbors.delete(v1)
    edges.delete([v1, v2])
    edges.delete([v2, v1])
  end

  def solve
    all_vertices = vertices.values
    until all_vertices.empty?
      nearest_vertex = all_vertices.min_by { |vertex| vertex.distance }
      break if nearest_vertex.distance == Float::INFINITY # Break if unreachable
      all_vertices.delete(nearest_vertex) # Mark as complete
      nearest_vertex.neighbors.each do |neighbor|
        neighbor_vertex = vertices[neighbor]
        if all_vertices.include?(neighbor_vertex)
          alt = nearest_vertex.distance + edges[[nearest_vertex.num, neighbor]]
          if alt < neighbor_vertex.distance # Found shorter route
            neighbor_vertex.distance = alt
            neighbor_vertex.prev = nearest_vertex.num
          end
        end
      end
    end
  end

  def shortest_distance
    vertices[stop].distance
  end

  def shortest_path
    next_node = stop
    path = []

    while next_node
      path.unshift(next_node)
      next_node = vertices[next_node].prev
    end

    path.join(' --> ')
  end

end

# Part ii
puts 'Part ii'

def new_graph(n)
  graph = Graph.new(rand(n), rand(n))

  n.times do |num|
    (0..n).each do |curr|
      graph.add_edge(num, curr, rand)
    end
  end

  probability = 1 - 5.0 / n

  graph.edges.keys.each do |edge|
    graph.delete_edge(edge.first, edge.last) if rand < probability
  end

  graph
end

g1 = new_graph(10)
g2 = new_graph(10)
g3 = new_graph(10)
g4 = new_graph(100)
g5 = new_graph(100)
g6 = new_graph(100)

Benchmark.bm do |x|
  y1 = x.report("N: 10") { g1.solve() }
  y2 = x.report("N: 10") { g2.solve() }
  y3 = x.report("N: 10") { g3.solve() }
  puts 'N: 10 Averaged three times / N^2:'
  puts (y1 + y2 + y3 / 3.0) / 10 ** 2

  y4 = x.report("N: 100") { g4.solve() }
  y5 = x.report("N: 100") { g5.solve() }
  y6 = x.report("N: 100") { g6.solve() }
  puts 'N: 100 Averaged three times / N^2:'
  puts (y4 + y5 + y6 / 3.0) / 100 ** 2
end

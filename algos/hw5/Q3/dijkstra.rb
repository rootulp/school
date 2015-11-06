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

# Part i
puts "Part i:"
graph = Graph.new(2, 8)
graph.add_edge(1, 2, 0.416815)
graph.add_edge(1, 5, 0.179091)
graph.add_edge(1, 6, 0.43717)
graph.add_edge(1, 9, 0.242133)
graph.add_edge(1, 10, 0.445668)
graph.add_edge(2, 5, 0.260901)
graph.add_edge(2, 9, 0.451311)
graph.add_edge(3, 4, 0.179344)
graph.add_edge(5, 9, 0.214976)
graph.add_edge(6, 7, 0.150886)
graph.add_edge(6, 10, 0.178003)
graph.add_edge(7, 10, 0.0871529)
graph.add_edge(8, 10, 0.486135)
graph.solve
puts graph.shortest_path
puts graph.shortest_distance

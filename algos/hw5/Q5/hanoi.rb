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
# hanoi = Graph.new('aba', 'cab')
hanoi = Graph.new('baa', 'aac')
hanoi.add_edge('aaa', 'baa', 1)
hanoi.add_edge('aaa', 'caa', 1)
hanoi.add_edge('baa', 'caa', 1)
hanoi.add_edge('baa', 'bca', 1)
hanoi.add_edge('bca', 'cca', 1)
hanoi.add_edge('bca', 'aca', 1)
hanoi.add_edge('aca', 'aba', 1)
hanoi.add_edge('caa', 'cba', 1)
hanoi.add_edge('cba', 'aba', 1)
hanoi.add_edge('cba', 'bba', 1)
hanoi.add_edge('aca', 'cca', 1)
hanoi.add_edge('aba', 'bba', 1)

hanoi.add_edge('cca', 'ccb', 1)

hanoi.add_edge('ccb', 'acb', 1)
hanoi.add_edge('ccb', 'bcb', 1)
hanoi.add_edge('acb', 'bcb', 1)
hanoi.add_edge('acb', 'abb', 1)
hanoi.add_edge('abb', 'bbb', 1)
hanoi.add_edge('abb', 'cbb', 1)
hanoi.add_edge('bbb', 'cbb', 1)
hanoi.add_edge('bcb', 'bab', 1)
hanoi.add_edge('bab', 'cab', 1)
hanoi.add_edge('bab', 'aab', 1)
hanoi.add_edge('cbb', 'cab', 1)
hanoi.add_edge('cab', 'aab', 1)

hanoi.add_edge('aab', 'aac', 1)

hanoi.add_edge('bba', 'bbc', 1)

hanoi.add_edge('bbc', 'cbc', 1)
hanoi.add_edge('bbc', 'abc', 1)
hanoi.add_edge('cbc', 'abc', 1)
hanoi.add_edge('cbc', 'cac', 1)
hanoi.add_edge('abc', 'acc', 1)
hanoi.add_edge('cac', 'aac', 1)
hanoi.add_edge('cac', 'bac', 1)
hanoi.add_edge('aac', 'bac', 1)
hanoi.add_edge('acc', 'bcc', 1)
hanoi.add_edge('acc', 'ccc', 1)
hanoi.add_edge('bcc', 'bcc', 1)
hanoi.add_edge('bcc', 'ccc', 1)

hanoi.solve
puts hanoi.shortest_path
puts hanoi.shortest_distance

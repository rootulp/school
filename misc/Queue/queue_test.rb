require_relative 'queue'
 
describe Queue do
  
  before :each do
    @queue = Queue.new(3)
    @queue.enqueue(1)
  end

  it "takes one parameter and returns a Queue object" do
    expect(@queue).to be_an_instance_of Queue
  end

  it "sets max size correctly" do
    expect(@queue.max_size).to be 3
  end

  it "increases size by 1 for enqueue" do
    @queue.enqueue(2)
    expect(@queue.size).to be 2
  end

  it "decreases size by 1 for dequeue" do
    @queue.dequeue()
    expect(@queue.size).to be 0
  end

  it "handles overflow with an exeption" do
    @queue.enqueue(2)
    @queue.enqueue(2)
    expect {@queue.enqueue(2)}.to raise_error
  end

  it "handles underflow with an exeption" do
    @queue.dequeue
    expect {@queue.dequeue}.to raise_error
  end

end

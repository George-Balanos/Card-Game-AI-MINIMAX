import java.util.ArrayList;

class MinimaxQueue{
	private ArrayList<State> queue;
	
	public MinimaxQueue(){
		queue = new ArrayList<State>();
	}
	
	public ArrayList<State> getQueue(){
		return this.queue;
	}
	
	public void enqueue(State state){
		queue.add(state);
	}
	
	public State dequeue(){
		return queue.remove(0);
	}
	
	public boolean isEmpty(){
		if(queue.size() > 0){
			return false;
		}
		
		return true;
		
	}
	
}
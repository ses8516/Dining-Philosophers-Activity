public class Philosopher extends Thread{
	private int id;
	private Fork left;
	private Fork right;
	private boolean rHanded;
	private int nTimes;
	private long thinkMillis;
	private long eatMillis;
	
	public Philosopher(int id, Fork left, Fork right, boolean rHanded, int nTimes, long thinkMillis, long eatMillis)
	{
		this.id = id;
		this.left = left;
		this.right = right;
		this.rHanded = rHanded;
		this.nTimes = nTimes;
		this.thinkMillis = thinkMillis;
		this.eatMillis = eatMillis;
	}

	public void run(){
		if (nTimes == 0){
			while(true){
				cycle();
			}
		}else{
			for (int i = 0; i < nTimes; i++){
				cycle();
			}
		}
	}
	
	private void cycle(){
		int thinkTime = (int)(Math.random() * ((thinkMillis + 1)));
		
		System.out.println("Philosopher" + this.id + "thinks for " + thinkTime + " time units");
		try {
			sleep(thinkTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (rHanded){
			goForRight();
			yield();
			goForLeft();
			yield();
		}else{
			goForLeft();
			yield();
			goForRight();
			yield();
		}
		
		int eatTime = (int)(Math.random() * ((eatMillis + 1)));
		System.out.println("Philosopher " + this.id + " eats for " + eatTime + " time unts");
		try {
			sleep(eatTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

		right.release();
		System.out.println("Philosopher " + this.id + " releases right fork");
		left.release();
		System.out.println("Philosopher " + this.id + " releases left fork");
	}
	
	private void goForRight(){
		System.out.println("Philosopher " + this.id + " goes for " + "right fork");
		right.acquire();
		System.out.println("Philosopher " + this.id + " has right fork");
	}
	
	private void goForLeft(){
		System.out.println("Philosopher " + this.id + " goes for " + "left fork");
		left.acquire();
		System.out.println("Philosopher " + this.id + " has left fork");
	}
	
	
}
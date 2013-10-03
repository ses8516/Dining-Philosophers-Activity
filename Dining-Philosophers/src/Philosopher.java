/**
 * The Philosopher class is a thread that cycles between thinking and eating.
 * In order to eat a philosopher must pick up their left and right forks.
 */
public class Philosopher extends Thread{
	private int id;
	private Fork left;
	private Fork right;
	private boolean rHanded;
	private int nTimes;
	private long thinkMillis;
	private long eatMillis;
	
	/**
	 * @param id - Philosopher id
	 * @param left - Left Fork
	 * @param right - Right Fork
	 * @param rHanded - is right handed or not
	 * @param nTimes - Times to run through cycle
	 * @param thinkMillis - Max time to think
	 * @param eatMillis - Max time to eat
	 */
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

	/**
	 * Run through a number of cycles equal to nTimes or unlimited if
	 * nTimes is zero
	 */
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
	
	/*
	 * Run cycle of thinking, grabbing forks, eating and releasing forks
	 */
	private void cycle(){
		//Think
		int thinkTime = (int)(Math.random() * ((thinkMillis + 1)));
		System.out.println("Philosopher " + this.id + " thinks for " + thinkTime + " time units");
		try {
			sleep(thinkTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Go for forks - (Right first if right handed else left first)
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
		
		//Eat
		int eatTime = (int)(Math.random() * ((eatMillis + 1)));
		System.out.println("Philosopher " + this.id + " eats for " + eatTime + " time unts");
		try {
			sleep(eatTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Release the forks
		right.release();
		System.out.println("Philosopher " + this.id + " releases right fork");
		left.release();
		System.out.println("Philosopher " + this.id + " releases left fork");
	}
	
	/*
	 * Attempt to acquire the right fork
	 */
	private void goForRight(){
		System.out.println("Philosopher " + this.id + " goes for " + "right fork");
		right.acquire();
		System.out.println("Philosopher " + this.id + " has right fork");
	}
	
	/*
	 * Attempt to acquire left fork
	 */
	private void goForLeft(){
		System.out.println("Philosopher " + this.id + " goes for " + "left fork");
		left.acquire();
		System.out.println("Philosopher " + this.id + " has left fork");
	}
	
	
}
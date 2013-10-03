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
		
		int time = 0;
		if (nTimes == 0){
		
		
		}
		else{
			for (int i = 0; i < nTimes; i++){
				time =(int)(Math.random() * ((thinkMillis + 1)));
				
				System.out.println("Philosopher" + this.id + "thinks for " + time + " time units");
				try {
					sleep(time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("Philosopher" + this.id + "goes for " + "right fork");
			}
		
		}
	}
}
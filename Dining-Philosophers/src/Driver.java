import java.util.ArrayList;

/**
 * The Driver class is run in the following form:
 * java Driver [-l] [np] [nt] [tm] [em]
 * [-1] is a flag and if included even philosophers are left handed
 * [np] number of philosophers and forks
 * [nt] number of think/eat cycles
 * [tm] think time in milliseconds
 * [em] eat time in milliseconds
 * 
 * The Driver class handles creating the forks, philosophers and starting
 * the philosopher threads
 */
public class Driver {

	/*
	 * java Driver [-l] [np] [nt] [tm] [em]
	 * np - number of philosophers
	 * nt - number of think/eat cycles
	 * tm - think time in milliseconds
	 * em - eat time in milliseconds
	 */
	public static void main(String[] args){
		boolean oddPhilLeft = false;
		int startIndex = 0;
		if(args[0].equals("-l")){
			oddPhilLeft = true;
			startIndex = 1;
		}
		
		int np = Integer.parseInt(args[startIndex]);
		startIndex++;
		int nt = Integer.parseInt(args[startIndex]);
		startIndex++;
		int tm = Integer.parseInt(args[startIndex]);
		startIndex++;
		int em = Integer.parseInt(args[startIndex]);
		
		//Create the forks
		ArrayList<Fork> forks = new ArrayList<Fork>(np);
		for(int i = 0; i < np; i++){
			forks.add(new Fork());
		}
		
		//Create the philosophers
		ArrayList<Philosopher> philosophers = new ArrayList<Philosopher>(np);
		for(int i = 0; i < np; i++){
			boolean rHanded = true;
			if(oddPhilLeft && i%2 == 1){
				rHanded = false;
			}
			
			philosophers.add(new Philosopher(i, forks.get(i), 
					forks.get((np + i - 1)%np), rHanded, nt,tm,em));
		}
		
		//start the philosophers
		for(Philosopher phil : philosophers){
			phil.start();
		}
	}
	
}
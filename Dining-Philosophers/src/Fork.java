/**
 * Forks are the required resource for philosophers to eat.
 * Two forks are required to eat.
 */
public class Fork implements IFork{
	private boolean allocated = false;
	
	/**
	 * Call dibs on the fork
	 */
	public void acquire() {
		while(allocated){
			Thread.yield();
		}
		allocated = true;
	}

	/**
	 * Free up the fork
	 */
	public void release() {
		allocated = false;
	}

}
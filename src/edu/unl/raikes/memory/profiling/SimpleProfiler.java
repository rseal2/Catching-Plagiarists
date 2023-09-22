package edu.unl.raikes.memory.profiling;

/**
 * A very simple memory profiler that leverages the Java Runtime 
 * for a rough approximation of memory usage.
 * @author Karl Shaffer
 */
public class SimpleProfiler {
	
	public enum TimeInterval {
		MILLISECOND(1f, "milliseconds"), 
		SECOND(1000f, "seconds"),
		MINUTE(60000f, "minutes"),
		HOUR(3600000f, "hours");
		
		float scaleFactor; 
		String tag;
		
		TimeInterval(float scaleFactor, String tag) {
			this.scaleFactor = scaleFactor; 
			this.tag = tag;
		}

		/**
		 * @return the scaleFactor
		 */
		public float getScaleFactor() {
			return scaleFactor;
		}

		/**
		 * @return the tag
		 */
		public String getTag() {
			return tag;
		}
	}
	
	public enum MemoryInterval {
		BYTE(1f, "bytes"), 
		KILOBYTE(1024f, "kilobytes"), 
		MEGABYTE(1048576f, "megabytes"); 
		
		float scaleFactor; 
		String tag; 
	
		MemoryInterval(float scaleFactor, String tag) {
			this.scaleFactor = scaleFactor; 
			this.tag = tag; 
		}

		/**
		 * @return the scaleFactor
		 */
		public float getScaleFactor() {
			return scaleFactor;
		}

		/**
		 * @return the tag
		 */
		public String getTag() {
			return tag;
		}
	}
	
	
	private static String SEPARATOR = "==================================================\n";
	private static String REPORT_FORMAT = ""
			+ "STARTING MEMORY: %,.2f %s\n"
			+ "ENDING MEMORY: %,.2f %s\n"
			+ "USAGE: %,.2f %s\n"
			+ "TIME: %,.2f %s"; 
	
	
	// Time variables.
	private long startTime; 
	private long endTime; 
	
	// Memory variables. 
	private long startFreeMemory;
	private long startTotalMemory; 
	private long endFreeMemory;
	private long endTotalMemory; 
	
	private boolean running; 
	
	public SimpleProfiler() {
		this.running = false; 
	}
	
	/**
	 * Starts the profiler. 
	 * @return The {@link Profiler}
	 */
	public SimpleProfiler start() {
		if (running) {
			throw new IllegalStateException("Cannot start the profiler while it is already running."); 
		}
		
		Runtime runtime = Runtime.getRuntime(); 
		
		this.running = !this.running; 
		this.startTime = System.currentTimeMillis(); 
		this.startTotalMemory = runtime.totalMemory(); 
		this.startFreeMemory = runtime.freeMemory(); 
		return this; 
	}
	
	/**
	 * Ends the profiler.
	 * @return The {@link Profiler}
	 */
	public SimpleProfiler stop() {
		if (!running) {
			throw new IllegalStateException("Cannot stop the profiler while it is not running.");
		}
		
		Runtime runtime = Runtime.getRuntime(); 
		
		this.running = !this.running; 
		this.endTime = System.currentTimeMillis(); 
		this.endTotalMemory = runtime.totalMemory(); 
		this.endFreeMemory = runtime.freeMemory(); 
		return this; 
	}
	
	/**
	 * A report of the simple profiling done with information about memory and time usage.
	 * @return
	 */
	public String getReport(TimeInterval timeInterval, MemoryInterval memoryInterval) {
		String toReturn = ""; 
		
		toReturn += SEPARATOR; 
		long startMemoryUsage = startTotalMemory - startFreeMemory; 
		long endMemoryUsage = endTotalMemory - endFreeMemory; 
		long delta = endMemoryUsage - startMemoryUsage;
		long time = endTime - startTime; 
		
		float formattedStartMemoryUsage = startMemoryUsage / memoryInterval.getScaleFactor(); 
		float formattedEndMemoryUsage = endMemoryUsage / memoryInterval.getScaleFactor(); 
		float formattedDelta = delta / memoryInterval.getScaleFactor(); 
		
		float formattedTime = time / timeInterval.getScaleFactor();
		
		toReturn += String.format(REPORT_FORMAT, 
				formattedStartMemoryUsage, 
				memoryInterval.getTag(),
				formattedEndMemoryUsage,
				memoryInterval.getTag(), 
				formattedDelta,
				memoryInterval.getTag(),
				formattedTime, 
				timeInterval.getTag()); 
		toReturn += "\n" + SEPARATOR; 
		
		return toReturn; 
	}
}

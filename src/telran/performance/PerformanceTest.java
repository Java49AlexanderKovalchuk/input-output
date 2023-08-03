package telran.performance;

public abstract class PerformanceTest {
	private String testName;
	private int nRuns;
	public PerformanceTest(String testName, int nRuns) {
		super();
		this.testName = testName;
		this.nRuns = nRuns;
	}
	abstract protected void runTest() ;
	public void run() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < nRuns; i++) {
			runTest();
		}
		displayInfo(start, System.currentTimeMillis());
	}
	private void displayInfo(long start, long finish) {
		System.out.printf("test %s; Number of the runs: %d; Running time: %dms\n",
				testName, nRuns, finish - start);
		
	}
}

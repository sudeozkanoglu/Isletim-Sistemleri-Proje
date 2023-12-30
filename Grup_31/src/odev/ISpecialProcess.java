package odev;

public interface ISpecialProcess {

	ProcessBuilder getProcessBuilder();

	int getPid();

	int getDestinationTime();

	// Process onceligi bir birim dusurulur.
	void decreasePriority();

	int getBurstTime();

	// Process'in calisma suresi bir birim dusurulur.
	void decreaseBurstTime();

	// Process'in bekleme zamani bir birim dusurulur. Bekleme zamani 20 oldugunda true dondurulur.
	boolean increaseWaitingTime();

	// Process'in bekleme suresi sifirlanir.
	void resetWaitingTime();
	
	int getMemory();
	int getPrinters();
	int getScanners();
	int getModems();
	int getCds();   
	Statement getStatement();
	Priority getPriority();
	void setStatement(Statement statement);
}

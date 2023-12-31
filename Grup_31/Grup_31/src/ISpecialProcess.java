
public interface ISpecialProcess {

	ProcessBuilder getProcessBuilder();

	int getPid();

	int getDestinationTime();

	Priority getPriority();

	// Prosesin önceliğini bir derece düşürür.
	void decreasePriority();

	int getBurstTime();

	// Prosesin çalışma süresini bir azaltır.
	void decreaseBurstTime();

	// Prosesin bekleme süresini bir artırır. Eğer 20 olmuşsa true döndürür.
	boolean increaseWaitingTime();

	// Prosesin bekleme süresini sıfırlar.
	void resetWaitingTime();

	Statement getStatement();
	
	void setStatement(Statement statement);
	int getMemory();
	int getPrinters();
	int getScanners();
	int getModems();
	int getCds();
    

   
}

package odev;

public class SpecialProcess implements ISpecialProcess {
	private ProcessBuilder process;
	private int pid;
	private int destinationTime;
	private Priority priority;
	private int burstTime;
	private int waitingTime;
	private Statement statement;
	private Memory memory;

    public SpecialProcess(int pid, int destinationTime, Priority priority, int burstTime,Memory memory) {
		this.pid = pid;
		this.destinationTime = destinationTime;
		this.priority = priority;
		this.burstTime = burstTime;
		this.waitingTime = 0;
		this.statement = Statement.New;
		this.memory=memory;
       

		process = new ProcessBuilder("java", "-jar", "./process.jar");
	}

	@Override
	public ProcessBuilder getProcessBuilder() {
		return this.process;
	}

	@Override
	public int getPid() {
		return this.pid;
	}

	@Override
	public int getDestinationTime() {
		return this.destinationTime;
	}

	@Override
	public Priority getPriority() {
		return this.priority;
	}

	// Prosesin önceliğini bir derece düşürür.
	@Override
	public void decreasePriority() {
		if (this.priority == Priority.Highest) {
			this.priority = Priority.Medium;
		} else if (this.priority == Priority.Medium) {
			this.priority = Priority.Lowest;
		}
	}

	@Override
	public int getBurstTime() {
		return this.burstTime;
	}

	// Prosesin çalışma süresini bir azaltır.
	@Override
	public void decreaseBurstTime() {
		if (this.burstTime > 0)
			--this.burstTime;
	}

	// Prosesin bekleme süresini bir artırır. Eğer 20 olmuşsa true döndürür.
	@Override
	public boolean increaseWaitingTime() {
		this.waitingTime++;
		return this.waitingTime < 20 ? false : true;
	}

	// Prosesin bekleme süresini sıfırlar.
	@Override
	public void resetWaitingTime() {
		this.waitingTime = 0;
	}

	@Override
	public Statement getStatement() {
		return this.statement;
	}

	@Override
	public void setStatement(Statement statement) {
		if (statement != null)
			this.statement = statement;
	}
	public int getMemory() {
        return memory.availableMemory;
    }

    public int getPrinters() {
        return memory.availablePrinters;
    }

    public int getScanners() {
        return memory.availableScanners;
    }

    public int getModems() {
        return memory.availableModems;
    }

    public int getCds() {
        return memory.availableCds;
    }

}

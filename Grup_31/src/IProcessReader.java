
public interface IProcessReader {
	// Verilen varış zamanında gelen prosesleri döndürür.
	IProcessQueue getProcesses(int destinationTime);

	// Gelecekte başka proses olup olmadığını döndürür.
	boolean isEmpty();
}

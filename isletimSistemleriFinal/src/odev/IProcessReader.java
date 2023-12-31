package odev;


public interface IProcessReader {
	//Varış zamanına göre prosesleri döndürür
	IProcessQueue getProcesses(int destinationTime);
	// Yeni gelecek olan process olup olmadigini dondurur.
		boolean isEmpty();
}

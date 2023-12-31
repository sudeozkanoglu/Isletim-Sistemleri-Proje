
public interface IProcessQueue extends IQueue<ISpecialProcess> {

	ISpecialProcess getFirstItem();

	// Kuyruktaki tüm proseslerin bekleme zamanını 1 artırır. Bekleme süresi 20
	// saniyeyi aşan prosesleri döndürür.
	IProcessQueue increaseWaitingTime();

	IProcessQueue search(int destinationTime);

	void delete(ISpecialProcess process);
}

package odev;


public interface IProcessQueue extends IQueue<ISpecialProcess> {

	ISpecialProcess getFirstItem();

	//kuyruktaki processlerin bekleme zamanini artirir ve bekleme süresi 20 saniyeyi gecen prosesleri geri döndürür
	IProcessQueue increaseWaitingTime();

	IProcessQueue search(int destinationTime);

	void delete(ISpecialProcess process);
}

public class Memory {
    public int availableMemory=1024; // Toplam bellek
    public int availablePrinters=2; // Yazıcı sayısı
    public int availableScanners=1; // Tarayıcı sayısı
    public int availableModems=1; // Modem sayısı
    public int availableCds=2; // CD sürücü sayısı
    
    
    
    public Memory(int memory, int printers, int scanners, int modems, int cds) {
        
    }

    public Memory() {
		// TODO Auto-generated constructor stub
	}

	public synchronized boolean requestResources(int memory, int printers, int scanners, int modems, int cds) {
        if (memory < availableMemory && printers < availablePrinters && scanners < availableScanners && modems < availableModems && cds < availableCds) {
            availableMemory -= memory;
            availablePrinters -= printers;
            availableScanners -= scanners;
            availableModems -= modems;
            availableCds -= cds;
            return true;
        }
        return false;
    }

    public synchronized void releaseResources(int memory, int printers, int scanners, int modems, int cds) {
        availableMemory += memory;
        availablePrinters += printers;
        availableScanners += scanners;
        availableModems += modems;
        availableCds += cds;
    }

    // Diğer yardımcı metodlar ve kaynak durumunu kontrol eden metodlar burada olabilir.
}

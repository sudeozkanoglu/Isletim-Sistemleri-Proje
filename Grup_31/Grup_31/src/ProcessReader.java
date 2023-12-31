
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProcessReader implements IProcessReader {
	private IProcessQueue processes;
	private Memory memory;
	public ProcessReader(String path) {
		this.processes = new ProcessQueue();
		this.memory=new Memory();
		readFile(path);
	}
	
	// Metin dosyasını okur ve bütün prosesleri oluşturup bir kuyruğa atar.
	private void readFile(String path) {
	    try {
	        File file = new File(path);
	        if (file.exists()) {
	            Scanner dosya = new Scanner(file);
	            int count = 0;
	            while (dosya.hasNextLine()) {
	                String line = dosya.nextLine();
	                line = line.replaceAll("\\s+", "");
	                var processInformations = line.split(",");

	                int pid = count;
	                int destinationTime = Integer.parseInt(processInformations[0]);

	                Priority priority = switch (Integer.parseInt(processInformations[1])) {
	                    case 0 -> Priority.RealTime;
	                    case 1 -> Priority.Highest;
	                    case 2 -> Priority.Medium;
	                    case 3 -> Priority.Lowest;
	                    default -> throw new IllegalArgumentException(
	                            "Unexpected value: " + Integer.parseInt(processInformations[1]));
	                };
	                
	                int burstTime = Integer.parseInt(processInformations[2]);
	                int memory = Integer.parseInt(processInformations[3]);
	                int printers = Integer.parseInt(processInformations[4]);
	                int scanners = Integer.parseInt(processInformations[5]);
	                int modems = Integer.parseInt(processInformations[6]);
	                int cds = Integer.parseInt(processInformations[7]);
	                ISpecialProcess process = new SpecialProcess(pid, destinationTime, priority, burstTime,ProcessReader.this.memory);
	                
	                boolean condition = ProcessReader.this.memory.requestResources(memory, printers, scanners, modems, cds);// burada yeterli kaynak var mı kontrolü yapılıyor
	                if(condition == true)
	                {
	                	this.processes.enqueue(process);
		                ++count;
	                }
	                else
	                {
	                	System.out.println("Hata");
	                }
	                
	            }
	            dosya.close();
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}


	// Verilen varış zamanında gelen prosesleri döndürür. Ayrıca prosesleri içindeki
	// kuyruktan çıkarır.
	@Override
	
	public IProcessQueue getProcesses(int destinationTime) {
		IProcessQueue foundedProcesses = new ProcessQueue();
		IProcessQueue tmpQueue = this.processes.search(destinationTime);
		ISpecialProcess tmpProcess;
		while (!tmpQueue.isEmpty()) {
			tmpProcess = tmpQueue.dequeue();
			ProcessReader.this.memory.releaseResources(tmpProcess.getMemory(), tmpProcess.getPrinters(), tmpProcess.getScanners(), tmpProcess.getModems(), tmpProcess.getCds());
			this.processes.delete(tmpProcess);
			foundedProcesses.enqueue(tmpProcess);
		}
		return foundedProcesses;
	}

	@Override
	public boolean isEmpty() {
		return processes.isEmpty();
	}

}

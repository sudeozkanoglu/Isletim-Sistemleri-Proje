package odev;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProcessReader implements IProcessReader {
	private IProcessQueue processes;

	public ProcessReader(String path) {
		this.processes = new ProcessQueue();

		readFile(path);
	}
	//prosesleri kuyruga atar
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



	                int burstTime = Integer.parseInt(processInformations[2]);
	                int memory = Integer.parseInt(processInformations[3]);
	                int printers = Integer.parseInt(processInformations[4]);
	                int scanners = Integer.parseInt(processInformations[5]);
	                int modems = Integer.parseInt(processInformations[6]);
	                int cds = Integer.parseInt(processInformations[7]);

	               
	                ++count;
	            }
	            dosya.close();
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}

//varis zamaninda gelen prosesleri döndürür

	@Override
	
	public IProcessQueue getProcesses(int destinationTime) {
		IProcessQueue foundedProcesses = new ProcessQueue();
		IProcessQueue tmpQueue = this.processes.search(destinationTime);
		ISpecialProcess tmpProcess;
		while (!tmpQueue.isEmpty()) {
			tmpProcess = tmpQueue.dequeue();
			foundedProcesses.enqueue(tmpProcess);
		}
		return foundedProcesses;
	}

	@Override
	public boolean isEmpty() {
		return processes.isEmpty();
	}

}

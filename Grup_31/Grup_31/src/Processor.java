
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Processor implements IProcessor {
	private ISpecialProcess currentProcess;

	public Processor() {
		this.currentProcess = null;
	}

	private void runProcess(ISpecialProcess process, int currentTime, String sendedMessage) {

		String messageLeft = "\u001b[0m" + "\u001b[38;5;" + process.getPid() % 256 + "m"
				+ String.format("%.4f", (float) currentTime) + " sn proses ";
		String messageMiddle = sendedMessage;
		String messageRight = "\t(id:" + String.format("%04d", process.getPid()) + " oncelik:"
				+ process.getPriority().ordinal() + " kalan sure:" + process.getBurstTime() + " sn)";
		String message = messageLeft + messageMiddle + messageRight;
		
		try {
			ProcessBuilder processBuilder = process.getProcessBuilder();
			processBuilder.command().add(message);
			Process p = processBuilder.start();
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String s = "";
			while ((s = in.readLine()) != null) {
				System.out.println(s);
			}
			processBuilder.command().remove(3);
			p.destroyForcibly();
		} catch (Exception e) {
		}
	}

	// Gönderilen prosesi çalıştırır.
	@Override
	public void run(ISpecialProcess process, int currentTime) {
		// Kuyrukta ve gelecek olan bütün prosesler bittiğinde en son çalışan prosesin
		// bittiğini göstermek için
		if (process == null && this.currentProcess.getStatement() == Statement.Terminated) {
			this.runProcess(this.currentProcess, currentTime, "sonlandi");
			return;
		}

		// Zaman aşımına uğrayan prosesleri ekrana çıkarmak için
		if (process.getStatement() == Statement.TimeOut) {
			this.runProcess(process, currentTime, "zamanasimi");
			return;
		}

		// İşlemci ilk defa çalıştığı zaman için
		if (this.currentProcess == null) {
			this.currentProcess = process;
			this.runProcess(this.currentProcess, currentTime, "basladi");
			process.decreaseBurstTime();
			return;
		}

		if (this.currentProcess != process) {
			if (this.currentProcess.getStatement() == Statement.Terminated) {
				this.runProcess(this.currentProcess, currentTime, "sonlandi");
			} else if (this.currentProcess.getStatement() == Statement.Ready) {
				this.runProcess(this.currentProcess, currentTime, "askida ");
			}
			this.currentProcess = process;
			this.runProcess(this.currentProcess, currentTime, "basladi");
		} else {
			this.runProcess(process, currentTime, "yurutuluyor");
		}
		process.decreaseBurstTime();
	}

}


public class Program {

	public static void main(String[] args) {
		IProcessReader processReader = new ProcessReader("giriş.txt");
		IProcessor processor = new Processor();
		IDispatcher dispatcher = new Dispatcher(processor, processReader);

		dispatcher.start();
	}
}

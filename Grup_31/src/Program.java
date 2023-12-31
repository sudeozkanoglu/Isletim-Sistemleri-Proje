
public class Program {

	public static void main(String[] args) {
		IProcessReader processReader = new ProcessReader(args[0]);
		IProcessor processor = new Processor();
		IDispatcher dispatcher = new Dispatcher(processor, processReader);

		dispatcher.start();
	}
}

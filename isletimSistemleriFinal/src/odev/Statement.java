package odev;


public enum Statement {
	New, // Prosesin oluştuğu durumu
	Ready, // Prosesin hazır olduğu durum
	Running, // Proses çalıştığı durum
	Terminated, // Prosesin sonlandırıldığı durum
	TimeOut; // Prosesin zamanaşımına uğradığı durum
}

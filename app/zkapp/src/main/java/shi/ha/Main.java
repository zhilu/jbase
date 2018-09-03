package shi.ha;

public class Main {
	public static void main(String[] args) throws Exception {

		boolean isWorking = false;
		Switcher co = new Switcher();

		while (true) {

			if (!isWorking) {
				
				String workingKey = co.getWorkingkey();
				if (workingKey.equals(co.getRegKey())) {
					new Task().work();
				} else {
					Thread.sleep(1000);
				}
			}else{
				Thread.sleep(60*1000);
			}
		}

	}
}

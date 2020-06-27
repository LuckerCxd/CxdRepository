package Try_sig.Fac;

import java.util.HashMap;

public class FlyWeight {
	public static void main(String[] args) {
		MachineFactory factory = new MachineFactory();
		Machine machine = factory.getMachine("hello");
		machine.tell();
	}
}
class MachineDog implements Machine{
	private String name;
	public MachineDog(String name) {
		this.name = name;
	}
	@Override
	public void tell() {
		System.out.println("Machine dog here : "+name);
	}
}

class MachineFactory{
	private HashMap<String, Machine> machineMap = new HashMap<>();
	public Machine getMachine(String name) {
		Machine machine = (MachineDog)machineMap.get(name);
		if(machine == null) {
			machine = new MachineDog(name);
			machineMap.put(name, machine);
		}
		return machine;
	}
}


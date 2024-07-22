//Use Case: Simplifying the interaction with a complex subsystem.

class CPU {
    public void freeze() {
        System.out.println("CPU freezing...");
    }

    public void jump(long position) {
        System.out.println("CPU jumping to " + position);
    }

    public void execute() {
        System.out.println("CPU executing...");
    }
}

class Memory {
    public void load(long position, byte[] data) {
        System.out.println("Memory loading data to " + position);
    }
}
class HardDrive {
    public byte[] read(long lba, int size) {
        System.out.println("Reading " + size + " from LBA " + lba);
        return new byte[]{1, 2, 3};
    }
}
class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void start() {
        cpu.freeze();
        memory.load(0, hardDrive.read(0, 1024));
        cpu.jump(0);
        cpu.execute();
    }
}
class StructuralFacade {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.start();
    }
}

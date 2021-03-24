public class AdapterPattern {
    public static void main(String args[]) {
        //Instantiating the old coffee machine
        OldCoffeeMachine oldMachineTest = new OldCoffeeMachine();
        //Instantiating the touch screen
        CoffeeTouchscreenAdapter touchScreenTest = new CoffeeTouchscreenAdapter(oldMachineTest);

        System.out.println("This is a demo of the OLD machine.");
        oldMachineTest.selectA(); //Old machine option A
        oldMachineTest.selectB(); //Old machine option B

        System.out.println();
        System.out.println("This is a demo of the touch screen adapter");
        touchScreenTest.chooseFirstSelection(); //Touch screen choosing option A
        touchScreenTest.chooseSecondSelection(); //Touch screen choosing option B
    }
}

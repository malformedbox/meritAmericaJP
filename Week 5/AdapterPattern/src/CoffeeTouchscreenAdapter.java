public class CoffeeTouchscreenAdapter implements CoffeeMachineInterface {
    private OldCoffeeMachine coffeeMachine;

    public CoffeeTouchscreenAdapter(OldCoffeeMachine newMachine){
        coffeeMachine = newMachine;
    }

    public void chooseFirstSelection() {
        coffeeMachine.selectA();
        System.out.println("Selected option A via the touchscreen.");
    }

    public void chooseSecondSelection() {
        coffeeMachine.selectB();
        System.out.println("Selected option B via the touchscreen.");
    }
}

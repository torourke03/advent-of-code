package Year2025.Day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Machine {
    private ArrayList<Boolean> lights;
    private ArrayList<ArrayList<Integer>> buttons;
    private ArrayList<Integer> joltages;

    public Machine(ArrayList<Boolean> lights, ArrayList<ArrayList<Integer>> buttons, ArrayList<Integer> joltages) {
        this.lights = lights;
        this.buttons = buttons;
        this.joltages = joltages;
    }

    public ArrayList<Boolean> getLights() {
        return lights;
    }

    public ArrayList<ArrayList<Integer>> getButtons() {
        return buttons;
    }

    public ArrayList<Integer> getJoltages() {
        return joltages;
    }

    public boolean checkButtonCombo(String buttonCombo) {
        assert buttonCombo.length() == buttons.size() : "Button Combo: " + buttonCombo + " > " + buttons.size();
        ArrayList<Boolean> checkLights = new ArrayList<>(lights.size());
        for (boolean _ : lights) checkLights.add(false);

        for (int i = 0; i < buttonCombo.length(); i++) {
            if (buttonCombo.charAt(i) == '1') {
                for (int j = 0; j < buttons.get(i).size(); j++) {
                    checkLights.set(buttons.get(i).get(j), !checkLights.get(buttons.get(i).get(j)));
                }
            }
        }
        return checkLights.equals(lights);
    }

    public int makeJoltages() {
        buttons.sort(Comparator.comparingInt(ArrayList::size));
        Collections.reverse(buttons);



        return 0;
    }
}

package Year2025.Day11;

import java.util.Objects;

public class Inputs {
    public String device;
    public boolean dac;
    public boolean fft;

    public Inputs(String device, boolean dac, boolean fft) {
        this.device = device;
        this.dac = dac;
        this.fft = fft;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Inputs inputs = (Inputs) o;
        return dac == inputs.dac && fft == inputs.fft && Objects.equals(device, inputs.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(device, dac, fft);
    }
}

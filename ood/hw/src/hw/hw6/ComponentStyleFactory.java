package hw.hw6;

import java.awt.Color;

public class ComponentStyleFactory {

    private static ComponentStyleFactory csf = null;

    private ComponentStyleFactory() {

    }

    public static ComponentStyleFactory instance() {
        if(csf == null) { csf = new ComponentStyleFactory(); }
        return csf;
    }

    // Creates a second color by addint a tint to the first color
    // The tint formula I used is original + (255 - original * offset based on color)
    // Included checks for out of bound cases
    public static ComponentStyler createMonochrome(Color c1){
        int red = c1.getRed() + ((255 - c1.getRed()) / 4);
        int green = c1.getGreen() + ((255 - c1.getGreen()) / 2);
        int blue = c1.getBlue() + ((255 - c1.getBlue()) * 3 / 4);

        if (red > 255) { red = 255; }
        if (green > 255) { green = 255; }
        if (blue > 255) { blue = 255; }

        if (red < 0) { red = 0; }
        if (green < 0) { green = 0; }
        if (blue < 0) { blue = 0; }

        Color c2 = new Color(red, green, blue);
        return new ComponentStyler(c1, c2);
    }

    public static ComponentStyler create(Color c1, Color c2) {
        return new ComponentStyler(c1, c2);
    }
}

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Generador {
   // private static final int NUMEROCOCHES = 15;
   // private static final int NUMEROCAMIONES = 15;
    private static final int NUMEROVEHICULOS = 30;

    public static void main(String[] args) throws InterruptedException {
        Random rd = new Random();
        CanvasGasolinera canvas = new CanvasGasolinera(900, 600);

        Gasolinera gasolinera = new Gasolinera(canvas);

        JFrame frame = new JFrame("Simulación Gasolinera");
        frame.add(canvas);
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        ArrayList<Thread> vehiculos = new ArrayList<>();

        for (int i = 0; i < NUMEROVEHICULOS; i++) {
            if(rd.nextBoolean()) {
                Coche coche = new Coche(i, gasolinera, canvas, true);
                vehiculos.add(coche);
            } else {
                Camion camion = new Camion(i, gasolinera, canvas, false);
                vehiculos.add(new Thread(camion));
            }
        }

        for (Thread v : vehiculos) {
            v.start();
            sleep(500);
        }

        // Esperar a que todos los coches y camiones terminen
        for (Thread v : vehiculos) {
            v.join();
        }

        System.out.println("Termina MAIN.");
    }
}

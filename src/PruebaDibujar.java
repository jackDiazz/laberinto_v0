import java.awt.*;
import javax.swing.*;
public class PruebaDibujar extends JPanel {

    private Laberinto lb;

    public PruebaDibujar(Laberinto lb) {
        this.lb = lb;
    }

    public void paint(Graphics g) {
        g.drawRect(20, 20, 500, 500);
        laberintoGrafico(lb, g);
    }

    public static void main(String[] args) {
        Laberinto l1 = new Laberinto(10, 10);
        l1.generaTablero();
        l1.generaLaberinto();
        System.out.println(l1.printLaberinto());


        JFrame vent = new JFrame("Laberinto");
        Dimension dp = Toolkit.getDefaultToolkit().getScreenSize();
        vent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vent.setBounds((int) dp.getWidth() / 5, 0, 600, 600);
        vent.setVisible(true);
        vent.setResizable(false);
        vent.getContentPane().add(new PruebaDibujar(l1));
    }

    public static void laberintoGrafico(Laberinto lab, Graphics db) {
        int divI = (int) 500 / lab.getLargo(), divJ = (int) 500 / lab.getAncho();
        // Recorrer tablero
        for (int i = 0; i < lab.getLargo(); i++) {
            for (int j = 0; j < lab.getAncho(); j++) {
                if (i == 0) { // Caso especial de la primera fila
                    if (j == lab.getAncho() - 1) continue;
                    if (lab.getTablero()[0][j].getBordeInferior() && lab.getTablero()[1][j].getBordeSuperior())
                        db.drawLine(20 + j * divJ, 20 + divI, 20 + (j + 1) * divJ, 20 + divI);
                    if (lab.getTablero()[0][j].getBordeDerecho() && lab.getTablero()[0][j + 1].getBordeIzquierdo())
                        db.drawLine(20 + j * divJ, 20, 20 + j * divJ, 20 + divI);
                    if (lab.getTablero()[0][lab.getAncho() - 2].getBordeDerecho() && lab.getTablero()[0][lab.getAncho() - 1].getBordeIzquierdo())
                        db.drawLine(20 + (lab.getAncho() - 2) * divJ, 20, 20 + (lab.getAncho() - 2) * divJ, 20 + divI);
                } else if (i == lab.getLargo() - 1) { // Caso especial de la última fila
                    if (j == lab.getAncho() - 1) continue;
                    if (lab.getTablero()[lab.getLargo() - 1][j].getBordeDerecho() && lab.getTablero()[lab.getLargo() - 1][j + 1].getBordeIzquierdo())
                        db.drawLine(20 + j * divJ, 20 + (lab.getAncho() - 1) * divI, 20 + j * divJ, 20 + lab.getAncho() * divI);
                    if (lab.getTablero()[lab.getLargo() - 1][0].getBordeDerecho() && lab.getTablero()[lab.getLargo() - 1][1].getBordeIzquierdo())
                        db.drawLine(20 + divJ, 20 + (lab.getLargo() - 1) * divI, 20 + divJ, 20 + lab.getLargo() * divI);
                } else if (j == 0) { // Caso especial de la primera columna
                    if (i == 0 && j == 0) {
                        if (lab.getTablero()[0][0].getBordeInferior() && lab.getTablero()[1][0].getBordeSuperior())
                            db.drawLine(20, 20 + divI, 20 + divJ, 20 + divI);
                    } else {
                        if (lab.getTablero()[i][0].getBordeInferior() && lab.getTablero()[i + 1][0].getBordeSuperior())
                            db.drawLine(20, 20 + i * divI, 20 + divJ, 20 + i * divI);
                        if (lab.getTablero()[i][0].getBordeDerecho() && lab.getTablero()[i][1].getBordeIzquierdo())
                            db.drawLine(20 + divJ, 20 + (i - 1) * divI, 20 + divJ, 20 + i * divI);
                    }
                } else if (j == lab.getAncho() - 1) { // Caso especial de la última columna
                    if (i == 0) continue;
                    if (lab.getTablero()[i][lab.getAncho() - 1].getBordeInferior() && lab.getTablero()[i + 1][lab.getAncho() - 1].getBordeSuperior())
                        db.drawLine(20 + (lab.getAncho() - 1) * divJ, 20 + (lab.getLargo()) * divI, 20 + (lab.getAncho()) * divJ, 20 + (lab.getLargo()) * divI);
                } else {
                    if (i == 0 || i == lab.getLargo() - 1 || j == 0 || j == lab.getAncho() - 1) continue;
                    if (lab.getTablero()[i][j].getBordeInferior() && lab.getTablero()[i + 1][j].getBordeSuperior())
                        db.drawLine(20 + j * divJ, 20 + i * divI, 20 + (j + 1) * divJ, 20 + i * divI);
                    if (lab.getTablero()[i][j].getBordeDerecho() && lab.getTablero()[i][j + 1].getBordeIzquierdo())
                        db.drawLine(20 + j * divJ, 20 + (i - 1) * divI, 20 + j * divJ, 20 + i * divI);
                }
            }
        }
    }
}
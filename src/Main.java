import javax.swing.*;
import java.awt.*;

record Car(String brand) { }

class ParkingLot {
    private final int colSize, rowSize;
    private final Car[][] grid;
    public ParkingLot(int rowSize, int colSize) {
        this.colSize = colSize;
        this.rowSize = rowSize;
        grid = new Car[rowSize][colSize];
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColSize() {
        return colSize;
    }

    public void parkCar(Car car, int row, int col) {
        if(grid[row][col]!=null) {
            return;
        }
        grid[row][col] = car;
    }

    public Car[][] getParkingLot() {
        return grid;
    }
}

class Window extends JFrame {
    private final ParkingLot parkingLot;
    Window (ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
    public void start() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800,800));
        this.add(generateLot());
        this.pack();
        this.setVisible(true);
    }

    private JPanel generateLot() {
        JPanel p = new JPanel();
        int x = parkingLot.getColSize(), y= parkingLot.getRowSize();
        p.setLayout(new GridLayout(y,x,5,5));

        for(int i = 0; i < y;i++) {

            for(int j=0;j < x;j++) {

                JPanel grid = new JPanel();
                grid.add(new JLabel(String.format("[%d,%d]", i,j)));

                if(parkingLot.getParkingLot()[i][j]!=null) {
                    grid.add(new JLabel(parkingLot.getParkingLot()[i][j].brand()));
                    grid.setBackground(Color.red);
                }

                else grid.setBackground(Color.green);

                p.add(grid);
            }
        }
        return p;
    }

}

public class Main {
    public static void main(String[] args) {
        ParkingLot lotOne = new ParkingLot(5,5);
        lotOne.parkCar(new Car("Ford"), 0, 1);
        lotOne.parkCar(new Car("Toyota"), 1, 3);
        lotOne.parkCar(new Car("Honda"), 2, 1);
        lotOne.parkCar(new Car("Mitsubishi"), 1, 4);
        lotOne.parkCar(new Car("Toyota"), 4, 2);
        lotOne.parkCar(new Car("Ford"), 1, 1);
        lotOne.parkCar(new Car("Toyota"), 0, 3);
        lotOne.parkCar(new Car("Honda"), 3, 4);
        lotOne.parkCar(new Car("Mitsubishi"), 4, 4);
        lotOne.parkCar(new Car("Toyota"), 1, 2);
        lotOne.parkCar(new Car("Toyota"), 4, 0);
        lotOne.parkCar(new Car("Toyota"), 2, 3);
        lotOne.parkCar(new Car("Toyota"), 0, 3);

        new Window(lotOne).start();
    }
}
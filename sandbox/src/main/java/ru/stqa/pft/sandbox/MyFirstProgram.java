package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {

    Point p = new Point(1, 2, 3, 4);

    System.out.println("Координаты точки x(" + p.x + ";" + p.y + ")");
    System.out.println("Координаты точки y(" + p.x2 + ";" + p.y2 + ")");
    System.out.println("Расстояние между точками " + "x и y" + " = " + p.distance());
  }
}






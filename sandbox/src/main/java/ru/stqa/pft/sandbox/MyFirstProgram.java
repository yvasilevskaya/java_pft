package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("Yulia");
    hello("sun");
    hello("spring");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4,6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());


    Point p1 = new Point(1, 2);
    Point p2 = new Point(3, 4);

    System.out.println("Координаты точки x(" + p1.x + ";" + p1.y + ")");
    System.out.println("Координаты точки y(" + p2.x + ";" + p2.y + ")");
    System.out.println("Расстояние между точками " + "x и y" + " = " + p1.distance(p2));
  }

  public static void hello(String somebody) {
    System.out.println("Hello," + somebody + "!:)");
  }
}






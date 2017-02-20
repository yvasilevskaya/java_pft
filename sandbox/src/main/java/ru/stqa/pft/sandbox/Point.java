package ru.stqa.pft.sandbox;

/**
 * Created by Yulia on 2/17/2017.
 */

class Point {
  int x, y;

  Point(int х,int y) {
    this.x = х;
    this.y = y;
  }

  double distance(int x, int y) {
    int dx = this.x - x;
    int dy = this.y - y;
    return Math.sqrt(dx * dx + dy * dy);
  }

  double distance(Point p) {
    return distance(p.x, p.y);
  }
}


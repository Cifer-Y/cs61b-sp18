public class NBody {

  public static double readRadius(String path) {
    In in = new In(path);
    in.readInt();
    double radius = in.readDouble();
    return radius;
  }

  public static Planet[] readPlanets(String path) {
    In in = new In(path);
    int count = in.readInt();
    in.readDouble();
    Planet[] planets = new Planet[count];
    for (int i = 0; i < count; i++) {
      double xxPos = in.readDouble();
      double yyPos = in.readDouble();
      double xxVel = in.readDouble();
      double yyVel = in.readDouble();
      double mass = in.readDouble();
      String imgFileName = in.readString();
      planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
    }
    return planets;

  }

  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String path = args[2];
    Planet[] ps = readPlanets(path);
    System.out.println(T);
    System.out.println(dt);
    

    double r = readRadius(path);
    StdDraw.enableDoubleBuffering();
    StdDraw.setScale(-1 * r, r);
		StdDraw.clear();

    double counter = 0;

    while (counter != T) {
      double[] xForces = new double[ps.length];
      double[] yForces = new double[ps.length];
      for (int i = 0; i < ps.length; i++) {
        xForces[i] = ps[i].calcNetForceExertedByX(ps);
        yForces[i] = ps[i].calcNetForceExertedByY(ps);
      }
      for (int i = 0; i < ps.length; i++) {
        ps[i].update(dt, xForces[i], yForces[i]);
      }
      StdDraw.picture(0, 0, "./images/starfield.jpg");
      for (Planet p : ps) {
        p.draw();
      }
      StdDraw.show();
      StdDraw.pause(10);
      counter += dt;
    }

    StdOut.printf("%d\n", ps.length);
    StdOut.printf("%.2e\n", r);
    for (int i = 0; i < ps.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
      ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
      ps[i].yyVel, ps[i].mass, ps[i].imgFileName);   
    }
    
  }
  
}
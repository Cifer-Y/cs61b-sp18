public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = xxPos - p.xxPos;
        double dy = yyPos - p.yyPos;
        double r = Math.sqrt((dx*dx) + (dy*dy));
        return r;
    }

    public double calcForceExertedBy(Planet p) {
        double r = calcDistance(p); 
        double f = (G * mass * p.mass) / (r*r);
        return f;
    }

    public double calcForceExertedByX(Planet p) {
        double f = calcForceExertedBy(p);
        double dx = p.xxPos - xxPos;
        double r = calcDistance(p);
        double fx =  (f*dx) / r;
        return fx;
    }

    public double calcForceExertedByY(Planet p) {
        double f = calcForceExertedBy(p);
        double dy = p.yyPos - yyPos;
        double r = calcDistance(p);
        double fy =  (f*dy) / r;
        return fy;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double fnx = 0.0;
        for (Planet p : allPlanets) {
            if(!equals(p)) {
                fnx += calcForceExertedByX(p);
            }
        }
        return fnx;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double fny = 0.0;
        for (Planet p : allPlanets) {
            if(!equals(p)) {
                fny += calcForceExertedByY(p);
            } 
        }
        return fny;
    }

    public void update(double dt, double fX, double fY) {
        double anx = fX / mass;
        double any = fY / mass;
        xxVel = xxVel + dt * anx;
        yyVel =  yyVel + dt * any;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }

    private boolean equals(Planet p) {
        return xxPos == p.xxPos && yyPos == p.yyPos && 
                xxVel == p.xxVel && yyVel == p.yyVel && 
                mass == p.mass && imgFileName == p.imgFileName;
    }
}

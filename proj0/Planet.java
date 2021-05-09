public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67E-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p){
        return Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos)
                + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
    }
    public double calcForceExertedBy(Planet p){
        return G * this.mass * p.mass / Math.pow(this.calcDistance(p), 2);
    }
    public double calcForceExertedByX(Planet p){
        double dist = this.calcDistance(p);
        return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / dist;
    }
    public double calcForceExertedByY(Planet p){
        double dist = this.calcDistance(p);
        return this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / dist;
    }
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double netForceX = 0;
        for (Planet p:allPlanets){
            if(!this.equals(p)){
                netForceX += this.calcForceExertedByX(p);
            }
        }
        return netForceX;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets){
        double netForceY = 0;
        for (Planet p:allPlanets){
            if(!this.equals(p)){
                netForceY += this.calcForceExertedByY(p);
            }
        }
        return netForceY;
    }
    public void update(double dt, double fX, double fY){
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel += aX * dt;
        this.yyVel += aY * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }
}

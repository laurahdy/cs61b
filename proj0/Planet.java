public class Planet {
    // adding instance variable;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    // adding Body constructor; later can represent a planet, star or various objects in this universe;
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    
    // adding constructor that copy the body object
    public Planet(Planet b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    // add instance method
    // ## use Math.pow() and Math.sqrt()
    public double calcDistance(Planet b) {

        double r_square = Math.pow(b.xxPos - this.xxPos, 2) + Math.pow(b.yyPos - this.yyPos, 2);
        return Math.sqrt(r_square);
    }
    
    // ## decalre a constant that will be used in the class;
    // ## scienctific 10^-11
    //final static double G = 6.67e-11;
    // ## other using 
    private static final double G = 6.67E-11;
    
    public double calcForceExertedBy(Planet b) {
        double r = this.calcDistance(b);
        double force = G * this.mass * b.mass / r / r;
        return force;
    }

    public double calcForceExertedByX(Planet b) {
        double dx = b.xxPos - this.xxPos;
        return this.calcForceExertedBy(b) * dx / this.calcDistance(b);

    }
    
    public double calcForceExertedByY(Planet b) {
        double dy = b.yyPos - this.yyPos;
        return this.calcForceExertedBy(b) * dy / this.calcDistance(b);
    }
    
    //Write methods calcNetForceExertedByX and calcNetForceExertedByY that each take in an array of Bodys and calculates the net X and net Y force exerted by all bodies in that array upon the current Body. For example, consider the code snippet below:
    // ## To compare two bodies, use the .equals method instead of ==: samh.equals(samh) (which would return true)
    public double calcNetForceExertedByX(Planet[] bodies) {
        double net_force_x = 0;
        for (int i = 0; i < bodies.length; i++) {
            if (bodies[i].equals(this)) {
                continue;}

            net_force_x += this.calcForceExertedByX(bodies[i]);
        }
        return net_force_x;
    }

    public double calcNetForceExertedByY(Planet[] bodies) {
        double net_force_y = 0;
        for (int i = 0; i < bodies.length; i++) {
            if (bodies[i].equals(this)) {
                continue;
            }
            net_force_y += this.calcForceExertedByY(bodies[i]);
        }
        return net_force_y;
    }
    
    // write a method update the velocity,acceleration, and poistion of the object, no returns
    public void update(double dt, double Fx, double Fy) {
        double a_x = Fx / this.mass;
        double a_y = Fy / this.mass;

        this.xxVel = this.xxVel + dt * a_x;
        this.yyVel = this.yyVel + dt * a_y;

        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos,"images/"+this.imgFileName);
    }
    
    
}
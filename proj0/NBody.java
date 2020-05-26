public class NBody {
    /** Class to run the simulation, have NO constructor 
     * Goal is to simulate a universe specified in one of the data file
     * some new
    */

    // give a filename return a double of the radius of the universe in that file
    public static double readRadius(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        double radius = in.readDouble();
        return radius;
    }
    
    // returns an array of body
    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        double radius = in.readDouble();
        Planet[] return_bodies = new Planet[N]; // need two new, one is to instaitate arrays; one is for body

        for (int i = 0; i < N; i++) {
            double x_pos = in.readDouble();
            double y_pos = in.readDouble();
            double x_vel = in.readDouble();
            double y_vel = in.readDouble();
            double mass = in.readDouble();
            String img_name = in.readString();

            return_bodies[i] = new Planet(x_pos, y_pos, x_vel, y_vel, mass, img_name);
        }
        return return_bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]); //  ## convert a string to a double format
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] body_list = readPlanets(filename);
        double radius = readRadius(filename);

        //prevent flicking in the animation;
        StdDraw.enableDoubleBuffering();

        // set canvas
        StdDraw.setXscale(-radius, radius); // (0,0) is in the center, radius is the half of the canvas
        StdDraw.setYscale(-radius, radius);

        // declare and instantiate two arrays of Body object
        double[] xForces = new double[body_list.length];
        double[] yForces = new double[body_list.length];

        // for simulation from time 0 to T
        for (double t = 0; t < T; t = t + dt) {

            // get the forces in x and y dimention
            for (int i = 0; i < body_list.length; i++) {
                xForces[i] = body_list[i].calcNetForceExertedByX(body_list);
                yForces[i] = body_list[i].calcNetForceExertedByY(body_list);
            }

            // update the postion of body in the array
            for (int i = 0; i < body_list.length; i++) {
                body_list[i].update(dt, xForces[i], yForces[i]);
            }

            // draw the background
            StdDraw.picture(0, 0, "images/starfield.jpg");

            // draw each body in the array 
            for (int i = 0; i < body_list.length; i++) {
                body_list[i].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

        }

        // final print
        // System.out.println(body_list.length);
        // System.out.println(radius);
        // for (Body b:body_list) {
        //     System.out.print(b.xxPos + ' ');
        //     System.out.print(b.yyPos + ' ');
        //     System.out.print(b.xxVel + ' ');
        //     System.out.print(b.yyVel + ' ');
        //     System.out.print(b.mass + ' ');
        //     System.out.println(b.imgFileName);
        // }

        // ## https://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html#syntax
        // ## formatter
        // final print
        StdOut.printf("%d\n", body_list.length);
        StdOut.printf("%.2e\n", radius); // precions 2 after the floating point
        for (Planet b : body_list) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s \n ",
            b.xxPos,b.yyPos,b.xxVel,b.yyVel,b.mass,b.imgFileName);
        }




    }
}


/**
 * java NBody 157788000.0 25000.0 data/planets.txt
 * javac NBody.java
 * 
 *  
 * */
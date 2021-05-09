public class NBody {
    private static String backgroundImage = "./images/starfield.jpg";

    public static double readRadius(String filePath){
        In in = new In(filePath);
        in.readInt();
        return in.readDouble();
    }
    public static Planet[] readPlanets(String filePath){
        In in = new In(filePath);
        int planets_num = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[planets_num];
        for(int i =0; i < planets_num; i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xP, yP, xV, yV, m, img);
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();

        double t = 0.0; // time variable
        int n = planets.length;
        double[] xForces = new double[n];
        double[] yForces = new double[n];
        while (t < T) {

            /** Calculate net forces for each Planet */
            for (int i = 0; i < n; i++) {
                double fX = planets[i].calcNetForceExertedByX(planets);
                double fY = planets[i].calcNetForceExertedByY(planets);
                xForces[i] = fX;
                yForces[i] = fY;
            }

            /** Update each Planet's members */
            for (int i = 0; i < n; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            /* Show the background */
            StdDraw.picture(0, 0, backgroundImage);

            /** Draw all of the Planets */
            for (Planet p : planets) {
                p.draw();
            }

            /* Shows the drawing to the screen */
            StdDraw.show();
            StdDraw.pause(10);

            t += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }


    }
}

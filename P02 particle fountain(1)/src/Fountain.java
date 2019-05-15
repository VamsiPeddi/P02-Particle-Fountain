//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Particle Fountain Animation
// Files: Fountain.java
// Course: CS 300 SPring 2019
//
// Author: Vamsi Peddi
// Email: vpeddi@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: -
// Online Sources: -
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Random;

public class Fountain {

  private static Random randGen;

  private static Particle[] particles;

  private static double velocityX;

  private static double velocityY;

  private static int positionX; // middle of the screen (left to right): 400
  private static int positionY; // middle of the screen (top to bottom): 300
  private static int startColor; // blue: Utility.color(23,141,235)
  private static int endColor; // lighter blue: Utility.color(23,200,255)


  /**
   * Creates a single particle at position (3,3) with velocity (-1,-2). Then checks whether calling
   * updateParticle() on this particle's index correctly results in changing its position to
   * (2,1.3).
   * 
   * @return true when no defect is found, and false otherwise
   */
  private static boolean testUpdateParticle() {

    boolean testPassed = true;

    particles = new Particle[1];
    particles[0] = new Particle();
    particles[0].setPositionX(3);
    particles[0].setPositionY(3);
    particles[0].setVelocityX(-1);
    particles[0].setVelocityY(-2);

    updateParticle(0);

    float xPos = particles[0].getPositionX();
    float yPos = particles[0].getPositionY();

    if (xPos != 2.0 || yPos != 1.0) {

      testPassed = false;
      System.out.println("FAILED");

    }
    return testPassed;
  }



  /**
   * Calls removeOldParticles(6) on an array with three particles (two of which have ages over six
   * and another that does not). Then checks whether the old particles were removed and the young
   * particle was left alone.
   * 
   * @return true when no defect is found, and false otherwise
   */
  private static boolean testRemoveOldParticles() {
    boolean testPassed = true;
    particles = new Particle[3];
    particles[0] = new Particle();
    particles[1] = new Particle();
    particles[1].setAge(8);
    particles[2] = new Particle();
    particles[2].setAge(19);

    removeOldParticles(6);

    if (particles[1] != null || particles[2] != null) {
      System.out.println("FAILED");
    }


    return testPassed;
  }

  /**
   * Call the test methods and creates the main particles array. Also initializes X Position and Y
   * POsition
   * 
   * no return value
   */
  public static void setup() {

    testUpdateParticle();
    for (int i = 0; i < particles.length; i++) {
      particles[i] = null;
    }

    testRemoveOldParticles();
    for (int i = 0; i < particles.length; i++) {
      particles[i] = null;
    }


    particles = new Particle[800];

    positionX = 400;
    positionY = 300;

  }

  /**
   * Sets the background of the program. Creates 10 new particles each time we call it. This method
   * also makes calls to the updateParticle() method whenever required. This method also has a
   * removeOldParticle() method call
   * 
   * no return value
   */
  public static void update() {
    int color1 = Utility.color(235, 213, 186);
    Utility.background(color1);
    createNewParticles(10);
    for (int i = 0; i < particles.length; i++) {
      if (particles[i] != null) {
        updateParticle(i);
      }
    }
    removeOldParticles(80);
  }

  /**
   * increases age of particle by one each time you call it. Adds the velocity of the x and y
   * particles to their respective positions. Also adds the gravity effect by increasing Y velocity
   * by 0.3. Also decides the shape, size, and color of the particles coming out of the fountain
   * 
   * no return value
   */
  private static void updateParticle(int index) {

    int age = particles[index].getAge();
    particles[index].setAge(age + 1);

    float xPos = particles[index].getPositionX();
    float yPos = particles[index].getPositionY();
    xPos = (float) (xPos + particles[index].getVelocityX());
    yPos = (float) (yPos + particles[index].getVelocityY());
    particles[index].setPositionX(xPos);
    particles[index].setPositionY(yPos);

    particles[index].setVelocityY(particles[index].getVelocityY() + 0.3f);

    Utility.circle(xPos, yPos, 9);
    int color = Utility.color(23, 141, 235);
    Utility.fill(color);
  }

  /**
   * Each time this method is called it creates a given number of particles by the user. The method
   * also assigns important properties to the new particle objects that are being created
   * 
   * no return value
   */
  private static void createNewParticles(int numOfParticles) {

    int a = 0;
    for (int i = 0; i < particles.length; i++) {

      if (a == numOfParticles) {
        break;
      }

      if (particles[i] == null) {
        randGen = new Random();
        particles[i] = new Particle();
        int startColor = Utility.color(23, 141, 235);
        int endColor = Utility.color(23, 200, 255);
        int number = randGen.nextInt(2);

        particles[i].setPositionX(Fountain.positionX - 2.999f + randGen.nextFloat() + 2.999f);
        particles[i].setPositionY(Fountain.positionY - 2.999f + randGen.nextFloat() + 2.999f);

        int size = randGen.nextInt(8) + 4;
        Utility.lerpColor(startColor, endColor, 0.5f);

        particles[i].setVelocityX(-1.0f + 2 * randGen.nextFloat());

        particles[i].setVelocityY(-10.0f - randGen.nextFloat() * (-10.0f + 5.0f));

        particles[i].setAge(randGen.nextInt(41));
        int transparency = randGen.nextInt(96) + 32;
        a++;
      }

    }

  }

  /**
   * This method loops through particles and checks the ages of each particle to see if any particle
   * exceeds the maxAge given by the user. If a particle exceeds the age it is replaced by a null
   * value and them updated.
   * 
   * no return value
   */
  private static void removeOldParticles(int maxAge) {
    for (int i = 0; i < particles.length; i++) {
      if (particles[i] == null) {

      } else {
        int age = particles[i].getAge();
        if (age >= maxAge) {
          particles[i] = null;
        }
      }
    }
  }

  /**
   * Matches the position of the mouse whenever it is clicked to make new fountains of particles on
   * the position of the mouse
   * 
   * no return value
   */
  public static void mousePressed(int xPos, int yPos) {
    Fountain.positionX = xPos;
    Fountain.positionY = yPos;
  }

  /**
   * saves a screenshot of the screen to screenshot.png
   * 
   * no return value
   */
  public static void keyPressed(char keyPress) {
    if (keyPress == 'p' || keyPress == 'P') {
      Utility.save("screenshot.png");
    }
  }


  public static void main(String[] args) {

    Utility.runApplication();


  }

}

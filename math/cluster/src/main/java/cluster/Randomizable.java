
package cluster;

/** 
 * Interface to something that has random behaviour that is able to be
 * seeded with an integer.
 *
 */
public interface Randomizable {

  /**
   * Set the seed for random number generation.
   *
   * @param seed the seed 
   */
  void setSeed(int seed);
  
  /**
   * Gets the seed for the random number generations
   *
   * @return the seed for the random number generation
   */
  int getSeed();
}

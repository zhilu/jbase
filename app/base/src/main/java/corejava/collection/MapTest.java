package corejava.collection;

import java.util.*;

/**
 * This program demonstrates the use of a map with key type String and value type Employee.
 * @version 1.11 2012-01-26
 * @author Cay Horstmann
 */
public class MapTest
{
   public static void main(String[] args)
   {
      Map<String, EmployeeValue> staff = new HashMap<>();
      staff.put("144-25-5464", new EmployeeValue("Amy Lee"));
      staff.put("567-24-2546", new EmployeeValue("Harry Hacker"));
      staff.put("157-62-7935", new EmployeeValue("Gary Cooper"));
      staff.put("456-62-5527", new EmployeeValue("Francesca Cruz"));

      // print all entries

      System.out.println(staff);

      // remove an entry

      staff.remove("567-24-2546");

      // replace an entry

      staff.put("456-62-5527", new EmployeeValue("Francesca Miller"));

      // look up a value

      System.out.println(staff.get("157-62-7935"));

      // iterate through all entries

      for (Map.Entry<String, EmployeeValue> entry : staff.entrySet())
      {
         String key = entry.getKey();
         EmployeeValue value = entry.getValue();
         System.out.println("key=" + key + ", value=" + value);
      }
   }
}
class EmployeeValue
{
   private String name;
   private double salary;

   /**
    * Constructs an employee with $0 salary.
    * @param n the employee name
    */
   public EmployeeValue(String n)
   {
      name = n;
      salary = 0;
   }

   public String toString()
   {
      return "[name=" + name + ", salary=" + salary + "]";
   }
}


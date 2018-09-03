package corejava.io;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;

/**
 * 
 * 列出指定文件夹下的所有文件
 * @version 1.1 2012-05-31
 * @author Cay Horstmann
 */
public class FindDirectories
{
   public static void main(String[] args) throws IOException
   {
      Path dir = Paths.get(args.length == 0 ? System.getProperty("user.home") : args[0]);
      Files.walkFileTree(dir, new SimpleFileVisitor<Path>()
         {
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
            {               
               if (attrs.isDirectory())   // 好像所有的都是文件
                  System.out.println(file);
               return FileVisitResult.CONTINUE;
            }
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException
            {
               return FileVisitResult.CONTINUE;
            }
         });
   }
}

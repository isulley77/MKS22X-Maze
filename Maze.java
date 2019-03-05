import java.util.*;
import java.io.*;


public class Maze{


    private char[][]maze;
    private boolean animate;//false by default
    
    int width;
    int height;

    /*Constructor loads a maze text file, and sets animate to false by default.

      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)

      'S' - the location of the start(exactly 1 per file)

      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!


      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then:

         throw a FileNotFoundException or IllegalStateException

    */

    public Maze(String filename) throws FileNotFoundException{
        
        File map = new File(filename);
        Scanner s = new Scanner(map);
        
        int counter = 0;
        String l;
        
        while(s.hasNextLine()){
            
            l = s.nextLine();
            width = l.length();
            for(int i = 0; i < l.length(); i++){
            
                maze[counter][i] = l.charAt(i);
            }
            
            counter++;
        }
        
        height = counter;
        
       
    }


    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }


    public void setAnimate(boolean b){

        animate = b;

    }


    public void clearTerminal(){

        //erase terminal, go to top left of screen.

        System.out.println("\033[2J\033[1;1H");

    }






   /*Return the string that represents the maze.

     It should look like the text file with some characters replaced.

    */
    public String toString(){
      
      String Smaze = "";
        
        for(int i = 0; i < height; i++){
            
            for(int j = 0; j < width; j++){
            
                Smaze += maze[i][j];
            }
            
            Smaze += "\n";
        }
        
        return Smaze;
    }



    /*Wrapper Solve Function returns the helper function

      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.

    */
    public int solve(){


      for(int i = 0; i < maze.length; i++){
        for(int j = 0; j < maze.length; j++){
          if(maze[i][j] == 'S'){
            
            maze[i][j] = ' ';
            return solve(i, j);
          }
        }
      }
            //find the location of the S.


            //erase the S


            //and start solving at the location of the s.

            //return solve(???,???);
        return -1;
    }

    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.


      Postcondition:

        The S is replaced with '@' but the 'E' is not.

        All visited spots that were not part of the solution are changed to '.'

        All visited spots that are part of the solution are changed to '@'
    */
    private int solve(int row, int col){ //you can add more parameters since this is private

    
        if(maze[row][col] == 'E'){
            return 1;
        }
        
        maze[row][col] = '@';
        int counter;
        System.out.println(toString());
        if(maze[row + 1][col] != '@' && maze[row + 1][col] != '.' && maze[row + 1][col] != '#'){
            counter = solve(row + 1, col);
            if(counter != -1){
                return 1 + counter;
            }
        }
        if(maze[row - 1][col] != '@' && maze[row - 1][col] != '.' && maze[row - 1][col] != '#'){
            counter = solve(row + 1, col);
            if(counter != -1){
                return 1 + counter;
            }
        }
        if(maze[row][col + 1] != '@' && maze[row][col + 1] != '.' && maze[row][col + 1] != '#'){
            counter = solve(row + 1, col);
            if(counter != -1){
                return 1 + counter;
            }
        }
        if(maze[row][col - 1] != '@' && maze[row][col - 1] != '.' && maze[row][col - 1] != '#'){
            counter = solve(row + 1, col);
            if(counter != -1){
                return 1 + counter;
            }
        }
            
        

        //automatic animation! You are welcome.
        if(animate){

            clearTerminal();
            System.out.println(this);

            wait(20);
        }

        //COMPLETE SOLVE
        maze[row][col] = '.';
        return -1; //so it compiles
    }


}

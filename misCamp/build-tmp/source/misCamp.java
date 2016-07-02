import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class misCamp extends PApplet {

boolean whoGetChess = true;

// \u5132\u5b58\u68cb\u76e4\u5404\u4f4d\u7f6e\u984f\u8272
int checkerboard[][] = new int[9][9];

public void setup() {

  
  background(217,170,116);
  strokeWeight(3);
  line(50, 50, 450 , 50);

}

public void draw() {

  // \u756b\u68cb\u76e4
  for(int i = 0; i < 9; i++){
    // \u8a2d\u5b9a\u7c97\u5ea6
    strokeWeight(3);
    // \u6a6b\u7dda
    line(50, 50+50*i, 450 , 50+50*i);
    // \u76f4\u7dda
    line(50+50*i , 50,50+50*i,450);
  }

  judge();
}

public void mousePressed(){
  // \u68cb\u5b50\u4f4d\u7f6e
  int targetX = -100;
  int targetY = -100;

  // \u68cb\u5b50\u984f\u8272
  int blackChess = 0;
  int whiteChess = 255;
  // \u9ed1\u5b50\u5148\u884c
  int chessColor = blackChess;

  for(int i = 0; i < 10; i++ ){
    if(mouseX > 75+50*(i-1) && mouseX <  75+50*i){
      targetX = 50+50*i;
    }
    if(mouseY > 75+50*(i-1) && mouseY <  75+50*i){
      targetY = 50+50*i;
    }
  }

  // \u5224\u65b7\u57f7\u5b50\u65b9\u984f\u8272
  if(whoGetChess){
    chessColor = blackChess;
    checkerboard[(targetX-50)/50][(targetY-50)/50] = 1;
  }else{
    chessColor = whiteChess;
    checkerboard[(targetX-50)/50][(targetY-50)/50] = 2;
  }

  // \u5efa\u7acb\u4e00\u500b\u68cb\u5b50\u7684\u7269\u4ef6
  // \u4e26\u8a2d\u5b9a\u5176\u5ea7\u6a19\u4ee5\u53ca\u984f\u8272
  Chess newChess = new Chess(chessColor,targetX,targetY);
  newChess.getChess();


  // \u8b8a\u66f4\u57f7\u5b50\u8005\u984f\u8272
  whoGetChess = !whoGetChess;
  for (int[] o : checkerboard) {
    println("o: "+o);
  }
  // println("checkerboard: "+checkerboard);
}

public void judge(){
  for(int i = 0;i< 4;i++){
    for(int j = 0;j< 4;j++){
      // \u8a08\u7b97\u9023\u7e8c\u68cb\u5b50\u6578
      int countCheer = 0;

      for(int k = 0;k<5;k++){
        if(checkerboard[i][j] != 0){
          if(checkerboard[i][j] == checkerboard[i+k][j]){
            countCheer++;
          }
          if(countCheer>=5){
            background(0);
          }
        }
      }
    }
  }
}

class Chess{
  int chessColor = 0;
  int targetX = 0;
  int targetY = 0;
  Chess(int chessColor,int targetX,int targetY){
    this.chessColor = chessColor;
    this.targetX = targetX;
    this.targetY = targetY;
  }
  public void getChess(){
    fill(chessColor);  // Use color variable 'c' as fill color
    noStroke();  // Don't draw a stroke around shapes
    ellipse(targetX, targetY, 40, 40);
  }
}
  public void settings() {  size( 600, 500 ); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "misCamp" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

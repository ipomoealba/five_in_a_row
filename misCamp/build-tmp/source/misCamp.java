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

// \u5224\u65b7\u57f7\u5b50\u65b9
boolean whoGetChess = true;

// \u5132\u5b58\u68cb\u76e4\u5404\u4f4d\u7f6e\u984f\u8272
int checkerboard[][] = new int[9][9];

// \u8a08\u7b97\u9023\u7e8c\u68cb\u5b50\u6578
int countCheerX = 0;  //  X\u8ef8
int countCheerY = 0;  //  Y\u8ef8
int countCheerS = 0;  //  \u659c\u5411

// \u521d\u59cb\u8a2d\u5b9a
public void setup() {

  // \u756b\u5e03\u5927\u5c0f
  
  // \u80cc\u666f\u8272
  background(217,170,116);

}

// \u4e0d\u65b7\u66f4\u65b0
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

  // fill(220,220,220);
  // noStroke();
  // rect(470, 50, 100, 70, 7);
  // judge();
}

public void mousePressed(){

  if(mouseX > 470 || mouseY > 470){
    // \u8d85\u51fa\u68cb\u76e4 \u7121\u53cd\u61c9
  }else if (mouseY < 50 || mouseX < 50) {
    // \u8d85\u51fa\u68cb\u76e4 \u7121\u53cd\u61c9
  }else{
    // \u5728\u68cb\u76e4\u5167
    // \u68cb\u5b50\u4f4d\u7f6e
    int targetX = -100;
    int targetY = -100;

    // \u68cb\u5b50\u984f\u8272
    int blackChess = 0;
    int whiteChess = 255;

    // \u9ed1\u5b50\u5148\u884c
    int chessColor = blackChess;


    for(int i = 0; i < 9; i++ ){

      if(mouseX > 75+50*(i-1) && mouseX <  75+50*i){
        targetX = 50+50*i;
      }
      if(mouseY > 75+50*(i-1) && mouseY <  75+50*i){
        targetY = 50+50*i;
      }
    }

    // \u5224\u65b7\u57f7\u5b50\u65b9\u984f\u8272
    if(checkerboard[(targetX-50)/50][(targetY-50)/50] == 0){

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

    }

    judge();
    // ================ \u6e2c\u8a66\u68cb\u76e4\u72c0\u614b =========================
    for (int i = 0 ; i < 9; i++) {
      for(int j = 0 ;j < 9; j++){
        print("| "+checkerboard[j][i]);
        if(j==8){
          println(" |");
          println("-------------------------------------");
        }else{
          print(" ");
        }
      }
    }
    println("=====================================");
    println("");
    // ======================================================
  }

}

int counter = 0;
public void judge(){
  // for(int i = 0;i<9;i++){
    for(int i = 0 ;i < 9 ;i++){
      compareX(0,i);
      compareY(i,0);
    }

  // for(int i = 0;i< 9;i++){

  //   for(int j = 0;j< 9;j++){

  //     // for(int k = 0;k<5;k++){

  //       if(checkerboard[i][j] != 0){

  //         if (i-1 > 0 && j-1 > 0) {
  //           if(i+1 < 9 && j+1 < 9){
  //           if( checkerboard[i][j] == checkerboard[i-1][j]){
  //             println("i: "+i);
  //             println("checkerboard[i][j]: "+checkerboard[i][j]);
  //             println("j: "+j);
  //             println("checkerboard[i][j-1]: "+checkerboard[i][j-1]);
  //             countCheerX++;
  //           }

  //         if( checkerboard[i][j] == checkerboard[i][j-k]){
  //           countCheerY++;
  //         }

  //         }

  //         println("countCheerX: "+countCheerX);
  //         println("countCheerY: "+countCheerY);

  //         if(countCheerX >=5 && countCheerY >= 5){
  //           background(0);
  //         }

  //       }
  //     }
  //   // }

  //   }
  // }
  countCheerX = 0;
  countCheerY = 0;
  countCheerS = 0;
}

public void compareX(int i , int j){
  if(i < 8 && j < 8){

      if(checkerboard[i][j] == checkerboard[i+1][j]){
        if(checkerboard[i][j] != 0){
            countCheerX++;

        }
        compareX(i+1,j);
      }else {
        println("countCheerX: "+countCheerX);
        countCheerX = 0;
        if(i+1 < 8){
          compareX(i,j);
        }
      }
      winner();
  }
}

public void compareY(int i , int j){
  if(j < 8 && i < 8){
      if(checkerboard[i][j+1] == checkerboard[i][j+1] ){
         if(checkerboard[i][j] != 0){
        println("countCheerY: "+countCheerY);
          countCheerY++;
          }
          compareY(i,j+1);

      }else{
        println("countCheerY: "+countCheerY);
        countCheerY = 0;

        if(j+1 < 8){
          compareY(i,j);
        }
      }
      winner();
  }

}

public void winner(){
  if(countCheerX >=5 || countCheerY >= 5){
     background(0);
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

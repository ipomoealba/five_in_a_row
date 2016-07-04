// 判斷執子方
boolean whoGetChess = true;

// 儲存棋盤各位置顏色
int checkerboard[][] = new int[9][9];

// 計算連續棋子數
int countCheerX = 0;  //  X軸
int countCheerY = 0;  //  Y軸
int countCheerS = 0;  //  斜向

// 初始設定
void setup() {

  // 畫布大小
  size( 600, 500 );
  // 背景色
  background(217,170,116);

}

// 不斷更新
void draw() {

  // 畫棋盤
  for(int i = 0; i < 9; i++){
    // 設定粗度
    strokeWeight(3);
    // 橫線
    line(50, 50+50*i, 450 , 50+50*i);
    // 直線
    line(50+50*i , 50,50+50*i,450);
  }

  // fill(220,220,220);
  // noStroke();
  // rect(470, 50, 100, 70, 7);
  // judge();
}

void mousePressed(){

  if(mouseX > 470 || mouseY > 470){
    // 超出棋盤 無反應
  }else if (mouseY < 50 || mouseX < 50) {
    // 超出棋盤 無反應
  }else{
    // 在棋盤內
    // 棋子位置
    int targetX = -100;
    int targetY = -100;

    // 棋子顏色
    int blackChess = 0;
    int whiteChess = 255;

    // 黑子先行
    int chessColor = blackChess;


    for(int i = 0; i < 9; i++ ){

      if(mouseX > 75+50*(i-1) && mouseX <  75+50*i){
        targetX = 50+50*i;
      }
      if(mouseY > 75+50*(i-1) && mouseY <  75+50*i){
        targetY = 50+50*i;
      }
    }

    // 判斷執子方顏色
    if(checkerboard[(targetX-50)/50][(targetY-50)/50] == 0){

      if(whoGetChess){
        chessColor = blackChess;
        checkerboard[(targetX-50)/50][(targetY-50)/50] = 1;
      }else{
        chessColor = whiteChess;
        checkerboard[(targetX-50)/50][(targetY-50)/50] = 2;
      }

      // 建立一個棋子的物件
      // 並設定其座標以及顏色
      Chess newChess = new Chess(chessColor,targetX,targetY);
      newChess.getChess();

      // 變更執子者顏色
      whoGetChess = !whoGetChess;

    }

    judge();
    // ================ 測試棋盤狀態 =========================
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
void judge(){
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

void compareX(int i , int j){
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

void compareY(int i , int j){
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

void winner(){
  if(countCheerX >=5 || countCheerY >= 5){
     background(0);
  }
}

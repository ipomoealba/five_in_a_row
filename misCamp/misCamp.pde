boolean whoGetChess = true;

// 儲存棋盤各位置顏色
int checkerboard[][] = new int[9][9];

void setup() {

  size( 600, 500 );
  background(217,170,116);
  strokeWeight(3);
  line(50, 50, 450 , 50);

}

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

  judge();
}

void mousePressed(){
  // 棋子位置
  int targetX = -100;
  int targetY = -100;

  // 棋子顏色
  int blackChess = 0;
  int whiteChess = 255;
  // 黑子先行
  int chessColor = blackChess;

  for(int i = 0; i < 10; i++ ){
    if(mouseX > 75+50*(i-1) && mouseX <  75+50*i){
      targetX = 50+50*i;
    }
    if(mouseY > 75+50*(i-1) && mouseY <  75+50*i){
      targetY = 50+50*i;
    }
  }

  // 判斷執子方顏色
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
  for (int[] o : checkerboard) {
    println("o: "+o);
  }
  // println("checkerboard: "+checkerboard);
}

void judge(){
  for(int i = 0;i< 4;i++){
    for(int j = 0;j< 4;j++){
      // 計算連續棋子數
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


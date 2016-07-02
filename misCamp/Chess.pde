class Chess{
  int chessColor = 0;
  int targetX = 0;
  int targetY = 0;
  Chess(int chessColor,int targetX,int targetY){
    this.chessColor = chessColor;
    this.targetX = targetX;
    this.targetY = targetY;
  }
  void getChess(){
    fill(chessColor);  // Use color variable 'c' as fill color
    noStroke();  // Don't draw a stroke around shapes
    ellipse(targetX, targetY, 40, 40);
  }
}

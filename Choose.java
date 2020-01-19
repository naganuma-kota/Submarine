public class Choose{
    private double[][][] attackMap = new double[6][6][4];//攻撃用マップ
    private double[][] moveMap = new double[6][6];//移動用マップ
    private int[][] myPlace = new int [6][6]; //味方の位置（-１は死亡,０は空白,それ以外はHP）
    private int[][] ShipNumber = {{3,1},{4,1},{3,4},{5,5}};//味方のyx座標を保存
    private Method m = new Method();
    //ただし、0行0列は使用しない（現実の指示と配列番号を対応させるため）


    public Choose(){
        for(int i=0;i<4;i++){//初期位置の記憶
                myPlace[ShipNumber[i][0]][ShipNumber[i][1]] = 3;
        }
    }
    public void attack(int x,int y){//攻撃を受けた時に実行
        //Method m = new Method();
        String result = damege(x, y);
        m.M11(attackMap, x, y);
        m.M21(moveMap, x, y, result);
        m.M22(moveMap, x, y, result);
        
    }
    public void move(int dx,int dy,int range){//相手が移動したときに実行
        //Method m = new Method();
        dx = dx*range;
        dy = dy*range;
        m.M15(attackMap,dx,dy);
        m.M23(moveMap,dx,dy);
    }
    public void myTurn(){//自分の行動を判断する
        //Method m = new Method();
        int[] A = m.M31(myPlace,attackMap);
        int[] T = m.M32(myPlace, moveMap);
        m.M33(myPlace,attackMap, moveMap, A, T);
        
    }
    public void information(){//現在の情報を表示
        System.out.println("myPlace");
        for(int y = 1;y<6;y++){
            for(int x = 1;x<6;x++){
                System.out.print(myPlace[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println("attackMap");
        for(int y = 1;y<6;y++){
            for(int x = 1;x<6;x++){
                System.out.print(attackMap[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println("moveMap");
        for(int y = 1;y<6;y++){
            for(int x = 1;x<6;x++){
                System.out.print(moveMap[y][x] + " ");
            }
            System.out.println();
        }

    }
    public String damege(int x,int y){//自分が相手の攻撃によってダメージを受けたか判定・処理する
        //TODO:変数に日本語を用いているため、英語に置き換える
        if(myPlace[y][x]>1){
            System.out.println("命中");
            myPlace[y][x]--;
            return "命中";
        }else if (myPlace[y][x]==1){
            System.out.println("命中、撃沈");
            myPlace[y][x]= -1;
            return "命中、撃沈";
        }else{
            boolean flag =false;
            outside: for(int dy=-1;dy<2;dy++){
                for(int dx=-1;dx<2;dx++){
                    try {
                        if(myPlace[y+dy][x+dx]>0){
                            flag = true;
                            break outside;
                        }
                    } catch (Exception e) {
                        continue;
                    }   
                }
            }
            if(flag){
                System.out.println("波高し");
                return "波高し";
            }else{
                System.out.println("はずれ");
                return "はずれ";
            }       
        }
    }
    public void setMyPlace(int x,int y,int n){
        myPlace[y][x] = n;
    }
}
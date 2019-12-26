public class Choose{
    private double[][] attackMap = new double[6][6];//攻撃用マップ
    private double[][] moveMap = new double[6][6];//移動用マップ
    private int[][] myPlace = new int [6][6]; //味方の位置（-１は死亡,０は空白,それ以外はHP）
    private int[][] ShipNumber = {{1,3},{1,4},{4,3}};//味方のxy座標を保存
    //ただし、0行0列は使用しない（現実の指示と配列番号を対応させるため）


    public Choose(){
        System.out.println("Choose class is waked"); 
        for(int i=0;i<3;i++){//初期位置の記憶
                myPlace[ShipNumber[i][1]][ShipNumber[i][0]] = 3;
        }
    }
    public void attack(int x,int y){//攻撃を受けた時に実行
        Method m = new Method();
        String result = damege(x, y);
        m.M11(attackMap, x, y);
        m.M21(moveMap, x, y, result);
        m.M22(moveMap, x, y, result);
        
    }
    public void move(int dx,int dy,int range){//相手が移動したときに実行
        Method m = new Method();
        m.M15(attackMap);
    }
    public void myTurn(){//自分の行動を判断する
        Method m = new Method();
        int[] aMax = m.M31(myPlace,attackMap);
        int[] mMax = m.M32(myPlace, moveMap);
        m.M33(myPlace,attackMap, moveMap, aMax, mMax);
        
    }
    public void information(){//現在の情報を表示
        System.out.println("myPlace");
        for(int i = 1;i<6;i++){
            for(int j = 1;j<6;j++){
                System.out.print(myPlace[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println("attackMap");
        for(int i = 1;i<6;i++){
            for(int j = 1;j<6;j++){
                System.out.print(attackMap[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println("moveMap");
        for(int i = 1;i<6;i++){
            for(int j = 1;j<6;j++){
                System.out.print(moveMap[j][i] + " ");
            }
            System.out.println();
        }

    }
    public String damege(int x,int y){//自分が相手の攻撃によってダメージを受けたか判定・処理する
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
            outside: for(int k=-1;k<2;k++){
                for(int l=-1;l<2;l++){
                    try {
                        if(myPlace[y+k][x+l]>0){
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
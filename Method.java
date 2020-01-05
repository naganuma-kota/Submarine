import java.util.Scanner;
public class Method{
    public void M11(double[][] attackMap, int x, int y){
        for(int i=-1;i<2;i++){  
            for(int j=-1;j<2;j++){
                try {
                    if(y+i>=1&&y+i<=5&&x+j>=1&&x+j<=5){
                        attackMap[y+i][x+j]++;
                    }    
                } catch (Exception e) {
                    continue;
                }
            }
        }
        attackMap[y][x]=0;
    }
    public void M12(double[][] attackMap, int x,int y){
        M11(attackMap, x, y);
    }
    public void M13(double[][]attackMap,int x,int y){
        for(int i=-1;i<2;i++){  
            for(int j=-1;j<2;j++){
                try {
                    if(y+i>=1&&y+i<=5&&x+j>=1&&x+j<=5){
                        attackMap[y+i][x+j]=0;
                    }    
                } catch (Exception e) {
                    continue;
                }
            }
        }
    }
    public void M14(double[][]attackMap,int x,int y){
        attackMap[y][x]=attackMap[y][x]+5;
    }
    public void M14_2(int[][]myPlace,int x,int y){
        myPlace[y][x] = -1;
    }
    public void M15(double[][]attackMap){//未実装
        for(int x=1;x<=5;x++){
            for(int y=1;y<=5;y++){
                attackMap[y][x]=attackMap[y][x]+0.5;
            }
        }
    }
    public void M21(double[][] moveMap, int x,int y, String result){
        if(result.equals("波高し")||result.equals("はずれ")){ 
            for(int i=-2;i<3;i++){  
                for(int j=-2;j<3;j++){
                    try {
                        if(y+i>=1&&y+i<=5&&x+j>=1&&x+j<=5){
                            moveMap[y+i][x+j]=moveMap[y+i][x+j]+0.5;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                    
                }
            }
            for(int i=-1;i<2;i++){  
                for(int j=-1;j<2;j++){
                    try {
                        if(y+i>=1&&y+i<=5&&x+j>=1&&x+j<=5){
                            moveMap[y+i][x+j]=moveMap[y+i][x+j]+0.5;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                    
                }
            }
        }                     

    }
    public void M22(double[][] moveMap, int x, int y, String result){
        if(result.equals("命中")||result.equals("命中、撃沈")){
            moveMap[y][x] =moveMap[y][x]+5;
        }
    }
    public void M23(){//未実装

    }
    public int[] M31(int[][] myPlace,double[][]attackMap){
        int[] aMax = new int[2];
        for(int i=1;i<=5;i++){
            for(int j=1;j<=5;j++){
                if(myPlace[j][i]>=1){
                    for(int k=-1;k<2;k++){  
                        for(int l=-1;l<2;l++){
                            try {
                                if(j+k>=1&&j+k<=5&&i+l>=1&&i+l<=5){
                                    if(myPlace[j+k][i+l]==0){
                                        if(attackMap[j+k][i+l]>attackMap[aMax[0]][aMax[1]]){
                                            aMax[0]=j+k;
                                            aMax[1]=i+l;
                                        }
                                    }
                                }    
                            } catch (Exception e) {
                                continue;
                            }
                        }
                    }
                }
            }
        }
        return aMax;
    }
    public int[] M32(int[][] shipNumber, double[][] moveMap){
        int[] T = new int[2];
        double max = 0;
        for(int i=0;i<4;i++){
            if(moveMap[shipNumber[i][0]][shipNumber[i][1]]>max){
                max = moveMap[shipNumber[i][0]][shipNumber[i][1]];
                T[0] = shipNumber[i][0];
                T[1] = shipNumber[i][1];
            }
        }
        System.out.println("t="+T[0]+","+T[1]+ ","+ max);
        return T;
    }
    public void M33(int[][] myPlace,double[][] attackMap,double[][] moveMap,int[] aMax,int[]mMax){
        if(attackMap[aMax[0]][aMax[1]]>=moveMap[mMax[0]][mMax[1]]){
            //System.out.println("移動元マス"+aMax[0]+ ", "+aMax[1]);
            M34(myPlace,attackMap,aMax);
        }else{
            System.out.println("移動元マス" + mMax[0] +", " + mMax[1]);
            M35(myPlace,moveMap,mMax);
        }
    }
    public void M34(int[][]myPlace,double[][]attackMap,int[] aMax){
        System.out.println(aMax[0] + "," + aMax[1] + "マスに攻撃");
        react(myPlace,attackMap,aMax[0],aMax[1]);
    }
    public void M35(int[][] myPlace,double[][] moveMap,int[] T){
        //TODO:現状半径2マスが移動範囲とされているため、十字型に作り直す
        //FIXME:xとyの順番がぐちゃぐちゃなので治す
        //TODO:mMaxとTなどど変数に統一性がないので改善する
        int[] min = {100,100};
        int x;int y;
        y = 0;
        for(x=-2;x<=2;x++){
            try {
                if(T[1]+x > 0){
                    if(min[1]!=100){
                        if(moveMap[T[0]+y][T[1]+x]<moveMap[min[0]][min[1]]){
                            if(myPlace[T[0]+y][T[1]+x]==0&&T[1]+x>0){
                                min[0]=T[0]+y;
                                min[1]=T[1]+x;
                            }
                        }    
                    }else{
                        min[0]=T[0]+y;
                        min[1]=T[1]+x;
                    }
                }
            } catch (Exception e) {
                continue;
            }
        }
        x = 0;
        for(y=-2;y<=2;y++){
            try {
                if(T[0]+y > 0){
                    if(min[0]!=100){
                        if(moveMap[T[0]+y][T[1]+x]<moveMap[min[0]][min[1]]){
                            if(myPlace[T[0]+y][T[1]+x]==0&&T[0]+y > 0){
                                min[0]=T[0]+y;
                                min[1]=T[1]+x;
                            }
                        }
                    }else{
                        min[0]=T[0]+y;
                        min[1]=T[1]+x;
                    }
                }
            } catch (Exception e) {
                continue;
            }
        }
        System.out.println("移動先マス"+min[0]+","+min[1]);
        shift(myPlace, T[0], T[1], min[0], min[1]);


        /** 
        for(int i=-1;i<2;i++){  //周囲16マスを探索している
            for(int j=-1;j<2;j++){
                try {//指定したマスが存在しない場合があるからその場合はスキップする
                    if(T[0]+i>=1&&T[0]+i<=5&&T[1]+j>=1&&T[1]+j<=5){
                        if(moveMap[T[0]+i][T[1]+j]<moveMap[min[0]][min[1]]){
                            if(myPlace[T[0]+i][T[1]+j]==0){
                                min[0]=T[0]+i;
                                min[1]=T[1]+j;
                            }
                        }
                    }    
                } catch (Exception e) {
                    continue;
                }
            }
        }
        for(int a=0;a<=1;a++){
            if(min[a]<=0){
                min[a]=1;
            }
        }
        System.out.println("移動先マス"+min[0]+", "+min[1]);
        if(myPlace[min[0]][min[1]]==0){
            shift(myPlace, T[1],T[0], min[1], min[0]);
        }else{
            System.out.println("移動先が重なりました。");
        }**/
        
    }
    public void react(int[][]myPlace,double[][]attackMap,int x,int y){//相手の反応を入力、指示するメソッド
        Scanner sc = new Scanner(System.in);
        System.err.println("相手の反応を次のコマンドから入力してください");
        System.err.println("a:波高し");
        System.err.println("b:はずれ");
        System.err.println("c:命中");
        System.err.println("d:命中、撃沈");
        String reaction =sc.next();
        switch(reaction){
            case "a":
                M12(attackMap, x, y);
                break;
            case "b":
                M13(attackMap, x, y);
                break;
            case "c":
                M14(attackMap, x, y);
                break;
            case "d":
                M14_2(myPlace, x, y);
                break;
        }
    }
    public void shift(int[][]myPlace,int Fx,int Fy,int Nx,int Ny){//完成版
        int mx = Nx - Fx;
        int my = Ny - Fy;
        if(mx>0&&my==0){
            System.out.println("東に"+ mx +"マス移動");
        }else if(mx<0&&my==0){
            System.out.println("西に"+ -1*mx +"マス移動");
        }else if(mx==0&&my>0){
            System.out.println("南に"+ my +"マス移動");
        }else if(mx==0&&my<0){
            System.out.println("北に"+ -1*my +"マス移動");
        }else{
            System.err.println("shiftメソッドよりエラーです");
            return;
        }
        myPlace[Ny][Nx] = myPlace[Fy][Fx];
        myPlace[Fy][Fx] = 0;
    }
}
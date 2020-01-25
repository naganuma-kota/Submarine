import java.util.Scanner;
public class Method{
    public void M11(double[][] attackMap, int x, int y){
        for(int dy=-1;dy<2;dy++){  
            for(int dx=-1;dx<2;dx++){
                try {
                    if(y+dy>=1&&y+dy<=5&&x+dx>=1&&x+dx<=5){
                        attackMap[y+dy][x+dx]++;
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
        for(int dy=-1;dy<=1;dy++){  
            for(int dx=-1;dx<=1;dx++){
                try {
                    if(y+dy>=1&&y+dy<=5&&x+dx>=1&&x+dx<=5){
                        attackMap[y+dy][x+dx]=0;
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
    public void M15(double[][]attackMap,int dx,int dy){
        //方針:すべてを*0.5したあと、別のマップを生成し、そこに移動後の値を入れていく。最後にミラーリングする。
        for(int x=1;x<=5;x++){
            for(int y=1;y<=5;y++){
                attackMap[y][x]=attackMap[y][x]*0.5;
            }
        }
        double[][] tmpMap = new double[6][6];
        for(int y=1;y<=5;y++){
            for(int x=1;x<=5;x++){
                try {
                    tmpMap[y+dy][x+dx]=attackMap[y][x];
                } catch (Exception e) {
                    continue;
                }
            }
        }
        for(int y=1;y<=5;y++){
            for(int x=1;x<=5;x++){
                attackMap[y][x]=tmpMap[y][x];
            }
        }
        

    }
    public void M21(double[][] moveMap, int x,int y, String result){
        if(result.equals("波高し")||result.equals("はずれ")){ 
            for(int dy=-2;dy<=2;dy++){  
                for(int dx=-2;dx<=2;dx++){
                    try {
                        if(y+dy>=1&&y+dy<=5&&x+dx>=1&&x+dx<=5){
                            moveMap[y+dy][x+dx]=moveMap[y+dy][x+dx]+0.5;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                    
                }
            }
            for(int dy=-1;dy<=1;dy++){  
                for(int dx=-1;dx<=1;dx++){
                    try {
                        if(y+dy>=1&&y+dy<=5&&x+dx>=1&&x+dx<=5){
                            moveMap[y+dy][x+dx]=moveMap[y+dy][x+dx]+0.5;
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
    public void M23(double[][]moveMap,int dx,int dy){
        for(int x=1;x<=5;x++){
            for(int y=1;y<=5;y++){
                moveMap[y][x]=moveMap[y][x]*0.5;
            }
        }
        double[][] tmpMap = new double[6][6];
        for(int y=1;y<=5;y++){
            for(int x=1;x<=5;x++){
                try {
                    tmpMap[y+dy][x+dx]=moveMap[y][x];
                } catch (Exception e) {
                    continue;
                }
            }
        }
        for(int y=1;y<=5;y++){
            for(int x=1;x<=5;x++){
                moveMap[y][x]=tmpMap[y][x];
            }
        }
    }
    public int[] M31(int[][] myPlace,double[][]attackMap){
        int[] A = new int[2];
        for(int y=1;y<=5;y++){
            for(int x=1;x<=5;x++){
                if(myPlace[y][x]>=1){
                    for(int dy=-1;dy<=1;dy++){  
                        for(int dx=-1;dx<=1;dx++){
                            try {
                                if(x+dy>=1&&x+dy<=5&&y+dx>=1&&y+dx<=5){
                                    if(myPlace[y+dy][x+dx]==0){
                                        if(attackMap[y+dy][x+dx]>attackMap[A[0]][A[1]]){
                                            A[0]=y+dy;
                                            A[1]=x+dx;
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
        return A;
    }
    public int[] M32(int[][] myPlace, double[][] moveMap){
        int[] T= new int[2];
        for(int y=1;y<=5;y++){
            for(int x=1;x<=5;x++){
                if(myPlace[y][x]>0){
                    if(moveMap[y][x]>=moveMap[T[0]][T[1]]){//暫定的に>=を使用している
                        T[0]=y;
                        T[1]=x;
                    }
                }
            }
        }
        return T;
    }
    public void M33(int[][] myPlace,double[][] attackMap,double[][] moveMap,int[] A,int[]T){
        if(attackMap[A[0]][A[1]]>=moveMap[T[0]][T[1]]){
            M34(myPlace,attackMap,A);
        }else{
            //System.out.println("移動元マス" + T[0] +", " + T[1]);
            M35(myPlace,moveMap,T);
        }
    }
    public void M34(int[][]myPlace,double[][]attackMap,int[] A){
        System.out.println(A[0] + "," + A[1] + "マスに攻撃");
        react(myPlace,attackMap,A[1],A[0]);
    }
    public void M35(int[][] myPlace,double[][] moveMap,int[] T){
        //FIXME:minが(100,100)で受け渡されたときの処理が出来ていない！！！
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
        //System.out.println("移動先マス"+min[0]+","+min[1]);
        shift(myPlace, T[1], T[0], min[1], min[0]);        
    }
    public void react(int[][]myPlace,double[][]attackMap,int x,int y){
        Scanner sc = new Scanner(System.in);
        System.err.println("相手の反応を次のコマンドから入力してください");
        System.err.println("a:波高し");
        System.err.println("b:はずれ");
        System.err.println("c:命中");
        System.err.println("d:命中、撃沈");
        String reaction =sc.next();
        sc.close();
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
    public void shift(int[][]myPlace,int Fx,int Fy,int Nx,int Ny){
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
            System.err.println("shiftメソッドに不正な値が渡されました。");
            return;
        }
        myPlace[Ny][Nx] = myPlace[Fy][Fx];
        myPlace[Fy][Fx] = 0;
    }
}
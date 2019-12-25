import java.util.Scanner;
public class Interface{
    public static void main(String args[]){
        String command; //コマンド
        int x;
        int y;
        String direction;
        Choose c = new Choose();
        Scanner sc = new Scanner(System.in);
        while(true){
            command=""; direction=""; x=0; y=0;//初期化
            System.err.print("Please,imput command: ");
            try {
                command =sc.next();
                if(command.equals("ak")){
                    try {
                        x = sc.nextInt();
                        y = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("input error");
                        continue;
                    }
                    ak(c,x,y);
                }else if(command.equals("mv")){
                    try {
                        direction = sc.next();
                        x =sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("input error");
                        continue;
                    }
                    mv(c,direction,x);
                }else if(command.equals("exit")){
                    exit();
                }else if(command.equals("next")){
                    c.myTurn();
                }else if(command.equals("info")){
                    c.information();
                }else{
                    System.out.println("command error");
                    continue;
            }
            } catch (Exception e) {
                System.out.println("system error");
                continue;
            }   
        }
    }

    public static void ak(Choose c,int x,int y){//攻撃コマンド
        if((x>=1 && x<=5)&&(y>=1 && y<=5)){//入力したxy座標が適正か判定
            System.out.println("OK");
            c.attack(x,y);
        }else{
            System.out.println("x or y value is wrong");
            return;
        }
    }

    public static void mv(Choose c,String direction,int range) {//移動コマンド
        int dx = 0;
        int dy = 0;
        switch (direction) {
            case "n":
                dy = -1;
                break;
            case "s":
                dy = 1;
                break;
            case "e":
                dx = 1;
                break;
            case "w":
                dx = -1;
                break;
            default:
                System.out.println("command error");
                return;
        }
        c.move(dx, dy, range);
    }

    public static void exit(){//システム終了判定
            System.out.println("goodby");
            System.exit(0);
    }
}
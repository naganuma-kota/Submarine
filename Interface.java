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
            System.out.println("-------------------------------------------------------------------");
            System.out.println("ak 数値(y座標)　数値(x座標)　：相手からの攻撃の際にその位置とともに入力");
            System.out.println("mv 文字列(方角)　数値(移動マス数)　：相手が移動した際に方角をn,s,e,wで、移動マスを1,2の数字で入力");
            System.out.println("next　：akもしくはmvコマンドを使用した後、自分を行動を表示させるために使用する");
            System.out.println("info　：自機の位置、攻撃用マップ、移動用マップを表示する");
            System.out.println("exit　：プログラムを終了する");
            System.out.println("-------------------------------------------------------------------");
            System.err.print("Please,imput command: ");
            try {
                command =sc.next();
                if(command.equals("ak")){
                    try {
                        y = sc.nextInt();
                        x = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("引数が違います");
                        continue;
                    }
                    ak(c,x,y);
                }else if(command.equals("mv")){
                    try {
                        direction = sc.next();
                        x =sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("引数が違います");
                        continue;
                    }
                    mv(c,direction,x);
                }else if(command.equals("exit")){
                    sc.close();
                    exit();
                }else if(command.equals("next")){
                    c.myTurn();
                }else if(command.equals("info")){
                    c.information();
                }else{
                    System.out.println("コマンドが違います");
                    continue;
            }
            } catch (Exception e) {
                System.out.println("システムエラーです。Σ(ﾟДﾟ；");
                continue;
            }   
        }
    }

    public static void ak(Choose c,int x,int y){//攻撃コマンド
        if((x>=1 && x<=5)&&(y>=1 && y<=5)){//入力したxy座標が適正か判定
            c.attack(x,y);
        }else{
            System.out.println("座標の値が不正です");
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
                System.out.println("方角の値が違います");
                return;
        }
        c.move(dx, dy, range);
    }

    public static void exit(){//システム終了判定
            System.out.println("goodby");
            System.exit(0);
    }
}
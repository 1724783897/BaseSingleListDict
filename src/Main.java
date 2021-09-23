
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String BaseUrl = "C:\\Users\\Lenovo\\Desktop\\BaseSingleListDict\\data\\";
        String pathCet4word =  BaseUrl + "cet4word.txt";
        Dict l1 = new Dict();
        int load = l1.load(pathCet4word);
        if(load == 0){
            System.out.println("数据加载完毕...");
            int ch = -1;
            while (ch != 0){
                System.out.println("--------------------------四级英语字典--------------------------");
                System.out.println("                ID  Name");
                System.out.println("                0   退出");
                System.out.println("                1   显示内容");
                System.out.println("                2   查询单词");
                System.out.println("                3   删除单词");
                System.out.println("                4   插入单词");
                System.out.println("                5   历史查询");
                System.out.println("                6   保存文件");
                System.out.println("-------------------------------------------------------------");
                System.out.print("请选择您想要执行的指令，输入ID即可: ");
                try {
                    ch = Integer.parseInt(scan.next());

                }catch (Exception e){
                    System.out.println("您没有输入正确ID 程序退出");
                    ch = 0;
                }

                switch (ch){

                    case 1:
                        l1.show();
                        break;

                    case 2:
                        System.out.print("请输入您要查询的单词: ");
                        String findValue = scan.next();
                        System.out.println("单词的意思是:  "+l1.find(findValue,BaseUrl+"history.txt"));
                        break;

                    case 3:
                        System.out.print("请输入您要删除的单词: ");
                        String removeEng = scan.next();
                        int flag ;
                        flag = l1.remove(removeEng);
                        if(flag==0){
                            System.out.println("删除成功");
                        }else{
                            System.out.println("删除失败");
                        }
                        break;


                    case 4:
                        System.out.print("请输入您要插入的单词: ");
                        String eng = scan.next();
                        System.out.print("请输入您要插入的释义: ");
                        String note = scan.next();
                        int insertValue = l1.insert(new Item(eng,note));
                        if(insertValue == 0){
                            System.out.println("插入成功");
                        }
                        break;

                    case 5:
                        System.out.println("您搜索过的历史记录为:");
                        int showHistValue = l1.showHist(BaseUrl+"history.txt");
                        if(showHistValue == 0){
                            System.out.println("查询成功");
                        }
                        break;

                    case 6:
                        System.out.println("是否另存为:");
                        String req = scan.next();
                        int i;
                        if(req.compareTo("y" )==0|| req.compareTo("Y")==0){
                            System.out.println("请输入你要保存的地址(绝对地址):");
                            String SavePath = scan.next();
                            i = l1.saveAs(SavePath);
                            if(i==0){
                                System.out.println("保存成功：路径为"+ SavePath);
                            }else{
                                System.out.println("保存失败");
                            }
                        }else{
                            i = l1.saveAs(pathCet4word);
                            if(i==0){
                                System.out.println("保存成功：路径为"+ pathCet4word);
                            }else{
                                System.out.println("保存失败");
                            }
                        }
                        break;
                }
            }
        }else{
            System.out.println("数据加载失败,程序已停止");
        }


    }
}
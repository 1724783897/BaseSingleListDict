
import java.io.*;
public class Dict {
    Node first;     //首节点
    Item data;      //节点数据内容
    int n;          //链表长度
    Node endNode;   //末尾节点
    History history_list_first ; // 历史记录节点
    /*
    *  字典初始化
    * */
    public Dict(){
        this.data = null;
    }


    /*
    *
    * 加载单词,存入单链表，排序后结果
    *
    * */
    public int load(String formName){
        first = null;
        Node r;
        endNode = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(formName));
            String s;
            int k = 0;
            String pre ;//单词eng
            String end;//释义note
            while ((s=br.readLine())!=null){
                int pos = s.indexOf(",");
                pre = s.substring(0,s.indexOf(","));
                end = s.substring(pos+1);
                r = new Node();
                if(first == null){
                    r.data = new Item(pre,end);
                    //r.data.eng= pre;错误演示
                    //r.data.note= end;错误演示
                    first = endNode = r;
                }else {
                    r.data = new Item(pre, end);
                    endNode.next = r;
                    endNode = r;
                }
                k++;
            }
            n = k;
            br.close();
        }catch (IOException ex){
            ex.printStackTrace();
            return -1;
        }
        this.dict_sort();
        return 0;
    }

    /*
    *
    * 查找单词
    *
    * */
    public String find(String english){
        Node p = first;
        History h_r;   //历史记录头节点
        h_r = new History(english);
        h_r.next = history_list_first;
        history_list_first = h_r;

        while (p!=null){
            if(p.data.eng.compareTo(english) == 0){
                return p.data.note;
            }
            p = p.next;
        }

        return "NOT FOUND";
    }

    /*
    *
    * 显示单词
    *
    * */
    void show(){
        Node p = first;
        while (p != null){
            System.out.println(p.data.eng + "      释义：" + p.data.note);
            p = p.next;
        }
        System.out.println();
    }



    /*
    *
    * 获取指定位置的节点
    *
    * */
    private Node getNode(int pos){
        Node p = first;
        int c = 1;
        while (c < pos){
            p = p.next;
            c++;
        }
        return p;
    }

    /*
    *
    *字典排序
    *
    * */
    private void dict_sort(){
        Node p ,q;
        Item temp;
        for(p = first; p!=null; p=p.next){
            for(q=p.next; q!=null;q=q.next){
                if(p.data.eng.compareTo(q.data.eng)>0){
                    temp = q.data;
                    q.data = p.data;
                    p.data = temp;
                }
            }
        }
    }

    /*
    *
    * 删除单词
    *
    * */
    public int remove(String eng){
        Node p=first,q;
        int pos=1;
        while (p!=null){
            if(p.data.eng.compareTo(eng)==0){
                q = getNode(pos-1);
                q.next = p.next;
                return 0;
            }
            p=p.next;
            pos++;
        }
        return -1;
    }

    /*
    *
    * 历史列表
    *
    * */

    int history_list_show(){
        History p=history_list_first;
        while (p!=null){
            System.out.println(p.history_item);
            p = p.next;
        }
        System.out.println();
        return 0;
    }

    /*
    *
    * 插入单词
    *
    * */

    public int insert(Item item){
        Node p ,r,preNode;
        int pos = 1;
        for(p=first;p!=null;p=p.next){
            if(p.data.eng.compareTo(item.eng) > 0){
                r= new Node();
                r.data = item;
                if(pos!=1){
                    preNode = getNode(pos-1);
                    r.next = preNode.next;
                    preNode.next = r;
                }else{
                    r.next = first;
                    first =r;

                }
                n++;
                return 0;
            }
            pos++;
        }
        r = new Node();
        r.data=item;
        preNode = getNode(pos-1);
        r.next = preNode.next;
        preNode.next = r;
        n++;
        return 0;
    }

    /*
    *
    * 保存文件
    *
    * */
    public int saveAs(String toFile){
        Node p = first;
        File f = new File(toFile);
        try {
            FileWriter fw = new FileWriter(f,false);
            BufferedWriter bw = new BufferedWriter(fw);
            while (p!=null){
                bw.write(p.data.eng +","+ p.data.note+"\n");
                p = p.next;
            }
            bw.close();
        } catch (IOException e) {
            return -1;
        }
        return 0;
    }
}

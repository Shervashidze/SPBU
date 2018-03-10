package group144.shervashidze;

public class Main {

    public static void main(String[] args) {
        List list = new List();
        list.add(1);
        list.add(2);
        list.add(3,1444);
        list.add(5,1);
        System.out.println(list.find(7));
        System.out.println(list.find(3));
        System.out.println(list.pop());
        System.out.println(list.pop());
        System.out.println(list.pop());
        System.out.println(list.getLength());
        System.out.println(list.pop());
    }
}


public class task4 {
    public static class Dessert{
        int flavor;
        int price;
        static int numDesserts = 0;

        public Dessert(int flavor, int price){
            this.flavor = flavor;
            this.price = price;
            numDesserts += 1;
        }

        public void printDessert(){
            System.out.println(this.flavor+" "+this.price+" "+numDesserts);
        }

    }
    public static void main(String[] args){
        Dessert D1 = new Dessert(1,2);
        D1.printDessert();
        Dessert D2 = new Dessert(3,4);
        D2.printDessert();
        System.out.println("I love desserts!");
    }


}

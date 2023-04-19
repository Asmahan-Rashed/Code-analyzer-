import java.awt.Desktop;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;  //Import the Scanner class
import java.io.FileWriter;

class input {
	File output = new File("out.txt");
    public static FileWriter w;

    static {
        try {
            w = new FileWriter("out.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
	
	File outcss = new File("path/style.css");
    public static FileWriter wcss;

    static {
        try {
            wcss = new FileWriter("path/style.css");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
	
    static void add(int a, int b) throws Exception  {
		//block number 1

		w.write("block number 1 visited\n");
		wcss.write("#block_1{\n" +
                    "    background-color: #83F492;\n" +
                    "}");
            //1
        int c=a+b;
        if(c==a+b || b==9) {
			if(c==a+b){

			wcss.write("#expr_1{background-color:#F76C3C}\n");

			}

		//block number 2

		w.write("block number 2 visited\n");
		wcss.write("#block_2{\n" +
                    "    background-color: #83F492;\n" +
                    "}");
            //2
            System.out.println("branch coverge :)");}

    }
    public static void main(String[] args)throws Exception{
		wcss.write(".global_class{background-color:#F73C78}\n" +
                "body{\n" +
                "background-color: #83F492;\n" +
                "}\n");
		//block number 3

		w.write("block number 3 visited\n");
		wcss.write("#block_3{\n" +
                    "    background-color: #83F492;\n" +
                    "}");
            //3
        int x = 1, z = 1,u=1,g=0,k=10;
        add(x,z);
        Scanner myObj = new Scanner(System.in);
        System.out.println("h");

        int c =myObj.nextInt();
        if (c>=18 || x==1 || u==1){ 
			if(c>=18||x==1){

			wcss.write("#expr_3{background-color:#F76C3C}\n");

			}
System.out.println("you're allowed ");
		//block number 4

			w.write("block number 4 visited\n");
			wcss.write("#block_4{\n" +
                    "    background-color: #83F492;\n" +
                    "}");
            }//4
        if (x==1){
		//block number 5

		w.write("block number 5 visited\n");
		wcss.write("#block_5{\n" +
                    "    background-color: #83F492;\n" +
                    "}");
            //5
            if (z==2){
		//block number 6

		w.write("block number 6 visited\n");
		wcss.write("#block_6{\n" +
                    "    background-color: #83F492;\n" +
                    "}");
            //6
                System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
            }
        }
        for (int i=0;i==88||i<10;i++){ 
			if(i==88){

			wcss.write("#expr_6{background-color:#F76C3C}\n");

			}
System.out.println("Heuuuuuuu");
		//block number 7

			w.write("block number 7 visited\n");
			wcss.write("#block_7{\n" +
                    "    background-color: #83F492;\n" +
                    "}");
            }//7

        System.out.println("vhkfvuirif");
        while (x==1 || x==0){ 
			if(x==1){

			wcss.write("#expr_7{background-color:#F76C3C}\n");

			}
x=2;
		//block number 8

			w.write("block number 8 visited\n");
			wcss.write("#block_8{\n" +
                    "    background-color: #83F492;\n" +
                    "}");
            }//8
        if(x==9||x==2){//9
            
			if(x==9){

			wcss.write("#expr_8{background-color:#F76C3C}\n");

			}
System.out.println("ljjvghuf");
		//block number 9

			w.write("block number 9 visited\n");
			wcss.write("#block_9{\n" +
                    "    background-color: #83F492;\n" +
                    "}");
            }
        //sub(x,z);
        while (k>0 || x==1)//10
        {
			if(k>0){

			wcss.write("#expr_9{background-color:#F76C3C}\n");

			}

		//block number 10

		w.write("block number 10 visited\n");
		wcss.write("#block_10{\n" +
                    "    background-color: #83F492;\n" +
                    "}");
            
            System.out.println("hi");
            k--;
            x=0;
        }

    String url ="path/new.html";
        File htmlFile = new File(url);
        Desktop.getDesktop().browse(htmlFile.toURI());

		wcss.close();

		w.close();
}
    static void sub(int a, int b)throws Exception{
		//block number 11

		w.write("block number 11 visited\n");
		wcss.write("#block_11{\n" +
                    "    background-color: #83F492;\n" +
                    "}");
            //11
        int c=a+b;
        if(c==a+b || b==9){//12
            
			if(c==a+b){

			wcss.write("#expr_11{background-color:#F76C3C}\n");

			}
System.out.println("branch coverge :)");
		//block number 12

			w.write("block number 12 visited\n");
			wcss.write("#block_12{\n" +
                    "    background-color: #83F492;\n" +
                    "}");
            }
        //System.out.println(c);
    }

}

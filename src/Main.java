import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.io.FileUtils;
import java.awt.Desktop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Scanner;  // Import the Scanner class


public class Main {
    public static void main(String[] args) throws Exception {

        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter your input path");
        String inputFile = myObj.nextLine();//"input.java";

        FileInputStream is = new FileInputStream(inputFile);
        ANTLRInputStream input = new ANTLRInputStream(is);
        JavaLexer lexer = new JavaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        ParseTree tree = parser.compilationUnit();

        //that rewriter writes into output.java, and output.java writes the css file and also the out.txt file
        TokenStreamRewriter rewriter = new TokenStreamRewriter(tokens);
        ParseTreeWalker walker= new ParseTreeWalker();

        //that rewriter writes into the new.html file: create divs and spans with different ids and classes
        TokenStreamRewriter rewriter_html = new TokenStreamRewriter(tokens);
        walker.walk(new TestIf(rewriter,rewriter_html), tree);

        File output = new File("out.java");
        output.createNewFile();
        FileWriter w = new FileWriter("out.java");
        w.write(rewriter.getText());
        w.close();

        File htmlTemplateFile = new File("src/template.html");
        String htmlString = FileUtils.readFileToString(htmlTemplateFile,"UTF-8");
        String title = "New Page";
        htmlString = htmlString.replace("$title", title);

        String url ="path/new.html";
        File newHtmlFile = new File(url);

        String body=rewriter_html.getText();
        //String body = code;//"<div style="+"background-color:powderblue"+";"+">"+code+"</div>";
        htmlString = htmlString.replace("$body", body);

        FileUtils.writeStringToFile(newHtmlFile, htmlString);

        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec("java out.java");
    }
}
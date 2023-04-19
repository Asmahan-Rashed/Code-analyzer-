import org.antlr.v4.runtime.TokenStreamRewriter;

public class TestIf extends JavaParserBaseListener{

    int count;
    TokenStreamRewriter rewriter;
    TokenStreamRewriter rewriter_html;

    public TestIf(TokenStreamRewriter rewriter,TokenStreamRewriter rewriter_html) {
        this.rewriter = rewriter;//that one writes the aug. code, aka output.java
        this.rewriter_html=rewriter_html;
        this.count=0;
    }


    @Override
    public void enterStatement(JavaParser.StatementContext ctx) {
        String code="";
        String span_id="expr_"+count;
        if ( ctx.FOR() !=null ){
            if(ctx.statement(0).block()== null)
            {
                //count++;
                rewriter.insertAfter(ctx.RPAREN().getSymbol(),"{");
            }
             String for_control  = ctx.forControl().expression().getText().trim();

             code=branch_coverage_check(for_control);

             if (code != "")
            {
                rewriter_html.insertBefore(ctx.forControl().getStart(), "<span  class=\"global_span\" id=\""+span_id+"\">");
                rewriter_html.insertAfter(ctx.forControl().getStop(), "</span>");
            }


        }
        if ( ctx.WHILE() !=null  || ctx.IF()!=null){
            if(ctx.statement(0).block()== null)
            {
                rewriter.insertAfter(ctx.parExpression().RPAREN().getSymbol(),"{" );//#@#
            }
            String s  = ctx.parExpression().expression().getText().trim();
            code=branch_coverage_check(s);
            if (code != "")
            {
                rewriter_html.insertBefore(ctx.parExpression().getStart(), "<span  class=\"global_span\" id=\"" + span_id + "\">");
                rewriter_html.insertAfter(ctx.parExpression().getStop(), "</span>");
            }


        }
        if (ctx.FOR() !=null || ctx.WHILE() !=null  || ctx.IF()!=null)
        {
            if(ctx.statement(0).block()== null) // 1 line for,while and if
            {
                count++;
                rewriter.insertBefore(ctx.statement(0).getStart(), code);

                rewriter.insertAfter(ctx.statement(0).getStop(), "\n\t\t//block number "+count+"\n"+"\n\t\t\tw.write(\"block number " + count + " visited\\n\");");
                rewriter.insertAfter(ctx.statement(0).getStop(), "\n\t\t\twcss.write(\"#block_" + count + "{\\n\" +\n" +
                        "                    \"    background-color: #83F492;\\n\" +\n" +//make block id green if the cond. is true
                        "                    \"}\");\n" +
                        "            " + "}");//extremly important; that's how we close the block that we started at #@#
                String ss = "block_" + count;
                rewriter_html.insertBefore(ctx.getStart(), "<div  class=\"global_class\" id=\"" + ss + "\">");
                rewriter_html.insertAfter(ctx.getStop(), "</div>");

            }
            else{  rewriter.insertAfter(ctx.statement(0).getStart(), code);}
        }
    }

    String branch_coverage_check(String s){
        String code="";
        int or_idx = s.lastIndexOf("||");
        if (or_idx != -1) {//true if and only if there is an or
            String first_conditions_befor_or = s.substring(0, or_idx);//26
            String span_id_eq_orange = "\n\t\t\twcss.write(\"#expr_"+count+"{background-color:#F76C3C}\\n\");\n";

            code = "\n\t\t\tif(" + first_conditions_befor_or + "){\n" + span_id_eq_orange + "\n\t\t\t}\n";//for writer that writes in out.java
        }

        return code;
    }

    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {
        count++;
        rewriter.insertAfter(ctx.getStart(), "//block number " + count + "\n");
        rewriter.insertAfter(ctx.getStart(), "\n\t\tw.write(\"block number "+ count +" visited\\n\");" );
        rewriter.insertAfter(ctx.getStart(),"\n\t\twcss.write(\"#block_"+count+"{\\n\" +\n" +
                "                    \"    background-color: #83F492;\\n\" +\n" +
                "                    \"}\");\n" +
                "            ");
        String s= "block_"+count;
        rewriter_html.insertBefore(ctx.getStart(), "<div  class=\"global_class\" id=\""+s+"\">");
        rewriter_html.insertAfter(ctx.getStop(), "</div>");
    }
    @Override
    public void enterCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        int num_import= ctx.children.size()-1;
        int f_File=0, f_Filewriter=0, f_IOException=0, f_Desktop=0;
        for (int i=0;i<num_import;i++)
        {
         String imp= String.valueOf(ctx.importDeclaration(i).getText()).trim().strip();
         //System.out.println(imp.trim().strip());
         if ( imp.equals("importjava.io.File;") ) f_File=1;
         if ( imp.equals("importjava.io.FileWriter;") ) f_Filewriter=1;
         if ( imp.equals("importjava.io.IOException;") ) f_IOException=1;
         if ( imp.equals("importjava.awt.Desktop;") ) f_Desktop=1;
        }
        if (f_File==0) rewriter.insertBefore(ctx.getStart(),"import java.io.File;\n");
        if(f_Filewriter==0) rewriter.insertBefore(ctx.getStart(),"import java.io.FileWriter;\n");
        if (f_IOException==0) rewriter.insertBefore(ctx.getStart(),"import java.io.IOException;\n" );
        if(f_Desktop==0) rewriter.insertBefore(ctx.getStart(),"import java.awt.Desktop;\n");
        // "import java.util.Scanner;\n");// user should put its own scanner if they need it, I'm not their babysitter
    }


}
 @Override
    public void enterClassBody(JavaParser.ClassBodyContext ctx) {
        rewriter.insertAfter(ctx.LBRACE().getSymbol(),"\n\tFile output = new File(\"out.txt\");\n" +
                "    public static FileWriter w;\n" +
                "\n" +
                "    static {\n" +
                "        try {\n" +
                "            w = new FileWriter(\"out.txt\");\n" +
                "        } catch (IOException e) {\n" +
                "            throw new RuntimeException(e);\n" +
                "        }\n" +
                "    }\n\t");
        rewriter.insertAfter(ctx.LBRACE().getSymbol(),"\n\tFile outcss = new File(\"path/style.css\");\n" +
                "    public static FileWriter wcss;\n" +
                "\n" +
                "    static {\n" +
                "        try {\n" +
                "            wcss = new FileWriter(\"path/style.css\");\n" +
                "        } catch (IOException e) {\n" +
                "            throw new RuntimeException(e);\n" +
                "        }\n" +
                "    }\n\t");
    }

    @Override
    public void exitMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        if (ctx.qualifiedNameList() == null )
            rewriter.insertBefore(ctx.methodBody().getStart(),"throws Exception");

        if((ctx.identifier().getText()).equals("main")){
            rewriter.insertBefore(ctx.methodBody().getStop(),"\n\t\tw.close();\n");
            rewriter.insertBefore(ctx.getStop(),"\n\t\twcss.close();\n");
            rewriter.insertBefore(ctx.getStop(),"String url =\"path/new.html\";\n" +//open html broweser
                    "        File htmlFile = new File(url);\n" +
                    "        Desktop.getDesktop().browse(htmlFile.toURI());\n");
        }
    }

    @Override
    public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        if((ctx.identifier().getText()).equals("main")){
            rewriter.insertAfter(ctx.methodBody().block().LBRACE().getSymbol(), "\n\t\twcss.write(\".global_class{background-color:#F73C78}\\n\" +\n" +
                "                \"body{\\n\" +\n" +
                "                \"background-color: #83F492;\\n\" +\n" +
                "                \"}\\n\");" );
    }
    }
}


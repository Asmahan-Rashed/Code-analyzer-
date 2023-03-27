import org.antlr.v4.runtime.TokenStreamRewriter;

import java.util.HashMap;

public class Testvisit extends JavaParserBaseVisitor{
    Integer check;
    HashMap<String,Integer> variable= new HashMap<String,Integer>();
    TokenStreamRewriter rewriterr;

    public Testvisit(TokenStreamRewriter rewriterr)
    {
        this.rewriterr = rewriterr;
    }


    @Override
    public Integer visitVariableDeclarator(JavaParser.VariableDeclaratorContext ctx) {
        String var = String.valueOf(ctx.variableDeclaratorId().identifier().IDENTIFIER().getText());
        //System.out.println(var);
        Integer value= Integer.valueOf(ctx.variableInitializer().expression().primary().literal().getText());
        //System.out.println(value);
        variable.put(var,value);
        return value;
    }

    @Override
    public Integer visitParExpression(JavaParser.ParExpressionContext ctx) {
        String var = String.valueOf(ctx.expression().expression(0).primary().identifier().getText());
        //System.out.println(var);
        Integer hashval = variable.get(var);
        //System.out.println(hashval);
        Integer right = Integer.valueOf(ctx.expression().expression(1).primary().literal().getText());
        //System.out.println(right);
        if((ctx.expression().EQUAL().getText().equals("==")) && (hashval.equals(right))){
            rewriterr.insertAfter(ctx.getStop(),"//Visited");
        }
        return 1;
    }


}

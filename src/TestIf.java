import org.antlr.v4.runtime.TokenStreamRewriter;

public class TestIf extends JavaParserBaseListener{

    int count;
    TokenStreamRewriter rewriter;

    public TestIf(TokenStreamRewriter rewriter) {
        this.rewriter = rewriter;
        this.count=0;
    }

    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {
        count++;
        rewriter.insertAfter(ctx.getStart(), "//block number " + count + "\n");
    }


}


package ast;

import java.util.Stack;
import java.util.StringTokenizer;

import utils.Constants;

public class ASTbuilder
{
	/**
	 * Expression string ---> into an expression tree
	 *   * Stack-based construction
	 * 
	 * @param str -- a string corresponding to an expression in our language (mostly postfix)
                     All tokens must have a space between them: e.g., 4 12 +   evaluates to 16
					 except prefix expressions: "+-+4" equates to "-4".
	 * @return an Expression Tree in the form of an ASTnode
	 */
	public static ASTnode build(String str)
	{
		// Split the string into individual tokens
		StringTokenizer tokenizer = new StringTokenizer(str, Constants.DELIMITER);

		// Convert expression via a stack technique
		Stack<ASTnode> stack = new Stack<ASTnode>();

		// Iterate through the tokens
		while (tokenizer.hasMoreTokens())
		{
			String token = tokenizer.nextToken();

            //
            // TODO: build the AST
            //
		}

		return stack.peek();
	}
}

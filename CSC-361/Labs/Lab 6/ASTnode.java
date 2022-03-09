/**
 * Abstract Syntax Tree (AST) class hierarchy for representing expressions trees
 * according to the following language:
 * 
 *    Unary Postfix Operators
 *      A: UnaryAbsoluteValueNode
 *      S: UnarySquareRootNode
 *
 *    Binary Operators
 *      +:  BinaryPlusNode
 *      -:  BinaryMinusNode
 *      *:  BinaryTimesNode
 *      /:  BinaryDivideNode
 *      **: BinaryPowerNode
 *
 *    Unary Prefix
 *      +: UnaryPlusNode
 *      -: UNaryMinusNode
 *      
 *    Some sample expressions:
 *      5 3 +
 *      5.0 3.0 *
 *      1.0 A 2.0 S 3.0 A 4.0 S -+5.0 + - * / A ----12.3 + 4 **
 * 
 * @author 
 */

package ast;

import utils.Constants;

//
// Top-Level Abstract AST node class
//
public abstract class ASTnode
{
	ASTnode() { }
	
	boolean isNull(){ return false; }
	
	public abstract void unparse(StringBuilder sb);
}

//
// A support class to return from methods in case of failure.
//
class NullNode extends ASTnode
{
	NullNode() { super(); }

	boolean isNull(){ return true; }
	
	public void unparse(StringBuilder sb) { sb.append("null"); }
}

//
//
// LITERAL NODES
//
//
abstract class LiteralNode extends ASTnode
{
	protected double _value;
	
	public double get() { return _value; }
	
	LiteralNode(double value)
	{
		super();
        _value = value;
    }
	
	public abstract void unparse(StringBuilder sb);
}

class IntLitNode extends LiteralNode
{
	IntLitNode(int value)
    {
        super(value);
    }


}

class RealLitNode extends LiteralNode
{
	RealLitNode(double value)
    {
        super(value);
    }

}

//                //
//                //
// Operations     //
//                //
//                //
abstract class OperationNode extends ASTnode
{
	OperationNode() { super(); }
}

//
//
// Binary Operation Nodes
//
//
abstract class BinaryExprNode extends OperationNode
{
	ASTnode _left;
	ASTnode _right;

	BinaryExprNode()
    {
		super();
		_left = null;
		_right = null;
    }

	BinaryExprNode(ASTnode left, ASTnode right)
    {
		super();
		_left = left;
		_right = right;
    }
}

class BinaryPlusNode extends BinaryExprNode
{
	BinaryPlusNode(ASTnode left, ASTnode right) { super(left, right); }
	
}

class BinaryMinusNode extends BinaryExprNode
{
	BinaryMinusNode(ASTnode left, ASTnode right) { super(left, right); }
	
}

class BinaryTimesNode extends BinaryExprNode
{
	BinaryTimesNode(ASTnode left, ASTnode right) { super(left, right); }
	
}

class BinaryDivideNode extends BinaryExprNode
{
	BinaryDivideNode(ASTnode left, ASTnode right) { super(left, right); }
	
}

class BinaryPowerNode extends BinaryExprNode
{
	BinaryPowerNode(ASTnode left, ASTnode right) { super(left, right); }

}

//
//
// Unary Operation Nodes
//
//
abstract class UnaryExprNode extends OperationNode
{
	ASTnode _expr;

	UnaryExprNode()
    {
		super();
		_expr = null;
    }

	UnaryExprNode(ASTnode expr)
    {
		super();
		_expr = expr;
    }
}

class UnaryPlusNode extends UnaryExprNode
{
	UnaryPlusNode(ASTnode expr) { super(expr); }
	
}

class UnaryMinusNode extends UnaryExprNode
{
	UnaryMinusNode(ASTnode expr) { super(expr); }
	
}

class UnaryAbsoluteValueNode extends UnaryExprNode
{
	UnaryAbsoluteValueNode(ASTnode expr) { super(expr); }
	
}

class UnarySquareRootNode extends UnaryExprNode
{
	UnarySquareRootNode(ASTnode expr) { super(expr); }
	
}
package org.teaminfty.math_dragon.view.math;

import org.matheclipse.core.expression.F;
import org.matheclipse.core.interfaces.IExpr;
import org.teaminfty.math_dragon.exceptions.EmptyChildException;
import org.teaminfty.math_dragon.exceptions.NotConstantException;

import android.graphics.Canvas;
import android.graphics.Rect;

public class MathOperationSubtract extends MathBinaryOperationLinear
{
    public MathOperationSubtract()
    {}

    public MathOperationSubtract(MathObject left, MathObject right)
    { 
        super(left, right);
    }
    
    @Override
    public int getPrecedence()
    { return MathObjectPrecedence.ADD; }
    
    @Override
    public IExpr eval() throws EmptyChildException
    {
        // Check if the children are not empty
        this.checkChildren();
        
        // Return the result
        return F.Subtract(getChild(0).eval(), getChild(1).eval());
    }

    @Override
    public double approximate() throws NotConstantException, EmptyChildException
    {
        // Check if the children are not empty
        this.checkChildren();
        
        // Return the result
        return getChild(0).approximate() - getChild(1).approximate();
    }
    
    @Override
    public void draw(Canvas canvas)
    {
        // Draw the bounding boxes
        drawBoundingBoxes(canvas);
        
        // Get the bounding box
        final Rect operator = getOperatorBoundingBoxes()[0];
        
        // Draw the operator
        operator.inset(operator.width() / 10, operator.height() / 10);      // Padding
        canvas.save();
        operatorPaint.setStrokeWidth(lineWidth);
        operatorPaint.setColor(getColor());
        canvas.drawLine(operator.left, operator.centerY(), operator.right, operator.centerY(), operatorPaint);
        canvas.restore();
        
        // Draw the children
        drawChildren(canvas);
    }
}

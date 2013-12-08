package org.teaminfty.math_dragon;

import org.matheclipse.core.eval.EvalEngine;
import org.matheclipse.core.expression.F;

import org.teaminfty.math_dragon.R;

import org.teaminfty.math_dragon.MathObject.EmptyChildException;
import org.teaminfty.math_dragon.MathObject.NotConstantException;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentMainScreen extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_screen, container, false);
    }
    
    @Override
    public void onStart()
    {
        super.onStart();
        
        // Create a MathObject to test it
        MathOperationDivide div = new MathOperationDivide(100, 100);
        MathOperationAdd plus = new MathOperationAdd(100,100);
        plus.setChild(0,new MathConstant(11,100,100));
        plus.setChild(1, new MathConstant(21,100,100));
        div.setChild(0, plus);
        div.setChild(1, new MathConstant(31, 100, 100));
        
        // Test the MathObject
        try
        {
            Log.i(getClass().getCanonicalName(), EvalEngine.eval(F.Simplify(div.eval())).toString());
            Log.i(getClass().getCanonicalName(), Double.toString(div.approximate()));
        }
        catch(EmptyChildException e)
        {
            e.printStackTrace();
        }
        catch(NotConstantException e)
        {
            e.printStackTrace();
        }
        
        // Just to test MathView
        MathView mathView = (MathView) getView().findViewById(R.id.mathView);
        mathView.setMathObject(div);
    }

}

/*
 * Charles Lett Jr.
 * pizza party test app
 * 02/07/21
 * Test app for calculating number of pizzas needed for a party
 */
package com.example.pizzapartytestapp;

//DON'T ENABLE IMPORT ON THE FLY
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    //sets the number of slices for pizza
    private final String KEY_TOTAL_PIZZAS = "totalPizza";
    private int mTotalPizzas;

    //initializes variables for use in program
    private EditText mNumAttendEditText;
    private TextView mNumPizzasTextView;
    private RadioGroup mHowHungryRadioGroup;

    //no clue
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //R = res folder (resources; layout = folder in res for layout;
        // activity_main = .xml in layout folder
        setContentView(R.layout.activity_main);

        //assigns widgets to fields? -- enables buttons/empty fields for use?
        //R. = resource folder
        //has to do with the user input, text, and button?
        mNumAttendEditText = findViewById(R.id.attendEditText);
        mNumPizzasTextView = findViewById(R.id.answerTextView);
        mHowHungryRadioGroup = findViewById(R.id.hungryRadioGroup);

        //restores state if focus is lost?
        //saves total number of pizzas needed
        if (savedInstanceState != null){
            mTotalPizzas = savedInstanceState.getInt(KEY_TOTAL_PIZZAS);
            displayTotal();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_TOTAL_PIZZAS, mTotalPizzas);
    }

    @SuppressLint("SetTextI18n")
    public void calculateClick(View view) {
        int numAttend;
        try {
            //stores what was typed in edit text (should have named something else)
            String numAttendStr = mNumAttendEditText.getText().toString();
            //converts string into int; see if can be made to take an integer to avoid extra code
            numAttend = Integer.parseInt(numAttendStr);
        }
        catch (NumberFormatException emptyNumAttend){
            numAttend = 0;
        }

        //for how many slices needed?
        int checkedId = mHowHungryRadioGroup.getCheckedRadioButtonId();

        PizzaCalculator.HungerLevel hungerLevel = PizzaCalculator.HungerLevel.RAVENOUS;

        //if statement for hunger options
        if(checkedId == R.id.lightRadioButton){
            hungerLevel = PizzaCalculator.HungerLevel.LIGHT;
        }
        else if(checkedId == R.id.mediumRadioButton){
            hungerLevel = PizzaCalculator.HungerLevel.MEDIUM;
        }

        //gets the number of pizzas needed
        PizzaCalculator calc = new PizzaCalculator(numAttend, hungerLevel);
        mTotalPizzas = calc.getTotalPizzas();

        //displays number of pizzas needed
        if (numAttend == 0){
            mNumPizzasTextView.setText("Please enter a number.");
        }

        else {
            displayTotal();
        }

    }

    private void displayTotal(){
        String totalText = getString(R.string.total_pizzas, mTotalPizzas);
        mNumPizzasTextView.setText(totalText);
    }

}
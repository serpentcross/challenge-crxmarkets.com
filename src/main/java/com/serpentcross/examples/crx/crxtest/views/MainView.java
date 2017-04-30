package com.serpentcross.examples.crx.crxtest.views;

import com.serpentcross.examples.crx.crxtest.constants.CodeStrings;

import lombok.Getter;
import lombok.Setter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.Random;

@ManagedBean(name = "mainView")
@ViewScoped
public class MainView {

    @Getter @Setter private String textValue;
    @Getter @Setter private String[] initialArray;

    private int[] intArray;

    @Getter @Setter private String answer = null;
    @Getter @Setter private boolean submitIsDisabled = true;

    public void renderInitialArray() {

        int arraySize;

        try {

            answer = CodeStrings.ANSWER;

            Random random = new Random();

            arraySize = Integer.parseInt(textValue);

            initialArray = new String[arraySize];
            intArray = new int[arraySize];

            if(arraySize < 0) {
                arraySize = 0;
                initialArray = null;
            }

            for (int i = 0; i < arraySize; i++) {
                //Filling our array by generating random numbers
                initialArray[i] = Integer.toString(random.nextInt(arraySize) + 1);
                intArray[i] = Integer.parseInt(initialArray[i]);
            }

            submitIsDisabled = false;

        } catch (NumberFormatException ex) {
            //Displaying an error, if something goes wrong
            submitIsDisabled = true;
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", CodeStrings.ERROR_TEXT + textValue);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void submitArrayAction(ActionEvent actionEvent) {
        //Calculating the answer by calling method
        answer = Integer.toString(calculateAnswer(getIntArray()));
    }

    private static int calculateAnswer(int[] src) {

        int volume = 0;

        // Precalculate the maximum landscape height to the right from the current position
        int rightMaxLevel[] = new int[src.length];
        
        for (int i = src.length - 1; i > 0 ; --i) {
            rightMaxLevel[i - 1] = Math.max(src[i], rightMaxLevel[i]);
        }

        int leftMaxLevel = 0;

        for (int i = 0; i < src.length; ++i) {
            // Get the maximum water height at the given position
            int maxLevel = Math.min(leftMaxLevel, rightMaxLevel[i]);

            // Increase the volume by the water height in the given position
            volume += Math.max(maxLevel - src[i], 0);

            // Calculate the left maximum level
            leftMaxLevel = Math.max(leftMaxLevel, src[i]);
        }

        return volume;
    }

    private int[] getIntArray() {
        return intArray;
    }
}

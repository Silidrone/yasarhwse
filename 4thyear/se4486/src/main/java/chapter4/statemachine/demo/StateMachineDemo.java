package chapter4.statemachine.demo;







import chapter4.statemachine.Action;
import chapter4.statemachine.SimpleTransition;
import chapter4.statemachine.StateMachine;
import chapter4.statemachine.Transition;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by dindar.oz on 10/17/2017.
 */
public class StateMachineDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StateMachine<DemoExtData,DemoIntData> sm = buildDemoStateMachine();
        sm.init();

        System.out.println("State Machines Demo");
        while (true)
        {
            // Display the current situation
            if (sm.getCurrentState() != null) {
                System.out.println("Current State: " + sm.getCurrentState());

                System.out.println("Transitions from this state:\n");

                for (Transition<DemoExtData,DemoIntData> transition:sm.getCurrentState().getTransitions())
                {
                    SimpleTransition<DemoExtData,DemoIntData> simpleTransition = (DemoTransition)transition;
                    System.out.println( simpleTransition.getActions().get(0)+" to be "+ simpleTransition.getTargetState().getActions().get(0));
                }
                // Output all valid transitions from here

            } else {
                System.out.println("\n\nNo Current state\nNo Current transitions\n");
            }

            // Ask for input
            System.out.println("Which transition should be allowed to trigger (0[None]-15)\n");
            int option = scanner.nextInt();


            // Run one iteration of the state machine
            DemoExtData extData = new DemoExtData(option,0);
            DemoIntData intData = new DemoIntData();
            List<Action<DemoExtData,DemoIntData>> actions = sm.update(extData,intData);
            for (Action<DemoExtData,DemoIntData> action:actions)
            {
                action.perform(extData,intData);
            }
        }
    }

    private static StateMachine<DemoExtData,DemoIntData> buildDemoStateMachine() {
           final String allText[] = {
            // State text
                    "A", "Entering State A", "In State A", "Exiting State A",
                    "B", "Entering State B", "In State B", "Exiting State B",
                    "C", "Entering State C", "In State C", "Exiting State C",
                    "D", "Entering State D", "In State D", "Exiting State D",
                    "E", "Entering State E", "In State E", "Exiting State E",
                    "F", "Entering State F", "In State F", "Exiting State F",
                    "G", "Entering State G", "In State G", "Exiting State G",

                    // Transition text
                    "Transition 1",
                    "Transition 2",
                    "Transition 3",
                    "Transition 4",
                    "Transition 5",
                    "Transition 6",
                    "Transition 7",
                    "Transition 8",
                    "Transition 9",
                    "Transition 10",
                    "Transition 11",
                    "Transition 12",
                    "Transition 13",
                    "Transition 14",
                    "Transition 15"
        };

        DemoState[] states = new DemoState[7];
        SimpleTransition<DemoExtData, DemoIntData>[] transitions = new DemoTransition[15];

        for (int i = 0; i < 7; i++) {
            DemoState demoState = new DemoState(allText[i*4]);
            demoState.setEntryTexts(Collections.singletonList(allText[i * 4+1]));
            demoState.setActionTexts(Collections.singletonList(allText[i * 4 + 2]));
            demoState.setExitTexts(Collections.singletonList(allText[i * 4 + 3]));
            states[i] = demoState;
        }

        for (int i = 0; i < 15; i++) {
            SimpleTransition<DemoExtData,DemoIntData> transition = new DemoTransition();
            transition.setActions(Collections.singletonList(new DemoAction(allText[28 + i])));

            transition.setCondition(new DemoCondition1(i+1));
            transitions[i]= transition;
        }
        transitions[0].setTargetState( states[1]);
        transitions[1].setTargetState( states[1]);
        transitions[2].setTargetState( states[2]);
        transitions[3].setTargetState( states[3]);
        transitions[4].setTargetState( states[4]);
        transitions[5].setTargetState( states[0]);
        transitions[6].setTargetState( states[5]);
        transitions[7].setTargetState( states[6]);
        transitions[8].setTargetState( states[5]);
        transitions[9].setTargetState( states[2]);
        transitions[10].setTargetState( states[6]);
        transitions[11].setTargetState( states[4]);
        transitions[12].setTargetState( states[6]);
        transitions[13].setTargetState( states[4]);
        transitions[14].setTargetState( states[6]);

        states[0].setTransitions(Arrays.asList(transitions[0],transitions[4]));
        states[1].setTransitions(Arrays.asList(transitions[1],transitions[2],transitions[6],transitions[7]));
        states[2].setTransitions(Arrays.asList(transitions[3],transitions[8]));
        states[3].setTransitions(Arrays.asList(transitions[10]));
        states[4].setTransitions(Arrays.asList(transitions[13],transitions[14]));
        states[5].setTransitions(Arrays.asList(transitions[5],transitions[11],transitions[12]));
        states[6].setTransitions(Arrays.asList(transitions[9]));

        StateMachine<DemoExtData,DemoIntData> stateMachine = new StateMachine<>();
        stateMachine.setInitialState(states[0]);

        return stateMachine;

    }
}

package taskplanner;

import javax.swing.*;
import java.time.LocalDate;

public class DatePickerComponent {
    private JTextField yearInput;
    private JComboBox<String> monthsComboBox;
    private JTextField dayInput;


    DatePickerComponent(JTextField yearInput, JComboBox<String> monthsComboBox, JTextField dayInput) {
        this.yearInput = yearInput;
        this.monthsComboBox = monthsComboBox;
        this.dayInput = dayInput;
    }

    void setDate(LocalDate date) {
        yearInput.setText(Integer.toString(date.getYear()));
        monthsComboBox.setSelectedIndex(date.getMonth().getValue() - 1); //-1 is needed as setSelectedIndex accepts a 0-based index and Month.getValue() returns the month-of-year, from 1 (January) to 12 (December)
        dayInput.setText(Integer.toString(date.getDayOfMonth()));
    }

    LocalDate getDate() {
        try {
            return LocalDate.of(Integer.parseInt(yearInput.getText()), monthsComboBox.getSelectedIndex() + 1, Integer.parseInt(dayInput.getText()));
        } catch (NumberFormatException exception) {
            return null;
        }
    }
}

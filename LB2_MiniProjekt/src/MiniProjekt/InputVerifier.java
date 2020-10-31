package MiniProjekt;

import javax.swing.*;

public class InputVerifier extends javax.swing.InputVerifier {

    @Override
    public boolean verify(JComponent input) {
        JTextField textField = (JTextField) input;
        String text = textField.getText();

        String regex = "[0-9]*+";
        return text.matches(regex);
    }
}

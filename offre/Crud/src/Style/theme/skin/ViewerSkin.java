/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Style.theme.skin;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author SBS
 */
public class ViewerSkin extends SkinAction {

    private boolean shouldMaskText = true;

    public ViewerSkin(PasswordField textField) {
        super(textField);
    }

    @Override
    void mouseReleased() {
        TextField textField = getSkinnable();
        textField.setText(textField.getText());
        textField.end();
    }

    @Override
    void textChanged() {
        if (!getPasswordField().isFocused() && getPasswordField().getText() == null) {
            return;
        }
        getButton().setVisible(getPasswordField().isFocused() && !getPasswordField().getText().isEmpty());
    }

    @Override
    void focusChanged() {
        if (!getPasswordField().isFocused() && getPasswordField().getText() == null) {
            return;
        }
        getButton().setVisible(getPasswordField().isFocused() && !getPasswordField().getText().isEmpty());
    }

    @Override
    void mousePressed() {
        TextField textField = getSkinnable();
        shouldMaskText = false;
        textField.setText(textField.getText());
        shouldMaskText = true;
    }

    @Override
    protected String maskText(String txt) {
        if (getSkinnable() instanceof PasswordField && shouldMaskText) {
            int n = txt.length();
            StringBuilder passwordBuilder = new StringBuilder(n);
            for (int i = 0; i < n; i++) {
                passwordBuilder.append(BULLET);
            }
            return passwordBuilder.toString();
        } else {
            return txt;
        }
    }
}


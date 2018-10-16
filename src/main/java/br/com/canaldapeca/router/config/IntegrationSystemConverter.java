package br.com.canaldapeca.router.config;

import br.com.canaldapeca.router.RouterApplication;

import java.beans.PropertyEditorSupport;

public class IntegrationSystemConverter extends PropertyEditorSupport {

    public void setAsText(final String text) throws IllegalArgumentException {
        setValue(RouterApplication.IntegrationSystem.fromValue(text));
    }

}

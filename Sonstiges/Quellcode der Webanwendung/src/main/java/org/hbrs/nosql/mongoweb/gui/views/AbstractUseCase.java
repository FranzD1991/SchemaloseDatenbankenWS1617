package org.hbrs.nosql.mongoweb.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.hbrs.nosql.mongoweb.gui.components.TopPanel;
import org.hbrs.nosql.mongoweb.model.UseCase;

/**
 * Created by Jan Eric Müller (aka Saso) on 20.12.2016.
 */
public abstract class AbstractUseCase extends VerticalLayout implements View
{
    @Override
    public void enter( ViewChangeListener.ViewChangeEvent viewChangeEvent )
    {
        setUpView();
    }
    
    protected abstract void setUp();
    protected abstract UseCase getUseCase();
    
    protected void setUpView()
    {
        this.addComponent( new TopPanel() );
        
        Label title = new Label( getUseCase().getName() );
        title.setSizeUndefined();
        //title.addStyleName( "useCases" );
        this.addComponent( title );
        
        setMargin( true );
        setSpacing( true );
        
        setUp();
    }
}
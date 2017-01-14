package org.hbrs.nosql.mongoweb.gui.components;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.hbrs.nosql.mongoweb.model.UseCase;

/**
 * Created by Jan Eric Müller (aka Saso) on 20.12.2016.
 */
public class TopPanel extends HorizontalLayout
{
    public TopPanel()
    {
        this.setSizeUndefined();
        this.setSpacing( true );
    
        Label headLabel = new Label( "<b> MongoWeb </b>", ContentMode.HTML );
        headLabel.addStyleName( "myTitel" );
        //headLabel.setSizeUndefined();
        
        this.addComponent( headLabel );
        this.setComponentAlignment( headLabel, Alignment.TOP_LEFT );
        
        HorizontalLayout layout = new HorizontalLayout();
        
        for ( UseCase uc : UseCase.values() )
        {
            Button bt = new Button( uc.getName() );
            bt.addClickListener( (Button.ClickListener)clickEvent -> UI.getCurrent().getNavigator().navigateTo( uc.name() ) );
            layout.addComponent( bt );
        }
    
        layout.setSpacing( true );
        //layout.setWidth( "100%" );
        this.addComponent( layout );
        setComponentAlignment( layout, Alignment.BOTTOM_CENTER );
    }
}

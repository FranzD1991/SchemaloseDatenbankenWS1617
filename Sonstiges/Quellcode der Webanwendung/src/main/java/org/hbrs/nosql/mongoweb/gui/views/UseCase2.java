/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hbrs.nosql.mongoweb.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.hbrs.nosql.mongoweb.db.MongoDBConnector;
import org.hbrs.nosql.mongoweb.gui.components.TopPanel;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import org.hbrs.nosql.mongoweb.model.UseCase;

/**
 * @author Franz
 */
public class UseCase2 extends AbstractUseCase
{
    @Override
    protected void setUp()
    {
        //MongoDB Connector
        MongoDBConnector mongo = new MongoDBConnector();
        
        VerticalLayout uc2 = new VerticalLayout();
        
        Button button = new Button( "Professoren suchen" );
        
        button.addClickListener( e ->
                                 {
                                     uc2.removeAllComponents();
            
                                     LocalTime t1 = LocalTime.now();
            
                                     // load list with values
                                     List uc2answer = mongo.getQuery2();
            
                                     LocalTime t2 = LocalTime.now();
                                     Duration elapsed = Duration.between( t1, t2 );
            
                                     // query over list
                                     for ( int i = 0; i < uc2answer.size(); i++ )
                                     {
                                         //create and post labels for list elements
                                         uc2.addComponent( new Label( (String)uc2answer.get( i ) ) );
                                     }
            
                                     uc2.addComponent( new Label( "Time: " + elapsed ) );
            
                                     addComponent( uc2 );
                                 } );
        
        
        addComponents( button );
        setMargin( true );
        setSpacing( true );
    }
    
    @Override
    protected UseCase getUseCase()
    {
        return ( UseCase.UC2 );
    }
}

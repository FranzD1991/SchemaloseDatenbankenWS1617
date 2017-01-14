/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hbrs.nosql.mongoweb.gui.views;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import org.hbrs.nosql.mongoweb.db.MongoDBConnector;
import org.hbrs.nosql.mongoweb.model.UseCase;

/**
 * @author Franz
 */
public class UseCase7 extends AbstractUseCase
{
    @Override
    protected void setUp()
    {
        //Add your Vaadin Coding here ;)
        //Add your Vaadin Coding here ;)
        //MongoDB Connector
        MongoDBConnector mongo = new MongoDBConnector();
        
        VerticalLayout uc7 = new VerticalLayout();
        
        TextField uc7input = new TextField();
        
        uc7input.setCaption( "Thema prüfen!" );
        
        Button button = new Button( "Suche" );
        
        button.addClickListener( e ->
                                 {
                                     uc7.removeAllComponents();
            
                                     LocalTime t1 = LocalTime.now();
            
                                     // load list with values
                                     List uc7answer = mongo.getQuery7( uc7input.getValue() );
            
                                     LocalTime t2 = LocalTime.now();
                                     Duration elapsed = Duration.between( t1, t2 );
            
                                     // query over list
                                     for ( int i = 0; i < uc7answer.size(); i++ )
                                     {
                                         //create and post labels for list elements
                                         uc7.addComponent( new Label( (String)uc7answer.get( i ) ) );
                                     }
            
                                     uc7.addComponent( new Label( "Time: " + elapsed ) );
            
                                     addComponent( uc7 );
                                 } );
        addComponents( uc7input, button );
        setMargin( true );
        setSpacing( true );
    }
    
    @Override
    protected UseCase getUseCase()
    {
        return ( UseCase.UC7 );
    }
}
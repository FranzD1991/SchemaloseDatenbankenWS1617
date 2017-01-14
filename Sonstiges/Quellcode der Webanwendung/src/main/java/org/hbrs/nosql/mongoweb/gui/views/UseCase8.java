/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hbrs.nosql.mongoweb.gui.views;

import com.mongodb.AggregationOutput;
import com.mongodb.DBObject;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.hbrs.nosql.mongoweb.db.MongoDBConnector;
import org.hbrs.nosql.mongoweb.model.UseCase;

import java.time.Duration;
import java.time.LocalTime;

/**
 * @author Franz
 */
public class UseCase8 extends AbstractUseCase
{
    @Override
    protected void setUp()
    {
        //Add your Vaadin Coding here ;)
        //MongoDB Connector
        MongoDBConnector mongo = new MongoDBConnector();
        
        VerticalLayout uc8 = new VerticalLayout();
    
        Label uc8label = new Label( "Welche Professoren haben wieviele Arbeiten zu einem Thema betreut?" );
        
        TextField uc8input = new TextField();
        uc8input.setCaption( "Thema:" );
    
        Button button = new Button( "Suche" );
        
        button.addClickListener( e ->
                                 {
                                     uc8.removeAllComponents();
            
                                     LocalTime t1 = LocalTime.now();
            
                                     // load list with values
                                     AggregationOutput uc8answer = mongo.getQuery8( uc8input.getValue() );
            
                                     LocalTime t2 = LocalTime.now();
                                     Duration elapsed = Duration.between( t1, t2 );
            
                                     // query over list
                                     for ( DBObject place : uc8answer.results() )
                                     {
                                         if ( !place.get( "_id" ).equals( "" ) )
                                         {
                                             uc8.addComponent(
                                                     new Label( place.get( "_id" ) + ": " + place.get( "Anzahl" ) ) );
                                         }
                                     }
            
                                     uc8.addComponent( new Label( "Time: " + elapsed ) );
            
                                     addComponent( uc8 );
                                 } );
        
        
        addComponents( uc8label, uc8input, button );
        setMargin( true );
        setSpacing( true );
    }
    
    @Override
    protected UseCase getUseCase()
    {
        return ( UseCase.UC8 );
    }
}
